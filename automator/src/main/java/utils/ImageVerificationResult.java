package utils;

import java.awt.image.BufferedImage;

/**
 * Image verification result.
 */
public class ImageVerificationResult {

    public final int diffPixels;
    public final double diffPercent;
    public final BufferedImage actualImage;
    public final BufferedImage diffImage;
    public final BufferedImage expectedImage;

    /**
     * Init image verification result.
     */
    public ImageVerificationResult(int diffPixels,
                                   double diffPercent,
                                   BufferedImage actualImage,
                                   BufferedImage diffImage,
                                   BufferedImage expectedImage) {
        this.diffPixels = diffPixels;
        this.diffPercent = diffPercent;
        this.actualImage = actualImage;
        this.diffImage = diffImage;
        this.expectedImage = expectedImage;
    }
}
