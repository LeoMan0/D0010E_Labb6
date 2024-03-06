/*
 * @ Leo Man
 * @ Leo Vedberg
 * @ Jacky Phuong
 * @ Viktor Sundén
 */

package general;

import java.util.Observable;

@SuppressWarnings("deprecation")
public abstract class State extends Observable {

    private int numberOfExectuions = 0;
    private boolean stop = false;
    private float timePassed = 0;

    // Check if simulator is stopped
    public boolean getStop() {
        return stop;
    }


    /*
     * @return time passed
     */

    public float getTimePassed() {
        return timePassed;
    }

    public void setTimePassed(float newTimePassed) {
        this.timePassed = newTimePassed;
    }

    public int getNumberOfExectuions() {
        return numberOfExectuions;
    }

    public void increseNumberOfExectuions() {
        this.numberOfExectuions++;
    }

    /*
     * @param event notify about the event in use to observer
     */
    public void notify(Event event) {
        setChanged();
        notifyObservers();
    }
}
