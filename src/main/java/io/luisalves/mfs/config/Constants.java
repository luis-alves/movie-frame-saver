package io.luisalves.mfs.config;

/**
 * Program's global constant values.
 *
 * @author Luís Alves
 */
public class Constants {

    public static final String FILE_SEPARATOR = System.getProperty("file.separator");

    /**
     * Private constructor.
     */
    private Constants() {
        throw new AssertionError();
    }
}
