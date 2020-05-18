package questions;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.io.File;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

public class DeleteFilesTest {

    final DeleteFiles deleteFiles = new DeleteFiles();

    @Before
    public void init() throws IOException {
        File root = new File("/Users/aadhavan.ramesh/experimental/code-practice/algorithms/linkedin/root");
        File dir1 = new File(root.getAbsolutePath().concat("/dir1"));
        dir1.mkdir();

        File file1 = new File(dir1.getAbsolutePath().concat("/file1"));
        file1.createNewFile();
        file1.setLastModified(System.currentTimeMillis() - TimeUnit.MINUTES.toMillis(10));

        File file2 = new File(dir1.getAbsolutePath().concat("/file2"));
        file2.createNewFile();
        file2.setLastModified(System.currentTimeMillis() - TimeUnit.MINUTES.toMillis(10));

        File file3 = new File(dir1.getAbsolutePath().concat("/file3"));
        file1.createNewFile();
        file1.setLastModified(System.currentTimeMillis() - TimeUnit.MINUTES.toMillis(5));

        File dir2 = new File(root.getAbsolutePath().concat("/dir2"));
        dir2.mkdir();

        File file4 = new File(dir2.getAbsolutePath().concat("/file4"));
        file4.createNewFile();
        file4.setLastModified(System.currentTimeMillis() - TimeUnit.MINUTES.toMillis(10));

        File file5 = new File(dir2.getAbsolutePath().concat("/file5"));
        file5.createNewFile();
        file5.setLastModified(System.currentTimeMillis() - TimeUnit.MINUTES.toMillis(5));

        File file6 = new File(dir2.getAbsolutePath().concat("/file6"));
        file6.createNewFile();
        file6.setLastModified(System.currentTimeMillis() - TimeUnit.MINUTES.toMillis(5));
    }

    @Test
    public void testDeleteFilesWithinRange() {

        File root = new File("/Users/aadhavan.ramesh/experimental/code-practice/algorithms/linkedin/root");
        deleteFiles.deleteFilesWithinRange(
                root,
                Long.MIN_VALUE,
                System.currentTimeMillis() - TimeUnit.MINUTES.toMillis(7));

        Assert.assertTrue(root.listFiles().length == 0);
    }
}
