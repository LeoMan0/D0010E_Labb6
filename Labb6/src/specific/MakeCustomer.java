package specific;

public class MakeCustomer {


    float arrivalTime, timeToPick, timeToPay, timeMaxWait;
    int customerId;

    boolean inStore;

    public MakeCustomer(int customerId, float arrivalTime, float timeToPick, float timeToPay, float timeMaxWait) {
        this.customerId = customerId;
        this.arrivalTime = arrivalTime;
        this.timeToPick = timeToPick;
        this.timeToPay = timeToPay;
        this.timeMaxWait = timeMaxWait;
    }

    public float getArrivalTime() {
        return arrivalTime;
    }

    public float getTimeToPick() {
        return timeToPick;
    }

    public float getTimeToPay() {
        return timeToPay;
    }

    public float getTimeMaxWait() {
        return timeMaxWait;
    }

    public int getCustomerId() {
        return customerId;
    }
}
