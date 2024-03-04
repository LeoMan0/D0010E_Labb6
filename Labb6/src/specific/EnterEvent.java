package specific;

import general.Event;

public class EnterEvent extends Event {


    public EnterEvent(float time) {
        super(time);
        this.nameOfCurrentEvent = "EnterEvent";
    }


    @Override
    public Event execute() {
        System.out.println("The things worked");
        getEventStatus();
        return null;
    }

}
