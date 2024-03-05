/*
 * @ Leo Man
 * @ Leo Vedberg
 * @ Jacky Phuong
 * @ Viktor Sundén
 */

package general;

import java.util.Observable;
import java.util.Observer;
import java.util.Observer;

@SuppressWarnings("deprecation")
public class State extends Observable {
    private boolean stopped;
    private double timePassed;

    // Check if simulator is stopped
    public boolean stopped() {
        return stopped;
    }


    /*
     * @return time passed
     */
    public double timePassed() {
        return timePassed;
    }

    /*
     * @param event notify about the event in use to observer
     */
    public void notify(Event event) {
        setChanged();
        notifyObservers(event);
    }
}
