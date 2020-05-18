package questions.image;

import java.io.IOException;

public interface ImageStore {

    public Image load(ImageIndex imageIndex) throws IOException;

    public void store(Image image) throws IOException;

    public void update(ImageIndex imageIndex, byte[] content, ImageMetadata metadata) throws IOException;
}
