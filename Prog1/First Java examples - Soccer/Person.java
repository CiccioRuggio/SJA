
public class Person {

    private String name;
    private String surname;
    private Nationalities nationality;

    public Person() {
        this.name = "Unknown";
        this.surname = "Unknown";
        this.nationality = Nationalities.UNKNOWN;
    }

    public Person(String name, String surname, String nationality) {
        this.name = name;
        this.surname = surname;

        setNationality(nationality);
    }

    public void setName(String var1) {
        this.name = var1;
    }

    public String getName() {
        return this.name;
    }

    public void setSurname(String var1) {
        this.surname = var1;
    }

    public String getSurname() {
        return this.surname;
    }

    public Nationalities getNationality() {
        return this.nationality;
    }

    public void setNationality(String nationality) {
        if (nationality == null || nationality.isEmpty()) {
            this.nationality = Nationalities.UNKNOWN;
        } else {
            try {
                this.nationality = Nationalities.valueOf(nationality.toUpperCase());
            } catch (IllegalArgumentException e) {
                this. nationality = Nationalities.UNKNOWN;
            }
        }
        // this.nationality = nationality;
    }

    
    @Override
    public String toString() {
        return "\nName: " + getName() + "\nSurname: " + getSurname() + "\nNationality: " + getNationality().getFlag();
    }
    
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