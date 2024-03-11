import general.*;
import specific.StoreState;

public class RunSim {

    public static void main(String[] args) {
        // StoreState storeState = new StoreState(1, 1234, 10f, 2, 5, 2, 3, 0.5f, 1);


        Simulator simulator = new Simulator(1, 1234, 10f, 2, 5, 2, 3, 0.5f, 1, true);
        simulator.start(); // Start the simulation
    }


}
