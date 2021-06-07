package io.luisalves.mfs;

import java.util.function.Consumer;

import io.luisalves.mfs.validators.ArgumentsValidator;
import io.luisalves.mfs.utils.PrintUtil;
import io.luisalves.mfs.validators.MimeTypeValidator;

/**
 * Main class used to validate and process a valid video file, by capturing the first 10 frames and save them in a user
 * specific path. Main function uses two arguments:
 * <ul>
 * <li>1st - video file name including its extension type</li>
 * <li>2nd - output path for the saved frames</li>
 * </ul>
 *
 * @author Luís Alves
 */
public class MovieFrameSaver {

    private static final Consumer<String> PRINT_LN_FUN = System.out::println;

    private static String framesOutputPath;
    private static String videoFileName;

    /**
     * Main method of the Java program.
     *
     * @param args array with video file name as 1st arg and output file path as 2nd arg
     */
    public static void main(String[] args) {
        validateArguments(args);

        videoFileName = args[0];
        framesOutputPath = args[1];

        processVideo();

        printSuccessMessageAndExit();
    }

    /**
     * Getter of the path of saved images.
     *
     * @return string representation of file system path to where the frame images will be saved
     */
    public static String getFramesOutputPath() {
        return framesOutputPath;
    }

    /**
     * Getter of the video name.
     *
     * @return string representation of video file name with extension
     */
    public static String getVideoFileName() {
        return videoFileName;
    }

    /**
     * Validation of the arguments inputted by the user.
     *
     * @param args array with video file name as 1st arg and output file path as 2nd arg
     */
    private static void validateArguments(String... args) {
        ArgumentsValidator.argsTotalNumber(args);

        MimeTypeValidator.inputFileMimeType(args[0]);
        ArgumentsValidator.inputFilePath(args[0]);

        ArgumentsValidator.outputFilePath(args[1]);
        ArgumentsValidator.outputPathPermissions(args[1]);
    }

    /**
     * Instantiates and runs the video handler and processor.
     */
    private static void processVideo() {
        var videoProcessor = new VideoProcessor();
        videoProcessor.run();
    }

    /**
     * Prints the program success message.
     */
    private static void printSuccessMessageAndExit() {
        PrintUtil.print(PRINT_LN_FUN, "");

        //@formatter:off
        PrintUtil.print(PRINT_LN_FUN,
                        "SUCCESS! A total of 10 frames where extracted from the "
                                + videoFileName
                                + " video.");

        PrintUtil.print(PRINT_LN_FUN,
                        "Please access the " + framesOutputPath + " directory to see the frames.");
        //@formatter:on

        System.exit(0);
    }
}
