/**
 * Modifies the Semaphore class to a binary semaphore, the flag may only be 1 or 0.
 */
public class BinarySemaphore extends Semaphore {

    /**
     * Ensures that the initial value may only be 0 or 1
     * @param initial
     */
    BinarySemaphore(int initial) {
        value = (initial >= 1) ? 1 : 0;
    }

    /**
     * When signalling, rather than incrementing the value up, it always makes the value 1
     */
    @Override
    public synchronized void V() {
        value = 1;
        notify();
    }
}
