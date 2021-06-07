package io.luisalves.mfs.utils;

import java.util.function.Consumer;

/**
 * Program's print utility.
 *
 * @author Luís Alves
 */
public class PrintUtil {

    /**
     * Used to print the program's message.
     *
     * @param consumer injected function used to print the message
     * @param message message to be printed
     */
    public static void print(Consumer<String> consumer, String message) {
        consumer.accept(message);
    }

    /**
     * Private constructor.
     */
    private PrintUtil() {
        throw new AssertionError();
    }
}
