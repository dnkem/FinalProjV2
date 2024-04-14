public class Routine {
    private int id;
    private String item1;
    private String item2;
    private String item3;

    public Routine(String item1,String item2,String item3){
        this.item1 = item1;
        this.item2 = item2;
        this.item3 = item3;
    }
    public void setItems(String i1, String i2, String i3){ this.item1 = i1; this.item2 = i2; this.item3 = i3; }
    public int getId() {return id;}

    public void setId(int id) {this.id = id;}

    public String getItem1() {return item1;}
    public String getItem2() {return item2;}
    public String getItem3() {return item3;}

    @Override
    public String toString() {
        return ("Routine:\nActivity 1: " + item1 + "\t\tActivity 2: " + item2 + "\t\tActivity 3: " + item3);
    }
}
