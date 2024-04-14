public class Member {
    private String firstName;
    private  String lastName;
    private String email;
    private String password;
    int id = 0;
    Stats stats;
    Goals goals;
    Routine routine;

    // ctors
    public Member(String firstName, String lastName, String email, String password) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.password = password;
        this.routine = new Routine("", "", "");
        this.stats = new Stats(0,0,0,0,0);
        this.goals = new Goals(0,0,0);
    }

    public void setInfo(String firstName, String lastName, String email, String password) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.password = password;
    }

//    public void setRoutine(String i1, String i2, String i3) {
//        this.routine.setId(this.getId());
//        this.routine.setItems(i1, i2, i3);
//    }
//    public void setStats(int weight, int height, int runSpeed, int pullUpsPb, float heart_rate) {
//        this.stats.setId(this.getId());
//        this.stats.setStats(weight, height, runSpeed, pullUpsPb, heart_rate);
//    }
    public void setId(int id) {this.id = id;}
    public String getFirstName() {return firstName;}
    public String getLastName() {return lastName;}
    public String getEmail() {return email;}
    public String getPassword() {return password;}
    public int getId() {return id;}
    public void setFirstName(String firstName) {this.firstName = firstName;}
    public void setLastName(String lastName) {this.lastName = lastName;}
    public void setEmail(String email) {this.email = email;}
    public void setPassword(String password) {this.password = password;}
    @Override public String toString(){ return ("Info:\nMember Id: " + this.getId() + "\nFirst Name: " + this.getFirstName() + " \nLast Name: " + this.getLastName() + " \nEmail:" + this.getEmail() + " \nPass:" + this.getPassword());}
}
