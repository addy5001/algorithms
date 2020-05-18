package questions.image;

public class ImageFactory {
    public static Image createImage(String namespace, String identifier, int xCoord, int yCoord, byte[] content, ImageMetadata metadata) {
        return new Image(new ImageIndex(namespace, identifier, xCoord, yCoord), content, metadata);
    }

    public static Image createImage(String namespace, String identifier, int xCoord, int yCoord, byte[] content) {
        return new Image(new ImageIndex(namespace, identifier, xCoord, yCoord), content, null);
    }
}
