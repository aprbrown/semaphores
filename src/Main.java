/**
 * Main class for CSC8002 Semaphores coursework.
 */
public class Main {
    public static void main(String[] args) throws InterruptedException {
        /*
         * xSemaphore is initially set to a value of 1 to ensure that X prints before Y
         */
        BinarySemaphore xS = new BinarySemaphore(1);
        BinarySemaphore yS = new BinarySemaphore(0);
        Semaphore zS = new Semaphore(0);

        Runnable xR = new LetterPrinter("X", xS, yS, zS);
        Runnable yR = new LetterPrinter("Y", yS, xS, zS);
        Runnable zR = new LetterPrinter(zS);

        Thread x = new Thread(xR);
        Thread y = new Thread(yR);
        Thread z = new Thread(zR);

        x.start();
        y.start();
        z.start();

        Thread.sleep(100000);
        System.exit(0);
    }
}
