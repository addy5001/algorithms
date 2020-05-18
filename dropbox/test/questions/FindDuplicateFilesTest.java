package questions;

import org.junit.Assert;
import org.junit.Test;

import java.util.List;

public class FindDuplicateFilesTest {

    final FindDuplicateFiles findDuplicateFiles = new FindDuplicateFiles();

    @Test
    public void testFindDuplicateFiles() {
        String[] test = {"root/a 1.txt(abcd) 2.txt(efgh)", "root/c 3.txt(abcd)", "root/c/d 4.txt(efgh)", "root 4.txt(efgh)"};
        List<List<String>> results = findDuplicateFiles.findDuplicate(test);
        Assert.assertEquals(2, results.size());
    }

    @Test
    public void testFindDuplicateFiles_nonDuplicates() {
        String[] test = {"root/a 1.txt(abcd) 2.txt(efsfgh)","root/c 3.txt(abdfcd)","root/c/d 4.txt(efggdfh)"};
        List<List<String>> results = findDuplicateFiles.findDuplicate(test);
        Assert.assertEquals(0, results.size());
    }
}
