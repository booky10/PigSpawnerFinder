package tk.booky.psf.main;
// Created by booky10 in PigSpawnerFinder (20:11 11.05.21)

import PigSpawnerFinder.PigSpawnerFromWorldSeed;
import joptsimple.OptionSet;
import tk.booky.psf.utils.Constants;

public class PigSpawnerFinderMain {

    // TODO: proper seed finding cleanup

    public static void main(String[] args) {
        Runtime.getRuntime().addShutdownHook(new Thread(() -> Constants.LOGGER.info("Shutting down"), "Shutdown Thread"));
        Thread.currentThread().setName("Startup Thread");

        OptionSet options = new ArgumentParser(args).parse();
        int threads = (int) options.valueOf("threads");
        int size = (int) options.valueOf("size"), multiplied = size * 2;

        for (int i = 1; i <= threads; i++) {
            new Thread(() -> {
                long seed;
                // noinspection all
                while (true) {
                    Constants.LOGGER.info("Searching an area of {}x{} chunks in {}...", multiplied, multiplied, seed = Constants.RANDOM.nextLong());

                    for (int x = -size; x < size; x++) {
                        for (int z = -size; z < size; z++) {
                            PigSpawnerFromWorldSeed.processForChunk(seed, x, z);
                        }
                    }
                }
            }, "Finder Thread " + i).start();

            if (i != threads) {
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException exception) {
                    exception.printStackTrace();
                    System.exit(1);
                }
            }
        }

        Constants.LOGGER.info("Started {} seed finding threads!", threads);

        new GarbageCollector().start();
        Constants.LOGGER.info("Started garbage collector thread!");
    }
}