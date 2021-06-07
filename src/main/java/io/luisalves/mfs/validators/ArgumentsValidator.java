package io.luisalves.mfs.validators;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.function.Consumer;

import io.luisalves.mfs.config.Constants;
import io.luisalves.mfs.utils.PrintUtil;

/**
 * Program's arguments validator.
 *
 * @author Luís Alves
 */
public class ArgumentsValidator {

    private static final Consumer<String> PRINT_FUN = System.out::print;
    private static final Consumer<String> PRINT_LN_FUN = System.out::println;
    private static final String PWD = Paths.get("").toAbsolutePath().toString();

    /**
     * Checks if the number of arguments are correct.
     *
     * @param args program's arguments
     */
    public static void argsTotalNumber(String... args) {
        if (args.length != 2) {
            PrintUtil.print(PRINT_FUN, "Wrong number of arguments. ");
            PrintUtil.print(PRINT_LN_FUN, "Please use: \"java fileNameWithExtension framesOutputPath\"");
            if ("/".equals(Constants.FILE_SEPARATOR)) {
                PrintUtil.print(PRINT_LN_FUN, "MacOs/Linux Example: \"java rambo.avi /home/username/pictures\"");
                return;
            }
            PrintUtil.print(PRINT_LN_FUN, "Windows Example: \"java rambo.avi c:\\username\\pictures\"");

            System.exit(0);
        }
    }

    /**
     * Checks if it's a valid file and is readable.
     *
     * @param fileName video file name
     */
    public static void inputFilePath(String fileName) {
        if (!Files.isRegularFile(Path.of(PWD, fileName)) || !Files.isReadable(Path.of(PWD, fileName))) {
            PrintUtil.print(PRINT_LN_FUN, "Please add valid video file in same directory as this jar file");

            System.exit(0);
        }
    }

    /**
     * Checks if the output image path is valid.
     *
     * @param outputPath output image path
     */
    public static void outputFilePath(String outputPath) {
        var path = new File(outputPath);
        if (!path.isDirectory()) {
            PrintUtil.print(PRINT_LN_FUN, "Invalid output file path. Please, select a valid directory path");

            System.exit(0);
        }
    }

    /**
     * Checks if the user has permission to write to the output path.
     *
     * @param outputPath image output path
     */
    public static void outputPathPermissions(String outputPath) {
        if (!new File(outputPath).canWrite()) {
            //@formatter:off
            PrintUtil.print(PRINT_LN_FUN,
                            "Invalid output file path. Please, select a directory path with correct write permissions");
            //@formatter:on

            System.exit(0);
        }
    }

    /**
     * Private constructor.
     */
    private ArgumentsValidator() {
        throw new AssertionError();
    }
}
