package questions.image;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

public class InMemoryImageStore implements ImageStore {

    private ConcurrentMap<ImageIndex, Image> map;

    public InMemoryImageStore() {
        map = new ConcurrentHashMap<>();
    }

    @Override
    public Image load(ImageIndex imageIndex) {
        return map.get(imageIndex);
    }

    @Override
    public void store(Image image) {
        map.put(image.getImageIndex(), image);
    }

    @Override
    public void update(ImageIndex imageIndex, byte[] content, ImageMetadata metadata) {
        map.computeIfPresent(imageIndex, (imageIndexKey, oldImage) -> {
            oldImage.setContent(content);
            oldImage.setImageMetadata(metadata);
            return oldImage;
        });
    }
}
