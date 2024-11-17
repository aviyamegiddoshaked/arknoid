package hitlisteners;

/**
 * The type Counter.
 */
public class Counter {
    private int value;

    /**
     * Instantiates a new Counter and gives it the value 0.
     *
     */
    public Counter() {
        this.value = 0;
    }

    /**
     * Increase.
     * add number to current count.
     * @param number the number
     */
    public void increase(int number) {
        this.value += number;
    }

    /**
     * Decrease.
     * decreases number from count.
     * @param number the number
     */
    public void decrease(int number) {
        this.value -= number;
    }

    /**
     * Get value int.
     * Gets the current value of count.
     * @return the int
     */
    public int getValue() {
        return this.value;
    }
}
