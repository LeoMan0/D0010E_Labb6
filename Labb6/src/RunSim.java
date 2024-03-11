import general.*;
import specific.StoreState;

public class RunSim {


    private Simulator simulator;


    public static void main(String[] args) {
        // StoreState storeState = new StoreState(1, 1234, 10f, 2, 5, 2, 3, 0.5f, 1);


        RunSim sim = new RunSim(1, 1234, 10f, 2, 5,
                2, 3, 0.5f, 1, false);
        System.out.println(sim.runSimAndGetMissedCustomers());

        RunSim sim1 = new RunSim(1, 1234, 10f, 2, 5,
                2, 3, 0.5f, 1, true);
        sim1.run();


    }

    public RunSim(double lambda, int seed, float closeStoreTime, int checkoutsOpen, int maxCapacityInStore, double minPayTime,
                  double maxPayTime, double minPickTime, double maxPickTime, boolean print) {

        this.simulator = new Simulator(lambda, seed, closeStoreTime, checkoutsOpen, maxCapacityInStore, minPayTime, maxPayTime, minPickTime, maxPickTime, print);
    }
    

    public void run() {
        this.simulator.start();
    }


    public int runSimAndGetMissedCustomers() {
        this.simulator.start();
        return simulator.getMissedCustomers();
    }


}
