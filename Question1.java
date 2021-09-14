import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;
//QUESTION 1, USE WITH BUS AND TOWN
public class Question1 {

    public static void main(String[] args) {

        Scanner in = new Scanner(System.in);
        System.out.println("Input the number of towns");
        int numberOfTowns = in.nextInt();
        ArrayList<Town> towns = new ArrayList<Town>();

        for (int i = 0; i < numberOfTowns; i++) {
            System.out.println("Input town name");
            Town a = new Town(in.next());
            towns.add(i, a);
        }


        System.out.println("Input the number of buses");
        int numberOfBuses = in.nextInt();
        Bus[] buses = new Bus[numberOfBuses];

        for (int i = 0; i < numberOfBuses; i++) {
            System.out.println("Input the two towns bus goes to");
            String first = in.next();
            String second = in.next();
            Town A = new Town("");
            Town B = new Town("");
            for (int j = 0; j < numberOfTowns; j++) {
                Town a = towns.get(j);
                if (a.getTownName().equals(first)) {
                    A = a;
                    break;
                }
            }
            for (int j = 0; j < numberOfTowns; j++) {
                Town a = towns.get(j);
                if (a.getTownName().equals(second)) {
                    B = a;
                    break;
                }
            }
            Bus b = new Bus(A, B);
            System.out.println("Input the colour");
            b.setBusColour(in.next());
            buses[i] = b;
        }

        System.out.println("Input the starting point of Ryan");
        String start = in.next();
        System.out.println("Input the number of trips Ryan took");
        int numberOfTrips = in.nextInt();
        String[] wayByColour = new String[numberOfTrips];

        for (int i = 0; i < numberOfTrips; i++) {
            System.out.println("Input the colour Ryan took");
            wayByColour[i] = in.next();
        }

        for (int j = 0; j < numberOfTowns; j++) {
            if (towns.get(0).getTownName().equals(start)) break;
            Town a = towns.get(j);
            if (a.getTownName().equals(start)) {
                Collections.swap(towns, 0, j);
            }
        }

        double[][] array = new double[numberOfTowns + 1][numberOfTrips + 1];
        for (int i = 0; i < numberOfTowns + 1; i++) {
            for (int j = 0; j < numberOfTrips + 1; j++) {
                array[i][j] = 0;
            }
        }

        array[1][0] = 1;


        Town current;
        Town[] currentsList = new Town[numberOfTowns];

        for (int i = 1; i < numberOfTrips + 1; i++) {
            String colour = wayByColour[i - 1];
            int tally = 0;
            for (int z = 1; z < numberOfTowns + 1; z++) {
                if (array[z][i - 1] != 0) {
                    currentsList[tally] = towns.get(z-1);
                    tally++;
                }
            }

            double[] prob = new double[numberOfTowns+1];
            for(int k = 0; k < numberOfTowns+1; k++) prob[k]=0;

            for (int x = 0; x < tally; x++) {
                int[] indexCounter = new int[numberOfTowns+1];
                double sum=0;
                current = currentsList[x];
                double previousProb = array[towns.indexOf(current)+1][i-1];
                for (int j = 1; j < numberOfBuses + 1; j++) {
                    Bus b = buses[j - 1];
                    if (b.getBusColour().equals(colour) && (b.getA() == current || b.getB() == current)) {
                        if (b.getA() == current) {
                            int index = towns.indexOf(b.getB());
                            array[index + 1][i]=1;
                            sum++;
                            indexCounter[index+1]=1;
                        } else {
                            int index = towns.indexOf(b.getA());
                            array[index + 1][i]=1;
                            sum++;
                            indexCounter[index+1]=1;
                        }
                    }
                }

                for(int y = 0; y < numberOfTowns + 1; y++) {
                    if(indexCounter[y]!=0)prob[y] += (1/sum)*previousProb;
                }
            }
            for(int y = 0; y < numberOfTowns + 1; y++)array[y][i] = prob[y];
        }


            for (int i = 0; i < numberOfTowns + 1; i++) {
                for (int j = 0; j < numberOfTrips + 1; j++) {
                    System.out.print(array[i][j] + "|");
                }
                System.out.println();
            }

            for(int i = 1; i < numberOfTowns+1; i++){
                System.out.println("The probability Ryan is in Town " + towns.get(i-1).getTownName() + " is " + array[i][numberOfTrips]);
            }


    }
}
