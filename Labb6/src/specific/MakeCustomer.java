package specific;

public class MakeCustomer {


    float arrivalTime;
    int customerId;

    boolean inStore;

    public MakeCustomer(int customerId, float arrivalTime) {
        this.customerId = customerId;
        this.arrivalTime = arrivalTime;

    }

    public float getArrivalTime() {
        return arrivalTime;
    }


    public int getCustomerId() {
        return customerId;
    }
}
