package io.luisalves.mfs;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.nio.file.Paths;
import java.util.Optional;
import java.util.function.Consumer;

import javax.imageio.ImageIO;

import org.bytedeco.javacv.FFmpegFrameGrabber;
import org.bytedeco.javacv.Frame;
import org.bytedeco.javacv.FrameGrabber;
import org.bytedeco.javacv.Java2DFrameConverter;

import io.luisalves.mfs.config.Constants;
import io.luisalves.mfs.utils.PrintUtil;

/**
 * Captures, converts and saves the frames of a movie file.
 *
 * @author Luís Alves
 */
public class VideoProcessor {

    public static final int TOTAL_FRAMES_NUMBER = 10;
    public static final String OUTPUT_IMAGE_TYPE = "jpg";
    private static final Consumer<String> println = System.out::println;

    /**
     * Entry point to start the capturing process
     */
    public void run() {
        var pwd = Paths.get("").toAbsolutePath().toString();
        try (var grabber = new FFmpegFrameGrabber(pwd + "/" + MovieFrameSaver.getVideoFileName())) {
            grabber.start();
            processFrames(grabber);
        } catch (FrameGrabber.Exception e) {
            PrintUtil.print(println, "Error occurred during video grabber");
            System.exit(0);
        }
    }

    /**
     * Reads 10 frames from the video and saves the images
     *
     * @param capture video capture tool provided by javacv library
     */
    private static void processFrames(FFmpegFrameGrabber capture) {
        var frameNumber = 1;
        while (frameNumber <= TOTAL_FRAMES_NUMBER) {
            var frame = getFrame(capture);

            if (frame.isPresent()) {
                saveFrameImageFile(frameNumber, frame.get());
            } else {
                PrintUtil.print(println, "Error getting frame #" + frameNumber);
            }

            frameNumber++;
        }
    }

    /**
     * Grabs a from the video
     *
     * @param capture video capture tool provided by javacv library
     * @return a video frame
     */
    private static Optional<Frame> getFrame(FFmpegFrameGrabber capture) {
        Frame frame = null;
        try {
            frame = capture.grabImage();
        } catch (FFmpegFrameGrabber.Exception e) {
            PrintUtil.print(println, "Error occurred during frame capture");
            System.exit(0);
        }

        return Optional.ofNullable(frame);
    }

    /**
     * Writes the image in the filesystem.
     *
     * @param frameNumber identifier number of the image
     * @param frame frame to be writen as images in the filesystem
     */
    private static void saveFrameImageFile(int frameNumber, Frame frame) {
        if (frame != null) {
            try {
                //@formatter:off
                ImageIO.write(convertFrameToBufferedImage(frame),
                              OUTPUT_IMAGE_TYPE,
                              getUniqueOutputFile(frameNumber));
                //@formatter:on
            } catch (IOException e) {
                PrintUtil.print(println, "Error occurred during frame saving process");
                System.exit(0);
            }
        }
    }

    /**
     * Converts the frame into a buffered image.
     *
     * @param frame frame to be converted to image
     * @return buffered image of the frame
     */
    private static BufferedImage convertFrameToBufferedImage(Frame frame) {
        try (var frameConverter = new Java2DFrameConverter()) {
            return frameConverter.getBufferedImage(frame);
        } catch (Exception e) {
            PrintUtil.print(println, "Error occurred during frame conversion");
            System.exit(0);
        }

        return null;
    }

    /**
     * Constructs the final image file path
     *
     * @param frameNumber identifier number of the image
     * @return file path of the final image
     */
    private static File getUniqueOutputFile(int frameNumber) {
        String videoName = MovieFrameSaver.getVideoFileName().split("\\.")[0];

        //@formatter:off
        String finalFrameOutputName
                = MovieFrameSaver.getFramesOutputPath() + Constants.FILE_SEPARATOR + videoName + "_frame#" + frameNumber + ".jpg";
        //@formatter:on

        return new File(finalFrameOutputName);
    }
}
