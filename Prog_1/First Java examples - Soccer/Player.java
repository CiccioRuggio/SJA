
public class Player extends Person {

    private int shirtNumber;
    private Roles role;
    private boolean captain;

    // Empty constructor — sets default values
    public Player() {
        setName("Unknown");
        setSurname("Unknown");
        this.shirtNumber = 0;
        this.role = Roles.UNKNOWN;
        this.captain = false;
        setNationality(Person.Nationalities.UNKNOWN);
    }

    // Parameterized constructor
    public Player(String name, String surname, int shirtNumber, Roles role, boolean captain, String nationality) {
        setName(name);
        setSurname(surname);
        this.shirtNumber = shirtNumber;
        this.role = role;
        this.captain = captain;
        if (nationality == null || nationality.equals("")) {
            setNationality(Person.Nationalities.UNKNOWN);
        } else {
            try {
                setNationality(Person.Nationalities.valueOf(nationality.toUpperCase()));
            } catch (IllegalArgumentException e) {
                setNationality(Person.Nationalities.UNKNOWN);
            }
        }
    }

    public String toString() {
        return "\nName: " + getName() + "\nSurname: " + getSurname() + "\nRole: " + this.role + "\nShirt number: " + this.shirtNumber + "\nCaptain: " + this.captain + "\nNationality: " + getNationality().getFlag();
    }

    public String getAbbreviation() {
        char nameChar, surnameChar;
        nameChar = getName().charAt(0);
        surnameChar = getSurname().charAt(0);
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
        String flag = (getNationality() != null) ? getNationality().getFlag() : "❓";
        return "\n - " + flag
                + " " + this.shirtNumber
                + " " + getSurname()
                + " " + getName().substring(0, 1)
                + " " + (this.captain ? "©" : "");
    }
}

enum Roles {
    ATT, DEF, CEN, POR, UNKNOWN
}
