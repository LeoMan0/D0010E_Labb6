package specific;

public class MakeCustomer {


    //Time is with eventState to be able to order EventQueque in the order of event
    float time;
    float arrivalTime, timeToPick, timeToPay, timeMaxWait;


    //eventState is used to track what event is next to be excuted for each cutomer
    //i.e eventState 0: EventArrival
    //etc not sure yet.

    int eventState = 0;
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
