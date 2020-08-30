package leetfree;

import org.junit.Assert;
import org.junit.Test;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/*
Design an in-memory file system to simulate the following functions:
ls: Given a path in string format. If it is a file path, return a list that only contains this file's name. If it is a
directory path, return the list of file and directory names in this directory. Your output (file and directory names
together) should in lexicographic order.
mkdir: Given a directory path that does not exist, you should make a new directory according to the path. If the middle
directories in the path don't exist either, you should create them as well. This function has void return type.
addContentToFile: Given a file path and file content in string format. If the file doesn't exist, you need to create
that file containing given content. If the file already exists, you need to append given content to original content.
This function has void return type.
readContentFromFile: Given a file path, return its content in string format.
 */
public class _588_DesignInMemoryFileSystem {

    @Test
    public void givenTest() {
        FileSystem fileSystem = new FileSystem();
        Assert.assertTrue(fileSystem.ls("/").isEmpty());
        fileSystem.mkdir("/a/b/c");
        fileSystem.addContentToFile("/a/b/c/d", "hello");
        List<String> content = fileSystem.ls("/");
        Assert.assertEquals(content.size(), 1);
        Assert.assertTrue(content.contains("a"));
        Assert.assertEquals("hello", fileSystem.readContentFromFile("/a/b/c/d"));
    }

    public class FileSystem {

        private final IElem root;

        public FileSystem() {
            root = new Folder();
        }

        public List<String> ls(String path) {
            IElem current = goToPath(path, Elem.FOLDER);
            return current.getContent();
        }

        public void mkdir(String path) {
            IElem current = goToPath(path, Elem.FOLDER);
        }

        public void addContentToFile(String filePath, String content) {
            File current = (File) goToPath(filePath, Elem.FILE);
            current.addtext(content);
        }

        public String readContentFromFile(String filePath) {
            File current = (File) goToPath(filePath, Elem.FILE);
            return current.fileContent();
        }

        private IElem goToPath(String path, Elem elem) {
            IElem current = root;
            String[] splitPath = path.split("/");
            if(splitPath.length ==0 )
                return current;
            for (int i = 1; i < splitPath.length - 1; i++) {
                current = current.getAndCreateNext(splitPath[i], Elem.FOLDER);
            }
            return current.getAndCreateNext(splitPath[splitPath.length - 1], elem);
        }

    }

    interface IElem {
        List<String> getContent();

        IElem getAndCreateNext(String folder, Elem elem);
    }

    enum Elem {
        FOLDER, FILE;
    }

    class Folder implements IElem {

        private final Map<String, IElem> content;

        public Folder() {
            content = new HashMap<>();
        }

        @Override
        public List<String> getContent() {
            return content.keySet().stream().sorted().collect(Collectors.toList());
        }

        @Override
        public IElem getAndCreateNext(String next, Elem elem) {
            switch (elem) {
                case FOLDER:
                    content.putIfAbsent(next, new Folder());
                    break;
                case FILE:
                    content.putIfAbsent(next, new File(next));
                    break;
            }
            return content.get(next);
        }
    }

    class File implements IElem {

        private String name;
        private StringBuilder content;

        public File(String name) {
            this.name = name;
            content = new StringBuilder();
        }

        @Override
        public List<String> getContent() {
            return List.of(name);
        }

        @Override
        public IElem getAndCreateNext(String folder, Elem elem) {
            throw new UnsupportedOperationException();
        }

        public void addtext(String text) {
            content.append(text);
        }

        public String fileContent() {
            return content.toString();
        }
    }
}
