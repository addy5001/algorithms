package questions.image;

import java.io.IOException;

public interface ImageManager {

    public void put(Image image) throws IOException;

    public Image get(ImageIndex imageIndex) throws IOException;

    public void update(ImageIndex imageIndex, byte[] content, ImageMetadata metadata) throws IOException;
}
