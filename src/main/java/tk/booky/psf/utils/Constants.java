package tk.booky.psf.utils;
// Created by booky10 in PigSpawnerFinder (21:54 12.05.21)

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Random;

public class Constants {

    private static final Package PACKAGE = Constants.class.getPackage();
    private static final boolean DEV = PACKAGE.getImplementationTitle() == null;

    public static final String NAME = DEV ? "DEV" : PACKAGE.getImplementationTitle();
    public static final String VERSION = DEV ? "DEV" : PACKAGE.getImplementationVersion();
    public static final String AUTHOR = DEV ? "DEV" : PACKAGE.getImplementationVendor();

    public static final Logger LOGGER = LogManager.getLogger(NAME);
    public static final Random RANDOM = new Random(System.currentTimeMillis());
}