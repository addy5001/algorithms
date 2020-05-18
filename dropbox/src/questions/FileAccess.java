package questions;

import java.util.List;
import java.util.Set;

public class FileAccess {

    public static class FileSystemTuple {
        String parent;
        String child;

        public FileSystemTuple(String parent, String child) {
            this.parent = parent;
            this.child = child;
        }

        public String getParent() {
            return parent;
        }

        public String getChild() {
            return child;
        }
    }

    List<FileSystemTuple> fileSystemTuples;
    Set<String> accessibleFileSystemNodes;

    public FileAccess(List<FileSystemTuple> fileSystemTuples, Set<String> accessibleFileSystemNodes) {
        this.fileSystemTuples = fileSystemTuples;
        this.accessibleFileSystemNodes = accessibleFileSystemNodes;
    }

    public boolean isAccessible(String file) {
        if(accessibleFileSystemNodes.contains(file))
            return true;

        for(FileSystemTuple tuple : fileSystemTuples) {
            if(file.equals(tuple.child)) {
                if(tuple.parent == null)
                    return accessibleFileSystemNodes.contains(file);
                else
                    return isAccessible(tuple.parent);
            }
        }

        return false;
    }
}
