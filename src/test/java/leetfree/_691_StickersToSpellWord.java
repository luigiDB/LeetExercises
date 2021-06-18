package leetfree;

import org.junit.Test;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

import static org.junit.Assert.assertEquals;

/**
 * We are given n different types of stickers. Each sticker has a lowercase English word on it.
 * You would like to spell out the given string target by cutting individual letters from your collection of stickers
 * and rearranging them. You can use each sticker more than once if you want, and you have infinite quantities of
 * each sticker.
 * Return the minimum number of stickers that you need to spell out target. If the task is impossible, return -1.
 * Note: In all test cases, all words were chosen randomly from the 1000 most common US English words, and target was
 * chosen as a concatenation of two random words.
 */
public class _691_StickersToSpellWord {
    @Test
    public void a() {
        assertEquals(3, minStickers(new String[]{"with", "example", "science"}, "thehat"));
    }

    @Test
    public void b() {
        assertEquals(-1, minStickers(new String[]{"notice", "possible"}, "basicbasic"));
    }

    public int minStickers(String[] stickers, String target) {

        Queue<Status> queue = new LinkedList<>();

        Status init = new Status();
        for (char c : target.toCharArray()) {
            init.rest[c] = (init.rest[c] + 1);
        }
        queue.offer(init);

        int level = 1;
        while (!queue.isEmpty()) {
            //count the elements of the current level
            int levelSize = queue.size();
            for (int i = 0; i < levelSize; i++) {
                Status poll = queue.poll();

                //determine next steps
                for (String sticker : stickers) {
                    if (isHelpfulSticker(poll, sticker)) {
                        Status nextStep = new Status(poll);
                        for (char c : sticker.toCharArray()) {
                            if (nextStep.rest[c] > 0) {
                                nextStep.rest[c] = nextStep.rest[c] - 1;
                            }
                        }

                        if(sum(nextStep.rest) == 0)
                            return level;

                        queue.offer(nextStep);
                    }
                }

            }
            level++;
        }
        return -1;
    }

    private int sum(int[] arr) {
        int sum = 0;
        for (int i: arr)
            sum += i;
        return sum;
    }

    private boolean isHelpfulSticker(Status objective, String sticker) {
        for (char c : sticker.toCharArray()) {
            if (objective.rest[c] > 0) {
                return true;
            }
        }
        return false;
    }

    private class Status {
        public int[] rest = new int[256];

        public Status() {
        }

        public Status(Status b) {
            rest = Arrays.copyOf(b.rest, 256);
        }
    }

    private String print(int[] arr) {
        StringBuilder sb = new StringBuilder();

        for (int i = 0; i < arr.length; i++) {
            if(arr[i] != 0)
                sb.append((char)i);
        }

        return sb.toString();
    }

}
