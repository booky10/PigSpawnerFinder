package tk.booky.psf;
// Created by booky10 in PigSpawnerFinder (19:53 12.05.21)

public class GarbageCollector extends Thread {

    public GarbageCollector() {
        super("Garbage Collector Thread");

        setDaemon(true);
    }

    @Override
    @SuppressWarnings("all")
    public void run() {
        while (true) {
            try {
                System.gc();
                Thread.sleep(1000);
            } catch (Throwable throwable) {
                throwable.printStackTrace();
            }
        }
    }
}