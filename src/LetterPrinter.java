/**
 * Class to create runnable objects which print a letter to the console. Semaphores are used to determine the order in
 * which the letters print.
 */
public class LetterPrinter implements Runnable {
    private String name;
    private Semaphore zSemaphore;
    private BinarySemaphore thisLetterBinSemaphore;
    private BinarySemaphore otherLetterBinSemaphore;

    /**
     * A constructor to create the 'Z' letter thread.
     * @param zSemaphore
     */
    LetterPrinter(Semaphore zSemaphore) {
        this.name = "Z";
        this.zSemaphore = zSemaphore;
    }

    /**
     * Constructor for other letters, in this project the letters will be 'X' and 'Y'
     * @param name
     * @param thisLetterBinSemaphore
     * @param otherLetterBinSemaphore
     * @param zSemaphore
     */
    LetterPrinter(String name, BinarySemaphore thisLetterBinSemaphore, BinarySemaphore otherLetterBinSemaphore,
                  Semaphore zSemaphore) {
        this.name = name;
        this.thisLetterBinSemaphore = thisLetterBinSemaphore;
        this.otherLetterBinSemaphore = otherLetterBinSemaphore;
        this.zSemaphore = zSemaphore;
    }

    /**
     * Run method which checks the specific letter used by the thread and calls the relevant method
     */
    @Override
    public void run() {
        if (name.equals("Z")) {
            printZ();
        } else {
            printLetter(thisLetterBinSemaphore, name, otherLetterBinSemaphore, zSemaphore);
        }
    }

    /**
     * Method to print 'Z' to screen. Prints 'Z', signals the zSemaphore and then sleeps for a random increment
     */
    private void printZ() {
        while (true) try {
            System.out.print(name + " ");
            zSemaphore.V();
            Thread.sleep((long) (Math.random() * 1000));
        } catch (InterruptedException e) {

        }
    }

    /**
     * Method for printing 'X' or 'Y'. The zSemaphore is first consumed to ensure that 'X' or 'Y' only prints if a 'Z'
     * has been printed previously. The binary semaphore for this letter is then consumed, and then the letter is printed,
     * the opposite letter semaphore is then signalled and finally the thread sleeps for a random time frame.
     * @param thisLetterBinSemaphore
     * @param name
     * @param otherLetterBinSemaphore
     * @param zSemaphore
     */
    private void printLetter(BinarySemaphore thisLetterBinSemaphore, String name,
                             BinarySemaphore otherLetterBinSemaphore, Semaphore zSemaphore) {
        while (true) try {
            zSemaphore.P();
            thisLetterBinSemaphore.P();
            System.out.print(name + " ");
            otherLetterBinSemaphore.V();
            Thread.sleep((long) (Math.random() * 1000));
        } catch (InterruptedException e) {

        }
    }
}
