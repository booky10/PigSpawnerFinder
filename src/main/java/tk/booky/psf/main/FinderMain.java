package tk.booky.psf.main;
// Created by booky10 in PigSpawnerFinder (20:11 11.05.21)

import joptsimple.OptionSet;
import tk.booky.psf.threads.GarbageCollectorThread;
import tk.booky.psf.threads.SeedFindingThread;
import tk.booky.psf.utils.Constants;

public class FinderMain {

    // TODO: proper seed finding cleanup

    public static void main(String[] args) {
        Runtime.getRuntime().addShutdownHook(new Thread(() -> Constants.LOGGER.info("Shutting down"), "Shutdown Thread"));
        Thread.currentThread().setName("Startup Thread");

        OptionSet options = new ArgumentParser(args).parse();
        int threads = (int) options.valueOf("threads");
        int size = (int) options.valueOf("size");

        for (int i = 1; i <= threads; i++) {
            new SeedFindingThread(i, size).start();

            try {
                Thread.sleep(i != threads ? 1000 : 125);
            } catch (InterruptedException exception) {
                exception.printStackTrace();
                System.exit(1);
            }
        }

        Constants.LOGGER.info("Started {} seed finding threads!", threads);
        new GarbageCollectorThread().start();
    }
}