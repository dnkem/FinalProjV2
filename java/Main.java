import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Scanner;
//include Member.java;
import java.sql.PreparedStatement;

public class Main {

    private static State currentState = State.userSelection;
    private static State previousState = currentState;
    private static Member currentMember;

    enum State {
        endPoint, userSelection, memberSelection, memberSignIn, memberRegister, signedInMember, trainerSignIn, trainerSelection, trainerScheduleView, trainerMemberProfileView, adminSignIn, adminEquipmentMonitor, adminClassSchedule, editInfo
    }

    static public void resetCurrentMember() {
        currentMember.setInfo("", "", "", "");
        currentMember.setId(0);
        currentMember.stats.setStats(0, 0, 0, 0, 0);
        currentMember.stats.setId(0);
        currentMember.goals.setGoals(0, 0, 0);
        currentMember.goals.setId(0);
        currentMember.routine.setItems("", "", "");
        currentMember.routine.setId(0);
    }

    static public void promptUser() {
        System.out.println("Enter a number for a user choice: \n1. Member\n2. Trainer \n3. Admin\n");
        Scanner scanner = new Scanner(System.in);
        int user = scanner.nextInt();
        if (user == 1) {
            updateState(State.memberSelection);
        } else if (user == 2) {
            updateState(State.trainerSelection);
        }
    }

    // Member Options
    static public void promptMember() {
        // ask user member selection
        System.out.println("Enter a number for Member sign/registration: \n1. Sign In \n2. Register\n");
        Scanner scanner = new Scanner(System.in);
        int choice = scanner.nextInt();

        if (choice == 1) {
            updateState(State.memberSignIn);
        } else if (choice == 2) {
            updateState(State.memberRegister);
        } else {
            System.out.println("Please enter a valid choice\n");
            updateState(State.endPoint);
        }
    }

    static public void registerMember() {
        // Get Member registration info
        Scanner scanner = new Scanner(System.in);
        System.out.println("**Enter Info** \nEnter your first name: ");
        String firstName = scanner.nextLine();
        System.out.println("Enter your last name: ");
        String lastName = scanner.nextLine();
        System.out.println("Enter your email: ");
        String email = scanner.nextLine();
        System.out.println("Enter your password: ");
        String password = scanner.nextLine();

        // stats
        System.out.println("**Now Enter your STATS**");
        System.out.println("Enter your current weight in kg: ");
        int weight = scanner.nextInt();
        System.out.println("Enter your current height in cm: ");
        int height = scanner.nextInt();
        System.out.println("Enter your current resting heart rate: ");
        float restingHeartRate = scanner.nextFloat();
        System.out.println("Enter your current pull ups personal best number (if none enter 0): ");
        int pullUps = scanner.nextInt();
        System.out.println("Enter your current person best run speed in km (ex: 1): ");
        int runSpeed = scanner.nextInt();

        // goals
        System.out.println("**Now Enter your GOALS**");
        System.out.println("Enter your GOAL WEIGHT: ");
        int goalWeight = scanner.nextInt();
        System.out.println("Enter your GOAL RUN SPEED: ");
        int goalRunSpeed = scanner.nextInt();
        System.out.println("Enter your GOAL PULL UPS NUMBER: ");
        int goalPullUps = scanner.nextInt();
        // routine
        System.out.println("**Create Your Routine**");
        System.out.println("Enter activity 1: ");
        String activity1 = scanner.nextLine();
        scanner.nextLine();
        System.out.println("Enter activity 2: ");
        String activity2 = scanner.nextLine();
        System.out.println("Enter activity 3: ");
        String activity3 = scanner.nextLine();
        // put info in currentMember
        currentMember = new Member(firstName, lastName, email, password);
        currentMember.stats.setStats(weight, height, runSpeed, pullUps, restingHeartRate);
        currentMember.routine.setItems(activity1, activity2, activity3);
        currentMember.goals.setGoals(goalWeight, goalRunSpeed, goalPullUps);

    }

    static public void signInMember() {
        if (currentState != State.memberRegister) {
            Scanner scanner = new Scanner(System.in);
            System.out.println("Sign in: \nEnter your email: ");
            String email = scanner.nextLine();
            System.out.println("Enter your password: ");
            String password = scanner.nextLine();
            // use currentMember as value to check
            currentMember = new Member("", "", email, password);
        }
    }

    static ArrayList<Object> editInfo() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Select what you would like to edit: ");
        System.out.println("1. First Name\n2. Last Name\n3. Email\n4. Password\n5. Weight\n6. Height\n7. HeartRate\n8. PullUps\n9. RunSpeed\n10. weight_kg \n11. pull_ups_pb\n12. run_speed");

        int choice = scanner.nextInt();
        String option = "";
        int flag = 9; // 9 for default 0 for number 1 for string 2 for float 4 for goals
        switch (choice) {
            case 1:
                option = "first_name";
                flag = 1;
                break;
            case 2:
                option = "last_name";
                flag = 1;
                break;
            case 3:
                option = "email";
                flag = 1;
                break;
            case 4:
                option = "password_key";
                flag = 1;
                break;
            case 5:
                option = "weight_kg";
                flag = 0;
                break;
            case 6:
                option = "height_cm";
                flag = 0;
                break;
            case 7:
                option = "heart_rate";
                flag = 2;
                break;
            case 8:
                option = "pull_ups";
                flag = 0;
                break;
            case 9:
                option = "run_speed";
                flag = 0;
                break;
            case 10:
                option = "weight_kg";
                flag = 4;
                break;
            case 11:
                option = "pull_ups_pb";
                flag = 4;
                break;
            case 12:
                option = "run_speed";
                flag = 4;
                break;
        }
        System.out.println(option);
        if (option != "" && flag != 9) {
            System.out.println("What would you like to change it to? ");
            scanner.nextLine();
            String choice2 = scanner.nextLine();
            System.out.println(choice2);
            ArrayList<Object> arr = new ArrayList<>();
            arr.add(option);
            arr.add(choice2);
            arr.add(flag);
            updateState(State.editInfo);
            return arr;
        } else {
            updateState(State.endPoint);
            return null;
        }


    }

    static void signedInMemberPrompt() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("**View Options**");
        System.out.println("1. Edit Info");
        int choice = scanner.nextInt();
        if (choice == 1) {
            updateState(State.editInfo);
        }

    }

    // member dashboard
    static public void memberDashBoard() {
        System.out.println("-----------------------MEMBER DASHBOARD--------------------");
        System.out.println(currentMember.toString() + "\n");
        System.out.println(currentMember.stats.toString());
        System.out.println(currentMember.goals.toString());
        System.out.println(currentMember.routine.toString());
    }

    static void trainerChoice() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter a number: \n1. Schedule\n2. Member Profile");
        int choice = scanner.nextInt();
        if (choice == 2) {
            updateState(State.trainerMemberProfileView);
        } else if (choice == 1) {
            updateState(State.trainerScheduleView);
        }

    }

    static String searchMember() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter a member's first name: ");
        String firstName = scanner.nextLine();
        return firstName;
    }


    public static void updateState(State state) {
        previousState = currentState;
        currentState = state;
        switch (state) {
            case userSelection:
                System.out.println("User selection");
                promptUser();
                break;

            case memberSelection:
                System.out.println("Member selection");
                promptMember();
                break;

            case memberSignIn:
                System.out.println("Member Sign in");
                signInMember();
                // go to profile display
                break;

            case memberRegister:
                System.out.println("Member Registration");
                registerMember();
                signInMember();
                break;
            case signedInMember:
                System.out.println("Member is signed in");
                memberDashBoard();
                signedInMemberPrompt();
                break;
            case editInfo:
                break;
            case endPoint:
                System.out.println("This is the end of this branch. Please Try Again.");
                break;
            case trainerSelection:
                trainerChoice();
                break;
            case trainerMemberProfileView:
                break;
            case trainerScheduleView:
                break;


        }
//        return null;
    }


    public static void main(String[] args) {
        // JDBC & Database credentials
        String url = "jdbc:postgresql://localhost:5432/postgres";
        String user = "postgres";
        String password = "bad123";

        try { // Load PostgreSQL JDBC Driver
            Class.forName("org.postgresql.Driver");
            // Connect to the database
            Connection conn = DriverManager.getConnection(url, user, password);

            // default page
            updateState(State.userSelection);
            if (currentState == State.memberRegister) {
                Statement reg_statement = conn.createStatement();

                PreparedStatement st = conn.prepareStatement("INSERT INTO members (first_name, last_name, email, password_key) VALUES (?, ?, ?, ?)");

                //add to tables
                reg_statement.executeUpdate("INSERT INTO members (first_name, last_name, email, password_key) VALUES ('" + currentMember.getFirstName() + "', '" + currentMember.getLastName() + "', '" + currentMember.getEmail() + "', '" + currentMember.getPassword() + "')");
                // assign id
                String reg_query = "SELECT * FROM members WHERE email = '" + currentMember.getEmail() + "'" + " AND password_key = '" + currentMember.getPassword() + "'";
                ResultSet reg_resultSet = reg_statement.executeQuery(reg_query);
                while (reg_resultSet.next()) {
                    currentMember.setId(Integer.parseInt(reg_resultSet.getString("member_id")));
                }
                System.out.println("ID:" + currentMember.getId());
                updateState(State.signedInMember);

                reg_statement.executeUpdate("INSERT INTO stats (member_id, weight_kg, height_cm, heart_rate, pull_ups_pb, run_speed) VALUES ('" + currentMember.getId() + "', '" + currentMember.stats.getWeight() + "', '" + currentMember.stats.getHeight() + "', '" + currentMember.stats.getHeartRate() + "', '" + currentMember.stats.getPullUpsPb() + "', '" + currentMember.stats.getRunSpeed() + "')");
                reg_statement.executeUpdate("INSERT INTO goals (member_id, weight_kg, pull_ups_pb, run_speed) VALUES ('" + currentMember.getId() + "', '" + currentMember.goals.getGoalWeight() + "', '" + currentMember.goals.getGoalPullUps() + "', '" + currentMember.goals.getGoalPullUps() + "')");
                reg_statement.executeUpdate("INSERT INTO routine (member_id, item1, item2, item3) VALUES ('" + currentMember.getId() + "', '" + currentMember.routine.getItem1() + "', '" + currentMember.routine.getItem2() + "', '" + currentMember.routine.getItem3() + "')");
            }
//
            if (currentState == State.memberSignIn) {
                String query = "SELECT * FROM members WHERE email = '" + currentMember.getEmail() + "'" + " AND password_key = '" + currentMember.getPassword() + "'";
                Statement statement = conn.createStatement();
                ResultSet resultSet = statement.executeQuery(query);
                //check if null
                if (!resultSet.next()) {
                    // if the info doesn't exist
                    System.out.println("The email or password is incorrect, please Member Selection try again");
                    resetCurrentMember();
                    System.out.println("**Please try again**");
                    updateState(State.endPoint);
                    return;
                }
                resultSet = statement.executeQuery(query);
                System.out.println("here");
                while (resultSet.next()) {
                    // set member id
                    System.out.println("in loop");
                    currentMember.setId(Integer.parseInt(resultSet.getString("member_id")));

                    // assign the other values to current member
                    currentMember.setFirstName(resultSet.getString("first_name"));
                    currentMember.setLastName(resultSet.getString("last_name"));
                    System.out.println("Hi " + currentMember.getFirstName() + " " + currentMember.getLastName() + " you're signed in!");
                    // get stats
                    String query4 = "Select * from stats where member_id = " + currentMember.getId();
                    Statement statement4 = conn.createStatement();
                    ResultSet resultSet4 = statement4.executeQuery(query4);

                    // assign stats values
                    while (resultSet4.next()) {
                        currentMember.stats.setStats(Integer.parseInt((resultSet4.getString("weight_kg"))), Integer.parseInt((resultSet4.getString("height_cm"))), Integer.parseInt((resultSet4.getString("run_speed"))), Integer.parseInt((resultSet4.getString("pull_ups_pb"))), Float.parseFloat((resultSet4.getString("heart_rate"))));
                        // get goals
                        String query3 = "Select * from goals where member_id = " + currentMember.getId();
                        Statement statement3 = conn.createStatement();
                        ResultSet resultSet3 = statement3.executeQuery(query3);
                        // assign goals values
                        while (resultSet3.next()) {
                            currentMember.goals.setGoals(Integer.parseInt((resultSet3.getString("weight_kg"))), Integer.parseInt((resultSet3.getString("run_speed"))), Integer.parseInt((resultSet3.getString("pull_ups_pb"))));
                        }
                        // get routine
                        String query2 = "Select * from routine where member_id = " + currentMember.getId();
                        Statement statement2 = conn.createStatement();
                        ResultSet resultSet2 = statement2.executeQuery(query2);
                        // prints routine
                        while (resultSet2.next()) {
                            currentMember.routine.setItems(resultSet2.getString("item1"), resultSet2.getString("item2"), resultSet2.getString("item3"));
                        }

                        updateState(State.signedInMember);

                    }

                }
            }

            if (currentState == State.editInfo) {
                ArrayList<Object> arr = editInfo();
                if (Integer.parseInt(arr.get(2).toString()) == 1) {
                    System.out.println("in 1");
                    PreparedStatement st2 = conn.prepareStatement("UPDATE members set " + arr.get(0) + "=? where email=?");
                    st2.setString(1, arr.get(1).toString());
                    st2.setString(2, currentMember.getEmail());
                    int updateStatus = st2.executeUpdate();
                } else if (Integer.parseInt(arr.get(2).toString()) == 0) {
                    System.out.println("in 0");
                    int item = Integer.parseInt(arr.get(1).toString());
                    PreparedStatement st = conn.prepareStatement("UPDATE stats set " + arr.get(0) + "=? where member_id=" + currentMember.getId());
                    st.setInt(1, item);
                    int updateStatus = st.executeUpdate();
                } else if (Integer.parseInt(arr.get(2).toString()) == 2) {
                    System.out.println("in 2");
                    float item = Float.parseFloat(arr.get(1).toString());
                    PreparedStatement st = conn.prepareStatement("UPDATE stats set " + arr.get(0) + "=? where member_id=" + currentMember.getId());
                    st.setFloat(1, item);
                    int updateStatus = st.executeUpdate();
                } else if (Integer.parseInt(arr.get(2).toString()) == 4) {
                    System.out.println("in 4");
                    int item = Integer.parseInt(arr.get(1).toString());
                    PreparedStatement st = conn.prepareStatement("UPDATE goals set " + arr.get(0) + "=? where member_id=" + currentMember.getId());
                    st.setInt(1, item);
                    int updateStatus = st.executeUpdate();
                }
                updateState(State.endPoint);
            }

            if (currentState == State.trainerMemberProfileView) {
                String name = searchMember();
                Statement statement = conn.createStatement();
                String query = "SELECT * FROM members WHERE first_name = '" + name + "'";
                ResultSet resultSet = statement.executeQuery(query);
                ArrayList<String> id = new ArrayList<>();
                while (resultSet.next()) {
                    id.add(resultSet.getString("member_id"));
                    System.out.println("\nMember ID: " + resultSet.getString("member_id"));
                    System.out.println("First Name: " + resultSet.getString("first_name"));
                    System.out.println("Last Name: " + resultSet.getString("last_name"));
                    System.out.println("Email: " + resultSet.getString("email"));
                }
                System.out.println(id);
                for (int i = 0; i < id.size(); i++) {
                    String query2 = "SELECT * FROM stats WHERE member_id = '" + id.get(i) + "'";
                    ResultSet resultSet2 = statement.executeQuery(query2);
                    while (resultSet2.next()) {
                        System.out.println("\nMember ID: " + resultSet2.getString("member_id"));
                        System.out.println("Current Weight: " + resultSet2.getString("weight_kg"));
                        System.out.println("Current Height: " + resultSet2.getString("height_cm"));
                        System.out.println("Current Run Speed: " + resultSet2.getString("run_speed"));
                        System.out.println("Current Pull Ups: " + resultSet2.getString("pull_ups_pb"));
                        System.out.println("Current HeartRate: " + resultSet2.getString("heart_rate"));
                    }
                }
                updateState(State.endPoint);


            }

            if (currentState == State.trainerScheduleView){
                System.out.println("Putting in Schedule");
                updateState(State.endPoint);
            }


            conn.close();

        } catch (Exception e) {
            System.out.println("Exception:" + e);
        }
    }
}
