package questions.image;

import java.io.Serializable;
import java.util.Optional;

public class Image implements Serializable {

    private final transient ImageIndex imageIndex;
    private byte[] content;
    private ImageMetadata imageMetadata;

    Image(ImageIndex imageIndex, byte[] content, ImageMetadata imageMetadata) {
        this.imageIndex = imageIndex;
        this.content = content;
        this.imageMetadata = imageMetadata;
    }

    public ImageIndex getImageIndex() {
        return imageIndex;
    }

    public byte[] getContent() {
        return content;
    }

    void setContent(byte[] content) {
        this.content = content;
    }

    public Optional<ImageMetadata> getImageMetadata() {
        return Optional.ofNullable(imageMetadata);
    }

    void setImageMetadata(ImageMetadata imageMetadata) {
        this.imageMetadata = imageMetadata;
    }
}
