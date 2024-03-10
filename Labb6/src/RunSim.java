import general.*;
import specific.StoreState;

public class RunSim {

    public static void main(String[] args) {
        // StoreState storeState = new StoreState(1, 1234, 10f, 2, 5, 2, 3, 0.5f, 1);
        StoreState storeState = new StoreState(3.0, 13, 8f, 2, 7, 0.35f, 0.6f, 0.6f, 0.9f);

        Simulator simulator = new Simulator(storeState);
        simulator.start(); // Start the simulation
    }


}
