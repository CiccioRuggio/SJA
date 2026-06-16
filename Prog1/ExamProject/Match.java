
import java.util.ArrayList;

// Runs an entire match on its own thread (started from MatchGUI).
// The simulation is turn based: every turn, this thread asks MatchGUI to show
// a popup and BLOCKS (via SwingUtilities.invokeAndWait, see MatchGUI) until the
// user answers. This guarantees only one thread touches the match state at a
// time (no two popups/updates can ever overlap)
public class Match implements Runnable {

    public static final int MOVES_PER_HALF = 25;
    public static final int MOVES_PER_EXTRA = 10;

    public static final int SHOT_CHANCE_DEF = 25;
    public static final int SHOT_CHANCE_CEN = 50;
    public static final int SHOT_CHANCE_ATT = 75;
    public static final int SAVE_CHANCE = 50;
    public static final int TACKLE_CHANCE = 50;

    private final Team home;
    private final Team away;
    private final Referee referee;
    private final MatchGUI gui;

    private int scoreHome = 0;
    private int scoreAway = 0;
    private int move = 0;
    private String periodName = "";

    private Team possession;
    private Player ballCarrier;
    private Team turnTeam;

    public Match(Team home, Team away, Referee referee, MatchGUI gui) {
        this.home = home;
        this.away = away;
        this.referee = referee;
        this.gui = gui;
    }

    public Team getHome() {
        return this.home;
    }

    public Team getAway() {
        return this.away;
    }

    public Referee getReferee() {
        return this.referee;
    }

    public int getScoreHome() {
        return this.scoreHome;
    }

    public int getScoreAway() {
        return this.scoreAway;
    }

    public int getMove() {
        return this.move;
    }

    public String getPeriodName() {
        return this.periodName;
    }

    public Team getPossession() {
        return this.possession;
    }

    public Player getBallCarrier() {
        return this.ballCarrier;
    }

    public Team getTurnTeam() {
        return this.turnTeam;
    }

    @Override
    public void run() {
        Team winner = gui.runCoinToss(referee, home, away);
        possession = winner;
        turnTeam = winner;
        ballCarrier = pickKickoffPlayer(possession);
        gui.refresh(this);

        playPeriod("1° Tempo", MOVES_PER_HALF);

        gui.runHalftimeSubstitutions(home);
        gui.runHalftimeSubstitutions(away);

        // The ball carrier may have been substituted out at halftime.
        if (!ballCarrier.isStarting()) {
            ballCarrier = pickKickoffPlayer(possession);
        }

        playPeriod("2° Tempo", MOVES_PER_HALF);

        if (scoreHome == scoreAway) {
            gui.announce("Pareggio al 90'! Si va ai tempi supplementari.");
            playPeriod("1° Supplementare", MOVES_PER_EXTRA);
        }
        if (scoreHome == scoreAway) {
            gui.announce("Ancora pareggio! Secondo tempo supplementare.");
            playPeriod("2° Supplementare", MOVES_PER_EXTRA);
        }

        gui.showFinalResult(this);
    }

    private void playPeriod(String name, int maxMoves) {
        this.periodName = name;
        for (this.move = 1; this.move <= maxMoves; this.move++) {
            playTurn();
            gui.refresh(this);
            pause();
        }
    }

    // Plays a single turn for the team whose turn it currently is.
    private void playTurn() {
        if (turnTeam == possession) {
            if (ballCarrier.getRole() == Roles.POR) {
                // Goalkeeper with the ball: only option is to pass it on.
                Player target = gui.askPassTarget(possession, ballCarrier);
                gui.logEvent(ballCarrier.getMatchLabel() + " rilancia su " + target.getMatchLabel() + ".");
                ballCarrier = target;
            } else {
                String choice = gui.askAttackingMove(possession, ballCarrier);
                if ("SHOT".equals(choice)) {
                    resolveShot();
                } else {
                    Player target = gui.askPassTarget(possession, ballCarrier);
                    gui.logEvent(ballCarrier.getMatchLabel() + " passa a " + target.getMatchLabel() + ".");
                    ballCarrier = target;
                }
            }
        } else {
            Team defendingTeam = turnTeam;
            Player tackler = gui.askTackler(defendingTeam, ballCarrier);
            if (roll(TACKLE_CHANCE)) {
                gui.showResult("Contrasto riuscito!",
                        tackler.getMatchLabel() + " recupera il pallone su " + ballCarrier.getMatchLabel() + "!");
                possession = defendingTeam;
                ballCarrier = tackler;
            } else {
                gui.showResult("Contrasto fallito",
                        tackler.getMatchLabel() + " non riesce a recuperare palla. "
                        + ballCarrier.getMatchLabel() + " mantiene il possesso.");
            }
        }

        // Turns always alternate, like in chess.
        turnTeam = (turnTeam == home) ? away : home;
    }

    // Resolves a shot taken by the current ball carrier.
    private void resolveShot() {
        int chance = shotChance(ballCarrier.getRole());
        Team defending = opponentOf(possession);

        if (!roll(chance)) {
            gui.showResult("Tiro fuori",
                    ballCarrier.getMatchLabel() + " calcia ma il tiro è impreciso. Rimessa dal fondo.");
            possession = defending;
            ballCarrier = defending.getGoalkeeper();
            return;
        }

        Player keeper = defending.getGoalkeeper();
        if (roll(SAVE_CHANCE)) {
            gui.showResult("PARATA!",
                    keeper.getMatchLabel() + " para il tiro di " + ballCarrier.getMatchLabel() + "!");
            possession = defending;
            ballCarrier = keeper;
        } else {
            if (possession == home) {
                scoreHome++;
            } else {
                scoreAway++;
            }
            gui.showResult("GOAL!!!",
                    ballCarrier.getMatchLabel() + " segna per " + possession.getName() + "!");
            possession = defending;
            ballCarrier = pickKickoffPlayer(defending);
        }
    }

    // Kickoff/restart is taken by a random starting midfielder (or any
    // outfield starter if the formation has none, which never happens
    // with the formations defined in Team.java).
    private Player pickKickoffPlayer(Team team) {
        Player[] starters = team.getStartingEleven();
        ArrayList<Player> centers = new ArrayList<>();
        for (Player p : starters) {
            if (p.getRole() == Roles.CEN) {
                centers.add(p);
            }
        }
        if (!centers.isEmpty()) {
            return centers.get((int) (Math.random() * centers.size()));
        }
        for (Player p : starters) {
            if (p.getRole() != Roles.POR) {
                return p;
            }
        }
        return starters[0];
    }

    private Team opponentOf(Team team) {
        return team == home ? away : home;
    }

    private int shotChance(Roles role) {
        switch (role) {
            case DEF:
                return SHOT_CHANCE_DEF;
            case CEN:
                return SHOT_CHANCE_CEN;
            case ATT:
                return SHOT_CHANCE_ATT;
            default:
                return 0;
        }
    }

    private boolean roll(int percentChance) {
        return Math.random() * 100 < percentChance;
    }

    // Small pause so the user can follow the live scoreboard/log updates.
    private void pause() {
        try {
            Thread.sleep(350);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }
}
