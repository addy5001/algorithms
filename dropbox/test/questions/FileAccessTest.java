package questions;

import org.junit.Assert;
import org.junit.Test;

import java.util.List;
import java.util.Set;

public class FileAccessTest {

    @Test
    public void testIsAccessible() {
        final FileAccess fileAccess = new FileAccess(
                List.of(new FileAccess.FileSystemTuple("dir1", "file1"),
                        new FileAccess.FileSystemTuple("dir1", "file2"),
                        new FileAccess.FileSystemTuple("parent1", "dir1"),
                        new FileAccess.FileSystemTuple("root", "parent1"),
                        new FileAccess.FileSystemTuple(null, "parent1"),
                        new FileAccess.FileSystemTuple("dir2", "file3"),
                        new FileAccess.FileSystemTuple("dir2", "file4"),
                        new FileAccess.FileSystemTuple("parent2", "dir2"),
                        new FileAccess.FileSystemTuple("root", "parent2"),
                        new FileAccess.FileSystemTuple("dir3", "file5"),
                        new FileAccess.FileSystemTuple("parent3", "dir3"),
                        new FileAccess.FileSystemTuple("root", "parent3")),
                Set.of("parent1", "dir3")
        );

        Assert.assertTrue(fileAccess.isAccessible("file2"));
        Assert.assertTrue(fileAccess.isAccessible("file5"));
        Assert.assertFalse(fileAccess.isAccessible("root"));
        Assert.assertFalse(fileAccess.isAccessible("file3"));
    }
}
