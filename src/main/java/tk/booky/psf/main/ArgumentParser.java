package tk.booky.psf.main;
// Created by booky10 in PigSpawnerFinder (21:52 12.05.21)

import joptsimple.OptionException;
import joptsimple.OptionParser;
import joptsimple.OptionSet;
import tk.booky.psf.utils.Constants;

import java.io.IOException;

public class ArgumentParser extends OptionParser {

    private final String[] arguments;

    public ArgumentParser(String[] arguments) {
        this.arguments = arguments;
        init();
    }

    private void init() {
        accepts("help");
        accepts("version");

        accepts("threads").withRequiredArg().ofType(int.class).defaultsTo(Runtime.getRuntime().availableProcessors());
        accepts("size").withRequiredArg().ofType(int.class).defaultsTo(2400);
    }

    public OptionSet parse() {
        try {
            OptionSet set = parse(arguments);

            if (set.has("help")) {
                try {
                    printHelpOn(System.out);
                } catch (IOException exception) {
                    throw new Error(exception);
                }
            } else if (set.has("version")) {
                Constants.LOGGER.info("{} version {} by {}.", Constants.NAME, Constants.VERSION, Constants.AUTHOR);
            } else {
                return set;
            }
        } catch (OptionException exception) {
            if (exception.getClass().getName().equals("joptsimple.UnrecognizedOptionException")) {
                Constants.LOGGER.error("{}.", exception.getMessage());
            } else {
                throw exception;
            }
        }

        System.exit(0);
        throw new IllegalStateException();
    }
}