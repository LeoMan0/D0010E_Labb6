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
        nextTime();

        return new EnterEvent(2);
    }

    public void nextTime() {
        time = time + 1;
    }


}
