package leetfree;

import org.junit.Test;

import java.util.*;

import static org.junit.Assert.assertEquals;

public class _1233_RemoveSub_FoldersfromTheFilesystem {

    @Test
    public void a() {
        assertEquals(
                List.of("/a","/c/d","/c/f"),
                removeSubfolders(new String[]{"/a","/a/b","/c/d","/c/d/e","/c/f"})
        );
    }

    @Test
    public void b() {
        assertEquals(
                List.of("/a"),
                removeSubfolders(new String[]{"/a","/a/b/c","/a/b/d"})
        );
    }

    @Test
    public void c() {
        assertEquals(
                List.of("/a/b/c","/a/b/ca","/a/b/d"),
                removeSubfolders(new String[]{"/a/b/c","/a/b/ca","/a/b/d"})
        );
    }

    public List<String> removeSubfolders(String[] folder) {
        List<String> result = new ArrayList<>();

        Trie root = new Trie();

        for(String path: folder) {
            boolean isNewPath = true;
            String[] split = path.split("/");

            Trie current = root;
            for (String step: split) {
                if(!current.sons.containsKey(step)) {
                    current.sons.put(step, new Trie());
                }
                current = current.sons.get(step);

                if(current.terminal)
                    isNewPath = false;
            }

            current.terminal = true;
            if(isNewPath)
                result.add(path);
        }

        return result;
    }

    class Trie {
        Map<String, Trie> sons = new HashMap<>();
        boolean terminal = false;
    }
}
