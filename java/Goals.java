public class Goals {
    private int id, goalWeight, goalRunSpeed, goalPullUps;

    public Goals(int goalWeight, int goalRunSpeed, int goalPullUps) {
        this.goalWeight = goalWeight;
        this.goalRunSpeed = goalRunSpeed;
        this.goalPullUps = goalPullUps;
    }
    public void setGoals(int goalWeight, int goalRunSpeed, int goalPullUps) {
        this.goalWeight = goalWeight;
        this.goalRunSpeed = goalRunSpeed;
        this.goalPullUps = goalPullUps;
    }
    public int getId() {return id;}
    public int getGoalWeight() {return goalWeight;}
    public int getGoalRunSpeed() {return goalRunSpeed;}
    public int getGoalPullUps() {return goalPullUps;}
    public void setId(int id) {this.id = id;}
    public void setGoalWeight(int goalWeight) {this.goalWeight = goalWeight;}
    public void setGoalRunSpeed(int goalRunSpeed) {this.goalRunSpeed = goalRunSpeed;}
    public void setGoalPullUps(int goalPullUps) {this.goalPullUps = goalPullUps;}
    @Override
    public String toString() {
        return ("Goals:\nGoal Weight: " + this.getGoalWeight() + "\t\tGoal Max Pulls Ups: " + this.getGoalPullUps() + "\t\tGoal Max Run Speed: " + this.getGoalRunSpeed());
    }


}
