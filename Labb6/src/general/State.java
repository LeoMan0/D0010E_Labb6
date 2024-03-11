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

    private boolean run = true;
    private float timePassed = 0;

    private boolean print;

    // Check if simulator is stopped

    public void stop() {
        run = false;
    }

    public boolean getRun() {
        return run;
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


    /*
     * @param event notify about the event in use to observer
     */
    public void notifyView() {
        setChanged();
        notifyObservers();
    }


    public void setPrint(boolean print) {
        this.print = print;
    }

    public boolean getPrint() {
        return this.print;
    }
}
