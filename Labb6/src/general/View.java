/*
 * @ Leo Man
 * @ Leo Vedberg
 * @ Jacky Phuong
 * @ Viktor Sundén
 */

package general;

import java.util.Observer;
import java.util.Observable;

//  A class for general view
@SuppressWarnings("deprecation")
public abstract class View implements Observer {
    public abstract void update(Observable o, Object arg);
}
