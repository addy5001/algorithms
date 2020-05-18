package questions.image;

import java.io.IOException;

public class Main {

    public static void main(String[] args) throws IOException {

//        Image image1 = ImageFactory.createImage("star1", "tid", 2, 4, "image1".getBytes());
//        Image image2 = ImageFactory.createImage("star1", "tid",2, 5, "image2".getBytes());
//        Image image3 = ImageFactory.createImage("star1", "tid",2, 6, "image3".getBytes());
//        Image image4 = ImageFactory.createImage("star1", "tid2",2, 7, "image4".getBytes());
//        Image image5 = ImageFactory.createImage("star1", "tid2",2, 8, "image5".getBytes());
//        Image image6 = ImageFactory.createImage("star1", "tid2",3, 4, "image6".getBytes());
//
        ImageManager imageManager = new ImageManagerImpl(
                new FileBackedImageStore("/Users/aadhavan.ramesh/experimental/code-practice/algorithms/imagestore"));
//        imageManager.put(image1);
//        imageManager.put(image2);
//        imageManager.put(image3);
//        imageManager.put(image4);
//        imageManager.put(image5);
//        imageManager.put(image6);
//
//        Image read1 = imageManager.get(new ImageIndex("star", "id",2, 7));
//        System.out.println(new String(read1.getContent()));
//        System.out.println(new String(imageManager.get(new ImageIndex("star", "id",2, 8)).getContent()));
//        System.out.println(new String(imageManager.get(new ImageIndex("star1", "tid",2, 4)).getContent()));
//        System.out.println(new String(imageManager.get(new ImageIndex("star1", "tid2",3, 4)).getContent()));


        imageManager.update(new ImageIndex("star1", "tid2", 3, 4), "madhu".getBytes(), new ImageMetadata(34, 24, 1080, 2));
        Image image1 = imageManager.get(new ImageIndex("star1", "tid2", 3, 4));
        System.out.println(new String(image1.getContent()));
        image1.getImageMetadata().ifPresent(System.out::println);
    }
}
