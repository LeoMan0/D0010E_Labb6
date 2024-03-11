import java.util.Random;

public class Optimize implements K {


    // Values from k interface. Change K file to change which method to run.
    private int seed = SEED;
    private int maxCapacityInStore = M;
    private double lambda = L;
    private double minPickTime = LOW_COLLECTION_TIME;
    private double maxPickTime = HIGH_COLLECTION_TIME;
    private double minPayTime = LOW_PAYMENT_TIME;
    private double maxPayTime = HIGH_PAYMENT_TIME;
    private float closeStoreTime = (float) END_TIME;


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

        System.out.println("Minsta antal kassor som ger minimalt antal missade (" + minMissedCustomers + "): " + optimalCheckouts);
        return optimalCheckouts;
    }


    public int optimalCheckOutsRandomSeed() {
        Random random = new Random();
        int randomSeed = random.nextInt(); //Creating a random seed

        return this.optimalCheckOutsSetSeed(randomSeed);
    }


    public static void main(String[] args) {
        Optimize optimize = new Optimize();

        int test = optimize.optimalCheckOutsSetSeed(optimize.seed);
        System.out.println(test);
        int test2 = optimize.optimalCheckOutsRandomSeed();
        System.out.println(test2);

    }


}
