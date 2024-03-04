package general;

import specific.*;

import java.util.ArrayList;

public class EventQueue {

    //Just here to test stuff
    public static void main(String[] args) {


    }

    //Creating a List such that
    // {Event,Event,Event,etc}
    ArrayList<Event> queueList = new ArrayList<>();


    public EventQueue(Event initialEvent) {
        this.queueList.add(initialEvent);
    }


    public void addEvent(Event event) {
        this.queueList.add(event);

    }


}
