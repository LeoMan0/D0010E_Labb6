package specific;

public class MakeCustomer {


    float time;
    float arrivalTime, timeToPick, timeToPay, timeMaxWait;


    int EventState = 0;
    boolean inStore;

    public MakeCustomer(float arrivalTime, float timeToPick, float timeToPay, float timeMaxWait) {
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


}
