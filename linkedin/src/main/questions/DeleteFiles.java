package questions;

import java.io.File;
import java.util.Objects;
import java.util.concurrent.TimeUnit;

public class DeleteFiles {

    public void deleteFilesBeforeDays(File root, int days) {
        _deleteFilesWithinRange(root, Long.MIN_VALUE, System.currentTimeMillis() - TimeUnit.DAYS.toMillis(days));
    }

    public void deleteFilesWithinRange(File root, long startRange, long endRange) {
        _deleteFilesWithinRange(root, startRange, endRange);
    }

    private void _deleteFilesWithinRange(File root, long startRange, long endRange) {
        if(root == null || !root.exists())
            return;

        File[] children = root.listFiles();
        if(Objects.nonNull(children)) {
            for (File child : children) {
                if (child.isDirectory()) {
                    if (Objects.isNull(child.listFiles()) || child.listFiles().length == 0) {
                        child.delete();
                    } else {
                        _deleteFilesWithinRange(child, startRange, endRange);
                    }
                } else {
                    if (child.lastModified() == startRange
                            || child.lastModified() == endRange
                            || (child.lastModified() > startRange && child.lastModified() < endRange)) {
                        child.delete();
                    }
                }
            }
        }
    }
}
