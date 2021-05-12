package tk.booky.psf.main;
// Created by booky10 in PigSpawnerFinder (20:11 11.05.21)

import PigSpawnerFinder.PigSpawnerFromWorldSeed;
import tk.booky.psf.utils.Constants;

public class PigSpawnerFinderMain {

    // TODO: proper seed finding cleanup

    private static final int SIZE = 2400, MULTIPLIED = SIZE * 2, THREADS = 10;

    public static void main(String[] args) {
        Runtime.getRuntime().addShutdownHook(new Thread(() -> Constants.LOGGER.info("Shutting down"), "Shutdown Thread"));
        Thread.currentThread().setName("Startup Thread");

        for (int i = 1; i <= THREADS; i++) {
            new Thread(() -> {
                long seed;
                // noinspection all
                while (true) {
                    Constants.LOGGER.info("Searching an area of {}x{} chunks in {}...", MULTIPLIED, MULTIPLIED, seed = Constants.RANDOM.nextLong());

                    for (int x = -SIZE; x < SIZE; x++) {
                        for (int z = -SIZE; z < SIZE; z++) {
                            PigSpawnerFromWorldSeed.processForChunk(seed, x, z);
                        }
                    }
                }
            }, "Finder Thread " + i).start();

            if (i != THREADS) {
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException exception) {
                    exception.printStackTrace();
                    System.exit(1);
                }
            }
        }

        Constants.LOGGER.info("Started {} seed finding threads!", THREADS);

        new GarbageCollector().start();
        Constants.LOGGER.info("Started garbage collector thread!");
    }
}