package utils;

import java.awt.*;
import java.awt.image.BufferedImage;

/**
 * Image processing utils.
 */
public class ImageUtils {

    public static ImageVerificationResult compare(BufferedImage actualImage,
                                           BufferedImage expectedImage,
                                           int similarPixelTolerance,
                                           int ignoreHeaderPixels,
                                           int ignoreFooterPixels) throws Exception {
        int diffPixels = 0;
        double diffPercent;

        // Generate diff image
        BufferedImage diffImage = copyImage(actualImage);

        // Get image sizes
        int width1 = diffImage.getWidth();
        int width2 = expectedImage.getWidth();
        int height1 = diffImage.getHeight();
        int height2 = expectedImage.getHeight();

        // Compare images (only if sizes match)
        if ((width1 != width2) || (height1 != height2)) {
            throw new Exception("Can not compare images with different sizes.");
        } else {
            // Define red color
            Color red = new Color(255, 0, 0);
            int redRgb = red.getRGB();

            // Compare pixel by pixel
            for (int i = ignoreHeaderPixels; i < height1 - ignoreFooterPixels; i++) {
                for (int j = 0; j < width1; j++) {
                    int rgb1 = diffImage.getRGB(j, i);
                    int blue1 = rgb1 & 0xFF;
                    int green1 = (rgb1 >> 8) & 0xFF;
                    int red1 = (rgb1 >> 16) & 0xFF;
                    int rgb2 = expectedImage.getRGB(j, i);
                    int blue2 = rgb2 & 0xFF;
                    int green2 = (rgb2 >> 8) & 0xFF;
                    int red2 = (rgb2 >> 16) & 0xFF;
                    int rDiff = Math.abs(red1 - red2);
                    int gDiff = Math.abs(green1 - green2);
                    int bDiff = Math.abs(blue1 - blue2);
                    int rootMeanSquare = (int) Math.sqrt(((rDiff * rDiff)
                            + (gDiff * gDiff) + (bDiff * bDiff)) / 3);

                    if (rootMeanSquare > similarPixelTolerance) {
                        diffPixels++;
                        diffImage.setRGB(j, i, redRgb);
                    }
                }
            }
        }

        diffPercent = (100 * diffPixels) / (double) (width2 * height2);
        return new ImageVerificationResult(diffPixels, diffPercent, actualImage, diffImage, expectedImage);
    }

    private static BufferedImage copyImage(BufferedImage source) {
        BufferedImage b = new BufferedImage(source.getWidth(), source.getHeight(), source.getType());
        Graphics g = b.getGraphics();
        g.drawImage(source, 0, 0, null);
        g.dispose();
        return b;
    }
}
