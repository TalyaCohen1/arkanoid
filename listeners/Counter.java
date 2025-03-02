//Talya Cohen- 213954217
package listeners;

/**
 * A class that models a counter that counts things.
 */
public class Counter {
    //fields
    private int count;

    /**
     * Constructs a new listeners.Counter.
     */
    public Counter() {
        this.count = 0;
    }
    /**
     * Increases the counter by a given number.
     * @param number The number to increase the counter by.
     */
    public void increase(int number) {
        this.count += number;
    }

    /**
     * Decreases the counter by a given number.
     * @param number The number to decrease the counter by.
     */
    public void decrease(int number) {
        this.count -= number;
    }

    /**
     * Gets the value of the counter.
     * @return The value of the counter.
     */
    public int getValue() {
        return this.count;
    }
}
