

public class Optimize implements K {
    private int seed = SEED;
    private int maxCapacityInStore = M;
    private double lambda = L;
    private double minPickTime = LOW_COLLECTION_TIME;
    private double maxPickTime = HIGH_COLLECTION_TIME;
    private double minPayTime = LOW_PAYMENT_TIME;
    private double maxPayTime = HIGH_PAYMENT_TIME;
    private float closeStoreTime = (float) END_TIME;


//    RunSim sim = new RunSim(1, 1234, 10f, 2, 5,
//            2, 3, 0.5f, 1, false);


    public int runOnce(int checkoutsOpen, int setSeed) {

        RunSim instanceOfRunSim = new RunSim(lambda, setSeed, closeStoreTime, checkoutsOpen, maxCapacityInStore, minPayTime, maxPayTime, minPickTime, maxPickTime, false);

        return instanceOfRunSim.runSimAndGetMissedCustomers();
    }

    
    public int optimalCheckOutsSetSeed(int setSeed) {
        int minMissedCustomers = Integer.MAX_VALUE;
        int optimalCheckouts = 1; // Start with 1 checkout as a baseline
        int checkoutsOpen = 1;
        boolean improvement = true;

        while (improvement) {
            int missedCustomers = runOnce(checkoutsOpen, setSeed);

            if (missedCustomers < minMissedCustomers) {
                minMissedCustomers = missedCustomers;
                optimalCheckouts = checkoutsOpen;
                checkoutsOpen++; // Try adding another checkout to see if it improves
            } else {
                improvement = false; // No improvement, so exit the loop
            }
        }
        return optimalCheckouts;
    }


    public static void main(String[] args) {
        Optimize optimize = new Optimize();

        int test = optimize.optimalCheckOutsSetSeed(optimize.seed);
        System.out.println(test);

    }


}
