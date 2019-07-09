
public class Space {
    public static void main(String[] arg)throws Exception{

        String wordline;
        String wordline1;
        //the count help use to determine the number of times the gave an input to the arraylist
        int countphase1 = 1;
        int countphase2 = 1;

        Simulation sim = new Simulation();
        System.out.println(sim.loadItems().size());

        System.out.println(sim.loadU2().get(3).name);
        System.out.println(sim.loadU1().get(0).weight);
        System.out.println(sim.runSimulation());
       

    }
}
