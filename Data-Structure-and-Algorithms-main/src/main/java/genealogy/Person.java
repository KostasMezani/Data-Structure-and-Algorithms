package genealogy;

public class Person {
    private final String id;
    private final String name;
    private final Gender gender;
    private final String fatherId;
    private final String motherId;
    private final String spouseId;

    public Person(String id, String name, Gender gender, String fatherId, String motherId, String spouseId){
        this.id = id;
        this.name = name;
        this.gender = gender;
        this.fatherId = fatherId;
        this.motherId = motherId;
        this.spouseId = spouseId;
    }

    public String getId() {
        return id;
    }

    public String getName(){
        return name;
    }

    public Gender getGender(){
        return gender;
    }

    public String getFatherId(){
        return fatherId;
    }

    public String getMotherId(){
        return motherId;
    }

    public String getSpouseId(){
        return spouseId;
    }
}
