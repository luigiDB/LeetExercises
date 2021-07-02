package leetfree;

import org.junit.Assert;
import org.junit.Test;

import java.util.*;

import static org.hamcrest.Matchers.containsInAnyOrder;
import static org.junit.Assert.assertEquals;

/**
 * Given a list of folders, remove all sub-folders in those folders and return in any order the folders after removing.
 * If a folder[i] is located within another folder[j], it is called a sub-folder of it.
 * The format of a path is one or more concatenated strings of the form: / followed by one or more lowercase English
 * letters. For example, /leetcode and /leetcode/problems are valid paths while an empty string and / are not.
 */
public class _1233_RemoveSub_FoldersfromTheFilesystem {

    @Test
    public void a() {
        Assert.assertThat(removeSubfolders(new String[]{"/a", "/a/b", "/c/d", "/c/d/e", "/c/f"}), containsInAnyOrder(
                "/a", "/c/d", "/c/f"
        ));
    }

    @Test
    public void b() {
        assertEquals(
                List.of("/a"),
                removeSubfolders(new String[]{"/a", "/a/b/c", "/a/b/d"})
        );
    }

    @Test
    public void c() {
        Assert.assertThat(removeSubfolders(new String[]{"/a/b/c", "/a/b/ca", "/a/b/d"}), containsInAnyOrder(
                "/a/b/c", "/a/b/ca", "/a/b/d"
        ));
    }

    @Test
    public void d() {
        assertEquals(
                List.of("/ah/al"),
                removeSubfolders(new String[]{"/ah/al/am", "/ah/al"})
        );
    }

    public List<String> removeSubfolders(String[] folder) {
        List<String> result = new ArrayList<>();
        Trie root = new Trie();

        Arrays.sort(folder, Comparator.comparingInt(String::length));

        for (String path : folder) {
            boolean isNewPath = true;
            String[] split = path.split("/");

            Trie current = root;
            for (String step : split) {
                if (!current.sons.containsKey(step)) {
                    current.sons.put(step, new Trie());
                }
                current = current.sons.get(step);

                if (current.terminal)
                    isNewPath = false;
            }

            current.terminal = true;
            if (isNewPath)
                result.add(path);
        }

        return result;
    }

    class Trie {
        Map<String, Trie> sons = new HashMap<>();
        boolean terminal = false;
    }
}
