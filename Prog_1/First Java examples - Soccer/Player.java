
public class Player {

    private String name;
    private String surname;
    private int shirtNumber;
    private Roles role;
    private Nationalities nationality;
    private boolean captain;

    // Empty constructor — sets default values
    public Player() {
        this.name = "Unknown";
        this.surname = "Unknown";
        this.shirtNumber = 0;
        this.role = Roles.UNKNOWN;
        this.captain = false;
        this.nationality = Nationalities.UNKNOWN;
    }

    // Parameterized constructor
    public Player(String name, String surname, int shirtNumber, Roles role, boolean captain, String nationality) {
        this.name = name;
        this.surname = surname;
        this.shirtNumber = shirtNumber;
        this.role = role;
        this.captain = captain;
        if (nationality == null || nationality.equals("")) {
            this.nationality = Nationalities.UNKNOWN;
        } else {
            try {
                this.nationality = Nationalities.valueOf(nationality.toUpperCase());
            } catch (IllegalArgumentException e) {
                this.nationality = Nationalities.UNKNOWN;
            }
        }
    }

    public String toString() {
        return "\nName: " + this.name + "\nSurname: " + this.surname + "\nRole: " + this.role + "\nShirt number: " + this.shirtNumber + "\nCaptain: " + this.captain + "\nNationality: " + this.nationality.getFlag();
    }

    public String getAbbreviation() {
        char nameChar, surnameChar;
        nameChar = this.name.charAt(0);
        surnameChar = this.surname.charAt(0);
        return "" + nameChar + surnameChar + this.shirtNumber;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return this.name;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getSurname() {
        return this.surname;
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

    public Nationalities getNationality() {
        return this.nationality;
    }

    public void setNationality(Nationalities nationality) {
        this.nationality = nationality;
    }

    public String getFullInfo() {
        String flag = (this.nationality != null) ? this.nationality.getFlag() : "❓";
        return "\n - " + flag
                + " " + this.shirtNumber
                + " " + this.surname
                + " " + this.name.substring(0, 1)
                + " " + (this.captain ? "©" : "");
    }
}

enum Roles {
    ATT, DEF, CEN, POR, UNKNOWN
}

enum Nationalities {
    AFGHANISTAN("🇦🇫"), ALBANIA("🇦🇱"), ALGERIA("🇩🇿"), ANDORRA("🇦🇩"), ANGOLA("🇦🇴"),
    ARGENTINA("🇦🇷"), ARMENIA("🇦🇲"), AUSTRALIA("🇦🇺"), AUSTRIA("🇦🇹"), AZERBAIJAN("🇦🇿"),
    BAHAMAS("🇧🇸"), BAHRAIN("🇧🇭"), BANGLADESH("🇧🇩"), BARBADOS("🇧🇧"), BELARUS("🇧🇾"),
    BELGIUM("🇧🇪"), BELIZE("🇧🇿"), BENIN("🇧🇯"), BHUTAN("🇧🇹"), BOLIVIA("🇧🇴"),
    BOSNIA("🇧🇦"), BOTSWANA("🇧🇼"), BRAZIL("🇧🇷"), BRUNEI("🇧🇳"), BULGARIA("🇧🇬"),
    BURKINA_FASO("🇧🇫"), BURUNDI("🇧🇮"), CAMBODIA("🇰🇭"), CAMEROON("🇨🇲"), CANADA("🇨🇦"),
    CAPE_VERDE("🇨🇻"), CENTRAL_AFRICAN_REPUBLIC("🇨🇫"), CHAD("🇹🇩"), CHILE("🇨🇱"), CHINA("🇨🇳"),
    COLOMBIA("🇨🇴"), COMOROS("🇰🇲"), CONGO("🇨🇬"), COSTA_RICA("🇨🇷"), CROATIA("🇭🇷"),
    CUBA("🇨🇺"), CYPRUS("🇨🇾"), CZECH_REPUBLIC("🇨🇿"), DENMARK("🇩🇰"), DJIBOUTI("🇩🇯"),
    DOMINICAN_REPUBLIC("🇩🇴"), DR_CONGO("🇨🇩"), ECUADOR("🇪🇨"), EGYPT("🇪🇬"), EL_SALVADOR("🇸🇻"),
    EQUATORIAL_GUINEA("🇬🇶"), ERITREA("🇪🇷"), ESTONIA("🇪🇪"), ESWATINI("🇸🇿"), ETHIOPIA("🇪🇹"),
    FIJI("🇫🇯"), FINLAND("🇫🇮"), FRANCE("🇫🇷"), GABON("🇬🇦"), GAMBIA("🇬🇲"),
    GEORGIA("🇬🇪"), GERMANY("🇩🇪"), GHANA("🇬🇭"), GREECE("🇬🇷"), GUATEMALA("🇬🇹"),
    GUINEA("🇬🇳"), GUINEA_BISSAU("🇬🇼"), GUYANA("🇬🇾"), HAITI("🇭🇹"), HONDURAS("🇭🇳"),
    HUNGARY("🇭🇺"), ICELAND("🇮🇸"), INDIA("🇮🇳"), INDONESIA("🇮🇩"), IRAN("🇮🇷"),
    IRAQ("🇮🇶"), IRELAND("🇮🇪"), ISRAEL("🇮🇱"), ITALY("🇮🇹"), IVORY_COAST("🇨🇮"),
    JAMAICA("🇯🇲"), JAPAN("🇯🇵"), JORDAN("🇯🇴"), KAZAKHSTAN("🇰🇿"), KENYA("🇰🇪"),
    KOSOVO("🇽🇰"), KUWAIT("🇰🇼"), KYRGYZSTAN("🇰🇬"), LAOS("🇱🇦"), LATVIA("🇱🇻"),
    LEBANON("🇱🇧"), LESOTHO("🇱🇸"), LIBERIA("🇱🇷"), LIBYA("🇱🇾"), LIECHTENSTEIN("🇱🇮"),
    LITHUANIA("🇱🇹"), LUXEMBOURG("🇱🇺"), MADAGASCAR("🇲🇬"), MALAWI("🇲🇼"), MALAYSIA("🇲🇾"),
    MALDIVES("🇲🇻"), MALI("🇲🇱"), MALTA("🇲🇹"), MAURITANIA("🇲🇷"), MAURITIUS("🇲🇺"),
    MEXICO("🇲🇽"), MOLDOVA("🇲🇩"), MONACO("🇲🇨"), MONGOLIA("🇲🇳"), MONTENEGRO("🇲🇪"),
    MOROCCO("🇲🇦"), MOZAMBIQUE("🇲🇿"), MYANMAR("🇲🇲"), NAMIBIA("🇳🇦"), NEPAL("🇳🇵"),
    NETHERLANDS("🇳🇱"), NEW_ZEALAND("🇳🇿"), NICARAGUA("🇳🇮"), NIGER("🇳🇪"), NIGERIA("🇳🇬"),
    NORTH_KOREA("🇰🇵"), NORTH_MACEDONIA("🇲🇰"), NORWAY("🇳🇴"), OMAN("🇴🇲"), PAKISTAN("🇵🇰"),
    PALESTINE("🇵🇸"), PANAMA("🇵🇦"), PAPUA_NEW_GUINEA("🇵🇬"), PARAGUAY("🇵🇾"), PERU("🇵🇪"),
    PHILIPPINES("🇵🇭"), POLAND("🇵🇱"), PORTUGAL("🇵🇹"), QATAR("🇶🇦"), ROMANIA("🇷🇴"),
    RUSSIA("🇷🇺"), RWANDA("🇷🇼"), SAUDI_ARABIA("🇸🇦"), SENEGAL("🇸🇳"), SERBIA("🇷🇸"),
    SIERRA_LEONE("🇸🇱"), SLOVAKIA("🇸🇰"), SLOVENIA("🇸🇮"), SOMALIA("🇸🇴"), SOUTH_AFRICA("🇿🇦"),
    SOUTH_KOREA("🇰🇷"), SOUTH_SUDAN("🇸🇸"), SPAIN("🇪🇸"), SRI_LANKA("🇱🇰"), SUDAN("🇸🇩"),
    SURINAME("🇸🇷"), SWEDEN("🇸🇪"), SWITZERLAND("🇨🇭"), SYRIA("🇸🇾"), TAIWAN("🇹🇼"),
    TAJIKISTAN("🇹🇯"), TANZANIA("🇹🇿"), THAILAND("🇹🇭"), TOGO("🇹🇬"), TRINIDAD_AND_TOBAGO("🇹🇹"),
    TUNISIA("🇹🇳"), TURKEY("🇹🇷"), TURKMENISTAN("🇹🇲"), UGANDA("🇺🇬"), UKRAINE("🇺🇦"),
    UAE("🇦🇪"), UNITED_KINGDOM("🇬🇧"), URUGUAY("🇺🇾"), USA("🇺🇸"), UZBEKISTAN("🇺🇿"),
    VENEZUELA("🇻🇪"), VIETNAM("🇻🇳"), YEMEN("🇾🇪"), ZAMBIA("🇿🇲"), ZIMBABWE("🇿🇼"),
    UNKNOWN("❓");

    private final String flag;

    Nationalities(String flag) {
        this.flag = flag;
    }

    public String getFlag() {
        return this.flag;
    }

    @Override
    public String toString() {
        return this.flag + " " + this.name().replace("_", " ");
    }
}
