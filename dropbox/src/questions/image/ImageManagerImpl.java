package questions.image;

import java.io.IOException;

public class ImageManagerImpl implements ImageManager {

    private ImageStore imageStore;

    public ImageManagerImpl(ImageStore imageStore) {
        this.imageStore = imageStore;
    }

    @Override
    public void put(Image image) throws IOException {
        this.imageStore.store(image);
    }

    @Override
    public Image get(ImageIndex imageIndex) throws IOException {
        return this.imageStore.load(imageIndex);
    }

    @Override
    public void update(ImageIndex imageIndex, byte[] content, ImageMetadata metadata) throws IOException {
        this.imageStore.update(imageIndex, content, metadata);
    }
}
