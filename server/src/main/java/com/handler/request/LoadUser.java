public class LoadUser{
    private String userName;
    private String password;
    private String email;
    private String firstName;
    private String lastname;
    private String gender;
    private String personID;

    LoadUser(String userName, String password, String email, String firstName, String lastname, String gender, String personID){
        this.userName = userName;
        this.password = password;
        this.email = email;
        this.firstName = firstName;
        this.lastname = lastname;
        this.gender = gender;
        this.personID = personID;
    }
}