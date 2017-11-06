public class LoadPerson{
    private String descendant;
    private String personID;
    private String firstName;
    private String lastname;
    private String gender;
    private String father;
    private String mother;
    private String spouse;


    LoadUser(String descendant, String personID, String firstName, String lastname, String gender, String father, String mother, String spouse){
        this.descendant = descendant;
        this.personID = personID;
        this.firstName = firstName;
        this.lastname = lastname;
        this.gender = gender;
        this.father = father;
        this.mother = mother;
        this.spouse = spouse;
    }
}