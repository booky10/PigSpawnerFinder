package tk.booky.psf.threads;
// Created by booky10 in PigSpawnerFinder (19:53 12.05.21)

import tk.booky.psf.utils.Constants;

public class GarbageCollectorThread extends Thread {

    public GarbageCollectorThread() {
        super("Garbage Collector Thread");

        setDaemon(true);
    }

    @Override
    public synchronized void start() {
        Constants.LOGGER.info("Started garbage collector thread!");
        super.start();
    }

    @Override
    @SuppressWarnings("all")
    public void run() {
        while (true) {
            try {
                Constants.LOGGER.debug("Running Garbage Collection...");
                System.gc();

                Thread.sleep(1000);
            } catch (Throwable throwable) {
                throwable.printStackTrace();
            }
        }
    }
}