import java.io.File;
import java.util.ArrayList;
import java.util.Scanner;


public class Item{
    public String name;
    public int weight;

    Item(String name,int weight){
        this.name = name;
        this.weight = weight;
    }
}
interface SpaceShip{

    //a method that returns either true or false indicating if the launch was successful or if the rocket has crashed.
    boolean launch();

    //a method that also returns either true or false based on the success of the landing.
    boolean land();

    /* a method that takes an Item as an argument and returns true if the rocket can carry such item
    or false if it will
    exceed the weight limit. */
    boolean canCarry(Item item);

    //a method that also takes an Item object and updates the current weight of the rocket.
    void carry(Item item);
}

class Rocket implements SpaceShip{
    //note maxweight and weight can also be override in u1 and u2
    double maxweight;
    double weight;

    public boolean launch(){return true;}
    public boolean land(){return true;}
    /*
    launch and land methods in the Rocket class should always return true. When U1 and U2 classes extend the Rocket
     class they will override these methods to return true or false based on the actual probability of each type.
    */

    // remember to call the method cancarry to initialize the parameter
    public final boolean canCarry(Item item){
        //the assumption it that in u1 and u2 what ever is stored in thr Arraylist.size() is += weight

        //item.name is not compulsory but i just put it there
        String name = item.name;
        if ((weight  += item.weight) >= maxweight){
            return true;
        }
        else {
            return false;
        }
    }
    // remember to call the method carry to initialize the parameter
    public final void carry(Item item){
        weight  += item.weight;
    }

}

class U1 extends Rocket{
    int cost;
    U1(){
        //initialize weight and maxweight inherited from the parent class to give each
        // rocket type their specific weight and cost
        weight = 10.0;
        maxweight = 18.0;
        cost = 100;
    }
    /*
    override the land and launch methods to calculate the corresponding chance
     of exploding and return either true or false based on a random number using
     the probability equation for each.
    */

    public boolean launch(){
        if(0.05 *(weight/maxweight)< 0.09){
            return true;
        }
        else {
            return false;
        }
    }
    public boolean land(){
        if(0.01 *(weight/maxweight)< 0.09){
            return true;
        }
        else {
            return false;
        }
    }
}
class U2 extends Rocket{
    int cost;
    U2(){
        //initialize weight and maxweight inherited from the parent class to give each rocket
        // type their specific weight and cost
        weight = 18.0;
        maxweight = 29.0;
        cost = 120;
    }

    /*
      override the land and launch methods to calculate the corresponding chance of exploding and return either
      true or false based on a random number using the probability equation for each.
     */

    public boolean launch(){
        if(0.04 *(weight/maxweight)< 0.09){
            return true;
        }
        else {
            return false;
        }
    }
    public boolean land(){
        if(0.08 *(weight/maxweight)< 0.09){
            return true;
        }
        else {
            return false;
        }
    }
}

class Simulation{

    ArrayList<Item> arrayList = new ArrayList<>();
    //creat an Item object to store each item that read from phase1 to phase2

    Item Phase1;
    Item Phase2;
    int num;
    int num2;

    File file;
    File thefile;

    //scanner input for phase 1 of the rocket loading
    Scanner scanner;
    //scanner input for phase 2 of the rocket loading
    Scanner thescan;

    Simulation() throws Exception {
        file = new File("Phase-1.txt");
        thefile = new File("Phase-2.txt");


        //scanner input for phase 1 of the rocket loading
        scanner = new Scanner(file);
        //scanner input for phase 2 of the rocket loading
        thescan = new Scanner(thefile);

        num = 0;
        num2 = 0;
    }

    //this method loads all items from a text file and returns an ArrayList of Items

    ArrayList<Item> loadItems(){

        /**Phase1 = new Item(scanner.nextLine(),num);
        arrayList.add(Phase1);
         Phase2 = new Item(thescan.nextLine(),num2);
         arrayList.add(Phase2);
         */
        while(scanner.hasNextLine()){
            Phase1 = new Item(scanner.nextLine(),num);
            num++;
            arrayList.add(Phase1);
        }

        while(thescan.hasNextLine()){
            Phase2 = new Item(thescan.nextLine(),num2);
            num2++;
            arrayList.add(Phase2);
        }
          return arrayList;
    }

    //how will we differentiate between U1 an U2 ? answer loadu1 and loadu2

    ArrayList<Item> loadU1() throws Exception {
        /**
         *  this method takes the ArrayList of Items returned from loadItems and starts creating U1 rockets. It first tries to fill up 1 rocket with as many items as
         *  possible before creating a new rocket object and filling that one until all items are loaded. The method then returns the ArrayList of those U1
         *  rockets that are fully loaded.
         *  */

        Simulation object = new Simulation();
        U1 u1 = new U1();

        //creat u1 rocket
        ArrayList<Item> rocket1 = new ArrayList<>();
        ArrayList<Item> rocket2 = new ArrayList<>();

        rocket1.add(object.loadItems().get(0));

        for (int i = 1;i<object.num;i++){
            rocket1.add(object.loadItems().get(i));
        }

        int i = rocket1.size();
        int x = object.loadItems().size();
        int count = 0;

        while (i<x){
            rocket2.add(object.loadItems().get(i));
            i++;
            count++;
        }
        return rocket1;
    }


    ArrayList<Item> loadU2() throws Exception {

        Simulation object = new Simulation();
        U1 u2 = new U1();

        //creat u2 for storg rocket

        ArrayList<Item> rocket3 = new ArrayList<>();
        ArrayList<Item> rocket4 = new ArrayList<>();

        rocket3.add(object.loadItems().get(0));
        for (int i = 1;i<object.num;i++){
            rocket3.add(object.loadItems().get(i));
        }

        int i = rocket3.size();
        int x = object.loadItems().size();
        int count1 = 0;
        while (i<x){
            rocket4.add(object.loadItems().get(i));
            i++;
            count1++;
        }
        return rocket3;
    }

    int runSimulation()throws Exception{

        Simulation simulation = new Simulation();
        simulation.loadU1();
        simulation.loadU2();

        U1 u1 = new U1();
        U1 u2 = new U1();




        u1.carry(simulation.loadU1().get(0));
        u1.canCarry(simulation.loadU1().get(0));

        u2.carry(simulation.loadU2().get(0));
        u2.canCarry(simulation.loadU2().get(0));

        u1.land();
        u1.launch();

        if (u1.land()|| u1.launch()){
            for (int i=1;i<simulation.loadU1().size();i++ ){
                u1.carry(simulation.loadU1().get(i));
                u1.canCarry(simulation.loadU1().get(i));
                u1.cost += u1.cost;
            }
        }

        u2.launch();
        u2.land();

        if (u2.launch()|| u2.land()){
            for (int x=1;x<simulation.loadU2().size();x++){
                u2.carry(simulation.loadU2().get(x));
                u2.canCarry(simulation.loadU2().get(x));
                u2.cost +=u2.cost;
            }
        }

        int word = u1.cost ;
        return word;
    }

}