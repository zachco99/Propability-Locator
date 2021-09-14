public class Bus {

    public Town A;
    public Town B;
    public String busColour;

    public Bus(Town a, Town b){
        this.A = a;
        this.B = b;
    }

    public void setBusColour(String colour){this.busColour=colour;}
    public String getBusColour() { return busColour;}

    public String getTowns(){return A.getTownName()+ " " + B.getTownName();}
    public Town getA(){return A;}
    public Town getB() { return B; }

    public String toString(){return getTowns()+ " " +getBusColour();}
}
