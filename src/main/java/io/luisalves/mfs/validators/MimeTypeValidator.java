package io.luisalves.mfs.validators;

import java.io.File;
import java.io.IOException;
import java.util.Set;
import java.util.function.Consumer;

import org.apache.tika.Tika;

import io.luisalves.mfs.utils.PrintUtil;

/**
 * Mime type validator to identify valid video files.
 *
 * @author Luís Alves
 */
public class MimeTypeValidator {

    private static final Consumer<String> PRINT_LN_FUN = System.out::println;

    //@formatter:off
    private static final Set<String> VIDEO_MIME_TYPES = Set.of("application/vnd.apple.mpegurl",
                                                               "application/x-mpegurl",
                                                               "video/3gpp",
                                                               "audio/vorbis",
                                                               "video/mp4",
                                                               "video/mpeg",
                                                               "video/ogg",
                                                               "audio/ogg",
                                                               "video/quicktime",
                                                               "video/webm",
                                                               "video/x-m4v",
                                                               "video/ms-asf",
                                                               "video/x-ms-wmv",
                                                               "video/x-msvideo",
                                                               "application/x-troff-msvideo");
    //@formatter:on

    /**
     * Check if the file has a valid video mime type.
     *
     * @param fileName video file name with extension
     */
    public static void inputFileMimeType(String fileName) {
        String mimeType = null;

        try {
            var file = new File(fileName);
            mimeType = new Tika().detect(file);
        } catch (IOException e) {
            PrintUtil.print(PRINT_LN_FUN, "Error checking mime type");

            System.exit(0);
        }

        if (mimeType == null || ! VIDEO_MIME_TYPES.contains(mimeType)) {
            PrintUtil.print(PRINT_LN_FUN, "Invalid file type.");

            System.exit(0);
        }
    }

    /**
     * Private constructor.
     */
    private MimeTypeValidator() {
        throw new AssertionError();
    }
}
