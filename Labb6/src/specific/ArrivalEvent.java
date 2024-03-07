package specific;

import general.*;

public class ArrivalEvent extends Event {


    public ArrivalEvent(float time, MakeCustomer customer) {
        super(time, customer);
        this.nameOfCurrentEvent = "ArrivalEvent";
    }

    @Override
    public Event execute() {


        float next = scheduleNextEventTime();

        return new EnterEvent(next, (MakeCustomer) this.getEventTarget());
    }

    // Here we have to find the time
    public float scheduleNextEventTime() {
        // Something if StoreFull = false
        return ++time;
        // Something else
        // Something wait or idk
    }


}
