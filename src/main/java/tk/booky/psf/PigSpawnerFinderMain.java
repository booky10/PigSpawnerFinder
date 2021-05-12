package tk.booky.psf;
// Created by booky10 in PigSpawnerFinder (20:11 11.05.21)

import PigSpawnerFinder.PigSpawnerFromWorldSeed;

import java.util.Random;

public class PigSpawnerFinderMain {

    // TODO: proper logging system
    // TODO: jobtsimple \w argument parsing
    // TODO: proper seed finding cleanup

    private static final Random RANDOM = new Random(System.currentTimeMillis());
    private static final int SIZE = 2400, MULTIPLIED = SIZE * 2, THREADS = 10;

    public static void main(String[] args) {
        Runtime.getRuntime().addShutdownHook(new Thread(() -> System.out.println("Shutting down..."), "Shutdown Thread"));
        Thread.currentThread().setName("Startup Thread");

        for (int i = 1; i <= THREADS; i++) {
            int thread = i;
            new Thread(() -> {
                long seed;
                // noinspection all
                while (true) {
                    System.out.printf("[Thread %d] Searching an area of %dx%d chunks in %d...%n", thread, MULTIPLIED, MULTIPLIED, (seed = RANDOM.nextLong()));

                    for (int x = -SIZE; x < SIZE; x++) {
                        for (int z = -SIZE; z < SIZE; z++) {
                            PigSpawnerFromWorldSeed.processForChunk(seed, x, z);
                        }
                    }
                }
            }, "Finder Thread " + thread).start();

            if (thread != THREADS) {
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException exception) {
                    exception.printStackTrace();
                    System.exit(1);
                }
            }
        }
        System.out.println("[Thread NaN] Started " + THREADS + " seed finding threads!");

        new GarbageCollector().start();
        System.out.println("[Thread NaN] Started " + THREADS + " garbage collector!");
    }
}