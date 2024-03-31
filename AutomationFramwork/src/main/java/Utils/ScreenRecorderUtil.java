package Utils;
import org.monte.media.Format;
import org.monte.media.FormatKeys;
import org.monte.media.Registry;
import org.monte.media.FormatKeys.*;
import org.monte.media.VideoFormatKeys;
import org.monte.media.math.Rational;
import org.monte.screenrecorder.ScreenRecorder;
import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import static org.monte.media.AudioFormatKeys.*;

public class ScreenRecorderUtil extends ScreenRecorder {
    public static ScreenRecorder screenRecorder;
    public String name;

    public ScreenRecorderUtil(GraphicsConfiguration cfg, Rectangle captureArea, Format fileFormat,
                              Format screenFormat, Format mouseFormat, Format audioFormat, File movieFolder, String name)
            throws IOException, AWTException {
        super(cfg, captureArea, fileFormat, screenFormat, mouseFormat, audioFormat, movieFolder);
        this.name = name;
    }
    
    @Override
    protected File createMovieFile(Format fileFormat) throws IOException {

        if (!movieFolder.exists()) {
            movieFolder.mkdirs();
        } else if (!movieFolder.isDirectory()) {
            throw new IOException("\"" + movieFolder + "\" is not a directory.");
        }
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH.mm.ss");
        return new File(movieFolder,
                name + "-" + dateFormat.format(new Date()) + "." + Registry.getInstance().getExtension(fileFormat));
    }

    public static void startRecord(String recordedVideoPath, String recordedVideoName) throws Exception {
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        int width = screenSize.width;
        int height = screenSize.height;

        Rectangle captureSize = new Rectangle(0, 0, width, height);

        GraphicsConfiguration gc = GraphicsEnvironment.getLocalGraphicsEnvironment().
                getDefaultScreenDevice()
                .getDefaultConfiguration();
        screenRecorder = new ScreenRecorderUtil(gc, captureSize,
                new Format(MediaTypeKey, MediaType.FILE, MimeTypeKey, FormatKeys.MIME_QUICKTIME),
                new Format(FormatKeys.MediaTypeKey, MediaType.VIDEO,
						FormatKeys.EncodingKey, VideoFormatKeys.ENCODING_QUICKTIME_JPEG,
						VideoFormatKeys.CompressorNameKey, VideoFormatKeys.COMPRESSOR_NAME_QUICKTIME_JPEG,
						VideoFormatKeys.DepthKey, 24,
						FormatKeys.FrameRateKey, Rational.valueOf(15),
						VideoFormatKeys.QualityKey, 0.5f,
						FormatKeys.KeyFrameIntervalKey, 15 * 60 ),
                new Format(FormatKeys.MediaTypeKey, MediaType.VIDEO,
						FormatKeys.EncodingKey, "black",
						FormatKeys.FrameRateKey, Rational.valueOf(15)),
                null, new File( recordedVideoPath ), recordedVideoName );
        screenRecorder.start();
    }

    public static void stopRecord() throws Exception {
        screenRecorder.stop();
    }

}