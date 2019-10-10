package utils.dataStructures.stack;

import java.util.Stack;

/*
Given an absolute path for a file (Unix-style), simplify it. Or in other words, convert it to the canonical path.

In a UNIX-style file system, a period . refers to the current directory. Furthermore, a double period .. moves the directory up a level. For more information, see: Absolute path vs relative path in Linux/Unix

Note that the returned canonical path must always begin with a slash /, and there must be only a single slash / between two directory names. The last directory name (if it exists) must not end with a trailing /. Also, the canonical path must be the shortest string representing the absolute path.



Example 1:

Input: "/home/"
Output: "/home"
Explanation: Note that there is no trailing slash after the last directory name.
Example 2:

Input: "/../"
Output: "/"
Explanation: Going one level up from the root directory is a no-op, as the root level is the highest level you can go.
Example 3:

Input: "/home//foo/"
Output: "/home/foo"
Explanation: In the canonical path, multiple consecutive slashes are replaced by a single one.
Example 4:

Input: "/a/./b/../../c/"
Output: "/c"
Example 5:

Input: "/a/../../b/../c//.//"
Output: "/c"
Example 6:

Input: "/a//b////c/d//././/.."
Output: "/a/b/c"
 */
public class _71_SimplifyPath {
    // 1. split the path by "/", to get String array with "directory name", ".", "..", also some blank
    // 2. loop through the array.
    // if foder name - push to stack
    // if . do nothing as it is current directory
    // if .. pop from stack as it .. for one folder back.
    // at the end of array, stack would have aproriate path.
    // construct path. from bottom of stack to top.

    public String simplifyPath(String path) {
        if (path == null || path.length() <= 0)
            return null;

        Stack<String> namesStack = new Stack<String>();
        for (String str : path.split("/")) { // split path by "/"
            if (str != null && !str.trim().isEmpty()) { // ignore null and empty strings.
                if (!str.equals(".") && !str.equals("..")) { // if the string is directory.
                    namesStack.push(str.trim());
                } else if (str.equals("..")) {
                    // pop the folder because we need to go to previous folder
                    if (!namesStack.isEmpty())
                        namesStack.pop();
                }
            }
        }

        StringBuilder result = new StringBuilder("/"); // path should start with "/"
        // constructing new path
        if (!namesStack.isEmpty()) {
            for (int i = 0; i < namesStack.size() - 1; i++)
                result.append(namesStack.get(i) + "/");
            result.append(namesStack.get(namesStack.size() - 1));
        }

        return result.toString();
    }
}
