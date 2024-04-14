public class Stats {
    private int id;
    private  int weight;
    private int height;
    private int runSpeed;
    private int pullUpsPb;
    private float heart_rate;

    public Stats(int weight, int height, int runSpeed, int pullUpsPb, float heart_rate) {
        this.weight = weight;
        this.height = height;
        this.runSpeed = runSpeed;
        this.pullUpsPb = pullUpsPb;
        this.heart_rate = heart_rate;
    }
    public void setStats(int weight, int height, int runSpeed, int pullUpsPb, float heart_rate) {
        this.weight = weight;
        this.height = height;
        this.runSpeed = runSpeed;
        this.pullUpsPb = pullUpsPb;
        this.heart_rate = heart_rate;
    }

    public int getId() {return id;}
    public int getWeight() {return weight;}
    public int getHeight() {return height;}
    public int getRunSpeed() {return runSpeed;}
    public int getPullUpsPb() {return pullUpsPb;}
    public float getHeartRate() {return heart_rate;}
    public void setId(int id) {this.id = id;}
    public void setWeight(int weight) {this.weight = weight;}
    public void setHeight(int height) {this.height = height;}
    public void setRunSpeed(int runSpeed) {this.runSpeed = runSpeed;}
    public void setPullUpsPb(int pullUpsPb) {this.pullUpsPb = pullUpsPb;}
    public void setHeart_rate(float heart_rate) {this.heart_rate = heart_rate;}
    @Override
    public String toString() {
        return ("Stats:\nWeight: " + this.getWeight() + "\t\tHeight: " + this.getHeight() + "\t\tHeart Rate: " + this.getHeartRate() + "\t\tMax Pulls Ups: " + this.getPullUpsPb() + "\t\tMax Run Speed: " + this.getRunSpeed());
    }
}
