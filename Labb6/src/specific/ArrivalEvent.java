package specific;

import general.*;

public class ArrivalEvent extends Event {


    public ArrivalEvent(float time) {
        super(time);
        this.nameOfCurrentEvent = "ArrivalEvent";
    }

    @Override
    public Event execute() {

        getEventStatus();

        float next = scheduleNextEventTime();

        return new EnterEvent(next);
    }

    // Here we have to find the time
    public float scheduleNextEventTime() {
        // Something if StoreFull = false
        return time;
        // Something else
        // Something wait or idk
    }


}
