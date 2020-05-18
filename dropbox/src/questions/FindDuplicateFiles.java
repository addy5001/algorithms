package questions;

import java.util.*;
import java.util.stream.Collectors;

public class FindDuplicateFiles {

    private final static String PATH_SEPARATOR = "/";

    public List<List<String>> findDuplicate(String[] paths) {
        return _naive(paths);
    }

    private List<List<String>> _naive(String[] paths) {
        Map<String, List<String>> contentReverseIndexMap = new HashMap<>();

        for(int i=0; i<paths.length; i++) {
            String[] files = paths[i].split(" ");
            String dir = files[0];

            for(int j=1; j<files.length; j++) {
                String[] folderContents = files[j].split("\\(");
                String content = folderContents[1].substring(0, folderContents[1].length()-1);
                contentReverseIndexMap.compute(content, (contentKey, listVal) -> {
                    if(listVal == null) {
                        listVal = new ArrayList<>();
                    }

                    listVal.add(String.format("%s%s%s", dir, PATH_SEPARATOR, folderContents[0]));
                    return listVal;
                });
            }
        }

        return contentReverseIndexMap
                .values()
                .stream()
                .filter(directories -> directories.size() > 1)
                .collect(Collectors.toList());
    }

    static class FileSystem {
        static List<String> listAllFiles(String path) { return Collections.emptyList(); }
        static boolean isDir(String path) { return false; }
        static String getContent(String path) { return null; }
    }

    public List<List<String>> _findDuplicates(String root) {
        Map<String, List<String>> contentReverseIndexMap = new HashMap<>();
        _dfs(root, "", contentReverseIndexMap);

        return contentReverseIndexMap
                .values()
                .stream()
                .filter(dupFiles -> dupFiles.size() > 1)
                .collect(Collectors.toList());
    }

    private void _dfs(String path, String parentPath, Map<String, List<String>> contentReverseIndexMap) {
        if(!FileSystem.isDir(path)) {
            String content = FileSystem.getContent(path);
            contentReverseIndexMap.compute(content, (contentKey, files) -> {
                if(files == null) {
                    files = new ArrayList<>();
                }

                files.add(parentPath);
                return files;
            });

            return;
        }

        List<String> subFiles = FileSystem.listAllFiles(path);
        for(String subFile : subFiles) {
            _dfs(subFile, parentPath.concat(PATH_SEPARATOR).concat(subFile), contentReverseIndexMap);
        }
    }

    /**
    listAllFile(String path) and isDir(String path)

    /**
     * Follow up questions
     *
     * BFS vs DFS
     * BFS explores neigbours first. This means that files which are located close to each other are also accessed one after another.
     * This is great for space locality and that's why BFS is expected to be faster.
     *
     * Very large files and false positives
     * For very large files we should do the following comparisons in this order:
     *
     * 1. compare sizes, if not equal, then files are different and stop here!
     * 2. hash them with a fast algorithm e.g. MD5 or use SHA256 (no collisions found yet), if not equal then stop here!
     * 3. compare byte by byte to avoid false positives due to collisions.
     *
     * Have you used an IDE in remote development mode?
     * For example, CLion has some options on how to compare the local files with the remote server files and then
     * decides to synchronize or not.
     *
     * Complexity
     * Runtime - Worst case (which is very unlikely to happen): O(N^2 * L) where L is the size of the maximum bytes that
     * need to be compared
     * Space - Worst case: all files are hashed and inserted in the hashmap, so O(H^2*L), H is the hash code size and
     * L is the filename size
     */
}
