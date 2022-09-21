package belajar.android.safaripetcare;

public class GuestDetail {

    private String petName;
    private String petSpecies;
    private String petGender;
    private String petOwner;
    private String contactOwner;
    private String petTreatment;
    private String key;

    public GuestDetail(String petName, String petSpecies, String petGender, String petOwner, String contactOwner, String petTreatment) {
        this.petName = petName;
        this.petSpecies = petSpecies;
        this.petGender = petGender;
        this.petOwner = petOwner;
        this.contactOwner = contactOwner;
        this.petTreatment = petTreatment;
    }

    public GuestDetail() {
    }

    public String getPetName() {
        return petName;
    }

    public void setPetName(String petName) {
        this.petName = petName;
    }

    public String getPetSpecies() {
        return petSpecies;
    }

    public void setPetSpecies(String petSpecies) {
        this.petSpecies = petSpecies;
    }

    public String getPetGender() {
        return petGender;
    }

    public void setPetGender(String petGender) {
        this.petGender = petGender;
    }

    public String getPetOwner() {
        return petOwner;
    }

    public void setPetOwner(String petOwner) {
        this.petOwner = petOwner;
    }

    public String getContactOwner() {
        return contactOwner;
    }

    public void setContactOwner(String contactOwner) {
        this.contactOwner = contactOwner;
    }

    public String getPetTreatment() {
        return petTreatment;
    }

    public void setPetTreatment(String petTreatment) {
        this.petTreatment = petTreatment;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }
}
