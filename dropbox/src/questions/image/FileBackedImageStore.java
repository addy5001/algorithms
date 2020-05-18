package questions.image;

import java.io.*;

public class FileBackedImageStore implements ImageStore {

    public static final String COORD_SEPARATOR = "_";
    public static final String FILE_EXTENSION = ".out";

    private String absoluteFilePath;
    private File imageRootDir;

    public FileBackedImageStore(String absoluteFilePath) throws IOException {
        this.absoluteFilePath = absoluteFilePath;
        this.imageRootDir = new File(absoluteFilePath);
        if(imageRootDir.exists() && !imageRootDir.isDirectory())
            throw new IOException("Image Root Directory Path is not a directory");

        if(!imageRootDir.exists() && !imageRootDir.mkdir())
            throw new IOException("Image Root dir could not be created");
    }

    @Override
    public Image load(ImageIndex imageIndex) throws IOException {
        File imageFile = new File(String.format("%s%s%s%s%s%s%s%s%s%s",
                absoluteFilePath,
                File.separator,
                imageIndex.getNamespace(),
                File.separator,
                imageIndex.getIdentifier(),
                File.separator,
                imageIndex.getxCoord(),
                COORD_SEPARATOR,
                imageIndex.getyCoord(),
                FILE_EXTENSION));

        if(!imageFile.exists())
            throw new IOException("Image File does not exist");

        Image readable = null;
        try(ObjectInputStream objectInputStream = new ObjectInputStream(new FileInputStream(imageFile))) {
            try {
                readable = (Image) objectInputStream.readObject();
            }
            catch (ClassNotFoundException cx) {
                return null;
            }
        }

        return new Image(readable.getImageIndex(), readable.getContent(), readable.getImageMetadata().orElse(null));
    }

    @Override
    public void store(Image image) throws IOException {
        ImageIndex imageIndex = image.getImageIndex();
        File imageFileDir = new File(String.format("%s%s%s%s%s",
                absoluteFilePath,
                File.separator,
                imageIndex.getNamespace(),
                File.separator,
                imageIndex.getIdentifier()));

        if(!imageFileDir.exists() && !imageFileDir.mkdirs())
            throw new IOException("Unable to create image directory");

        File imageFile = new File(String.format("%s%s%s%s%s%s",
                imageFileDir.getAbsolutePath(),
                File.separator,
                imageIndex.getxCoord(),
                COORD_SEPARATOR,
                imageIndex.getyCoord(),
                FILE_EXTENSION));

        if(imageFile.exists())
            throw new IOException("Image file already exists");

        if(!imageFile.createNewFile())
            throw new IOException("Unable to create image file");

        try(ObjectOutputStream objectOutputStream = new ObjectOutputStream(new FileOutputStream(imageFile))) {
            objectOutputStream.writeObject(image);
        }
    }

    @Override
    public void update(ImageIndex imageIndex, byte[] content, ImageMetadata metadata) throws IOException {
        File imageFile = new File(String.format("%s%s%s%s%s%s%s%s%s%s",
                absoluteFilePath,
                File.separator,
                imageIndex.getNamespace(),
                File.separator,
                imageIndex.getIdentifier(),
                File.separator,
                imageIndex.getxCoord(),
                COORD_SEPARATOR,
                imageIndex.getyCoord(),
                FILE_EXTENSION));

        if(!imageFile.exists())
            throw new IOException("Image File does not exist");

        Image image = new Image(imageIndex, content, metadata);
        try(ObjectOutputStream objectOutputStream = new ObjectOutputStream(new FileOutputStream(imageFile))) {
            objectOutputStream.writeObject(image);
        }
    }
}
