package questions.image;

import java.util.Objects;

public class ImageIndex {

    private final String namespace;
    private final String identifier;
    private final int xCoord;
    private final int yCoord;

    public ImageIndex(String namespace, String identifier, int xCoord, int yCoord) {
        this.namespace = namespace;
        this.identifier = identifier;
        this.xCoord = xCoord;
        this.yCoord = yCoord;
    }

    public String getNamespace() {
        return namespace;
    }

    public String getIdentifier() {
        return identifier;
    }

    public int getxCoord() {
        return xCoord;
    }

    public int getyCoord() {
        return yCoord;
    }

    @Override
    public boolean equals(Object obj) {
        if(Objects.isNull(obj))
            return false;

        if(obj instanceof ImageIndex) {
            ImageIndex other = (ImageIndex) obj;
            return namespace.equals(other.namespace)
                    && identifier.equals(other.identifier)
                    && xCoord == other.xCoord
                    && yCoord == other.yCoord;
        }

        return false;
    }

    @Override
    public int hashCode() {
        return Objects.hash(namespace, identifier, xCoord, yCoord);
    }

    @Override
    public String toString() {
        return "ImageIndex{" +
                "namespace='" + namespace + '\'' +
                ", identifier='" + identifier + '\'' +
                ", xCoord=" + xCoord +
                ", yCoord=" + yCoord +
                '}';
    }
}
