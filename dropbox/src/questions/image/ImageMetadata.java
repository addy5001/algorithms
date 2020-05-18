package questions.image;

import java.io.Serializable;

public class ImageMetadata implements Serializable {

    private int grayscale;
    private int rgb;
    private int definition;
    private int encoding;

    public ImageMetadata(int grayscale, int rgb, int definition, int encoding) {
        this.grayscale = grayscale;
        this.rgb = rgb;
        this.definition = definition;
        this.encoding = encoding;
    }

    public int getGrayscale() {
        return grayscale;
    }

    public int getRgb() {
        return rgb;
    }

    public int getDefinition() {
        return definition;
    }

    public int getEncoding() {
        return encoding;
    }

    @Override
    public String toString() {
        return "ImageMetadata{" +
                "grayscale=" + grayscale +
                ", rgb=" + rgb +
                ", definition=" + definition +
                ", encoding=" + encoding +
                '}';
    }
}
