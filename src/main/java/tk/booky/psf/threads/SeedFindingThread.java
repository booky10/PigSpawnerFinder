package tk.booky.psf.threads;
// Created by booky10 in PigSpawnerFinder (22:20 12.05.21)

import PigSpawnerFinder.PigSpawnerFromWorldSeed;
import tk.booky.psf.utils.Constants;

public class SeedFindingThread extends Thread {

    private final int size;

    public SeedFindingThread(int id, int size) {
        super("Seed Finder Thread " + id);
        this.size = size;
    }

    @Override
    public synchronized void start() {
        super.start();
    }

    @Override
    @SuppressWarnings("all")
    public void run() {
        Constants.LOGGER.info("Started finding seeds...");

        long seed;
        int multiplied = size * 2;

        while (true) {
            Constants.LOGGER.debug("Searching an area of {}x{} chunks in {}...", multiplied, multiplied, seed = Constants.RANDOM.nextLong());

            for (int x = -size; x < size; x++) {
                for (int z = -size; z < size; z++) {
                    PigSpawnerFromWorldSeed.processForChunk(seed, x, z);
                }
            }
        }
    }
}