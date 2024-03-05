package specific;

import general.*;

public class ArrivalEvent extends Event {


    public ArrivalEvent(float time, int customerNumber) {
        super(time, customerNumber);
        this.nameOfCurrentEvent = "ArrivalEvent";
    }

    @Override
    public Event execute() {


        float next = scheduleNextEventTime();

        return new EnterEvent(next, this.getEventTarget());
    }

    // Here we have to find the time
    public float scheduleNextEventTime() {
        // Something if StoreFull = false
        return ++time;
        // Something else
        // Something wait or idk
    }


}
