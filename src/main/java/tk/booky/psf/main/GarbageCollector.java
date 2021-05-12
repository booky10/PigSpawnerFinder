package tk.booky.psf.main;
// Created by booky10 in PigSpawnerFinder (19:53 12.05.21)

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class GarbageCollector extends Thread {

    private static final Logger LOGGER = LoggerFactory.getLogger("Garbage Collector");

    public GarbageCollector() {
        super("Garbage Collector Thread");

        setDaemon(true);
    }

    @Override
    @SuppressWarnings("all")
    public void run() {
        while (true) {
            try {
                LOGGER.debug("Running Garbage Collection...");
                System.gc();

                Thread.sleep(1000);
            } catch (Throwable throwable) {
                throwable.printStackTrace();
            }
        }
    }
}