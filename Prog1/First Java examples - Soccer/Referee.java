
public class Referee extends Person {

    private License license;

    public Referee() {
        super.setName("Unknown");
        super.setSurname("Unknown");
        super.setNationality("UNKNOWN");
        this.license = License.UNKNOWN;
    }

    public Referee(String name, String surname, String nationality, License license) {
        super(name, surname, nationality);
        this.license = license;
    }

    public License getLicense() {
        return this.license;
    }

    public void setLicense(License license) {
        this.license = license;
    }

    @Override
    public String toString() {
        return super.toString() + "";
    }

}
enum License {
    EURO1,
    EURO2,
    EURO3,
    UNKNOWN
}
