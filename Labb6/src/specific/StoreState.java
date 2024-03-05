package specific;

import general.State;

public class StoreState extends State {
    // StoreState is the state the simulation is in
    // But it is also here setting for how the simulation will be run is.
    //


    private int seed;
    private int checkoutsOpen;
    private int maxCapacityInStore;

    private int currentCapacityInStore = 0;

    // The checkouts pay time are assumed to be uniformly distributed within an interval [min max]
    //  And likewise for the time it takes for customers to pay
    private float minPayTime;
    private float maxPayTime;

    private float minPickTime;
    private float maxPickTime;

    private float closeStoreTime;


    public StoreState(int seed, float closeStoreTime, int checkoutsOpen, int maxCapacityInStore, float minPayTime, float maxPayTime, float minPickTime, float maxPickTime) {
        this.seed = seed;
        this.closeStoreTime = closeStoreTime;
        this.checkoutsOpen = checkoutsOpen;
        this.maxCapacityInStore = maxCapacityInStore;
        this.minPayTime = minPayTime;
        this.maxPayTime = maxPayTime;
        this.minPickTime = minPickTime;
        this.maxPickTime = maxPickTime;
    }


    public int getSeed() {
        return seed;
    }


    public int getCheckoutsOpen() {
        return checkoutsOpen;
    }


    public int getMaxCapacityInStore() {
        return maxCapacityInStore;
    }


    public float getMinPayTime() {
        return minPayTime;
    }


    public float getMaxPayTime() {
        return maxPayTime;
    }


    public float getMinPickTime() {
        return minPickTime;
    }


    public float getMaxPickTime() {
        return maxPickTime;
    }


    public float getCloseStoreTime() {
        return closeStoreTime;
    }

    public int getCurrentCapacityInStore() {
        return getCurrentCapacityInStore();
    }

    public void decreaseCurrentCapacity() {
        this.currentCapacityInStore--;
    }

    public void increaseCurrentCapacity() {
        this.currentCapacityInStore++;
    }
}
