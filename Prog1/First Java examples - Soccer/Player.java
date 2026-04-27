
public class Player extends Person {

    private int shirtNumber;
    private Roles role;
    private boolean captain;

    // Empty constructor — sets default values
    public Player() {
        super.setName("Unknown");
        super.setSurname("Unknown");
        this.shirtNumber = 0;
        this.role = Roles.UNKNOWN;
        this.captain = false;
        super.setNationality("UNKNOWN");
    }

    // Parameterized constructor
    public Player(String name, String surname, int shirtNumber, Roles role, boolean captain, String nationality) {
        super(name, surname, nationality);
        this.shirtNumber = shirtNumber;
        this.role = role;
        this.captain = captain;
    }

    @Override
    public String toString() {
        return super.toString() + "\nRole: " + this.role + "\nShirt number: " + this.shirtNumber + "\nCaptain: " + this.captain;
    }

    public String getAbbreviation() {
        char nameChar, surnameChar;
        nameChar = super.getName().toUpperCase().charAt(0);
        surnameChar = super.getSurname().toUpperCase().charAt(0);
        return "" + nameChar + surnameChar + this.shirtNumber;
    }

    public void setNumber(int number) {
        if (number <= 0 || number > 99) {
            throw new IllegalArgumentException("Invalid shirt number. It must be between 1 and 99.");
        } else {
            this.shirtNumber = number;
        }
    }

    public int getNumber() {
        return this.shirtNumber;
    }

    public void setRole(Roles role) {
        this.role = role;
    }

    public Roles getRole() {
        return this.role;
    }

    public void setCap(boolean captain) {
        this.captain = captain;
    }

    public boolean getCap() {
        return this.captain;
    }

    public String getFullInfo() {
        String flag = (super.getNationality() != null) ? super.getNationality().getFlag() : "❓";
        return "\n - " + flag
                + " " + this.shirtNumber
                + " " + super.getSurname()
                + " " + super.getName().substring(0, 1)
                + " " + (this.captain ? "©" : "");
    }
}

enum Roles {
    ATT, DEF, CEN, POR, UNKNOWN
}
