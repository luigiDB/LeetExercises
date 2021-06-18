package leetfree;

import org.junit.Test;

import java.util.List;
import java.util.stream.Collectors;

import static org.junit.Assert.assertArrayEquals;

public class _636_ExclusiveTimeOfFunctions {

    @Test
    public void a() {
        assertArrayEquals(
                new int[]{3, 4},
                exclusiveTime(2, List.of("0:start:0", "1:start:2", "1:end:5", "0:end:6"))
        );
    }

    @Test
    public void b() {
        assertArrayEquals(
                new int[]{8},
                exclusiveTime(1, List.of("0:start:0", "0:start:2", "0:end:5", "0:start:6", "0:end:6", "0:end:7"))
        );
    }

    @Test
    public void c() {
        assertArrayEquals(
                new int[]{7, 1},
                exclusiveTime(2, List.of("0:start:0", "0:start:2", "0:end:5", "1:start:6", "1:end:6", "0:end:7"))
        );
    }

    public int[] exclusiveTime(int n, List<String> logs) {
        List<Proc> schedule = logs.stream()
                .map(Proc::new)
                .collect(Collectors.toList());
        int[] res = new int[n];

        for (int i = 1; i < schedule.size(); i++) {
            Proc pre = schedule.get(i - 1);
            Proc curr = schedule.get(i);
            if (pre.isStart) {
                if (curr.isStart)
                    res[pre.id] += (curr.timestamp - pre.timestamp);
                else
                    res[pre.id] += (curr.timestamp - pre.timestamp) + 1;
            } else {
                if (!curr.isStart)
                    res[curr.id] += (curr.timestamp - pre.timestamp);
            }
        }

        return res;
    }

    private class Proc {

        private final Integer id;
        private final boolean isStart;
        private final Integer timestamp;

        public Proc(String s) {
            String[] split = s.split(":");
            id = Integer.valueOf(split[0]);
            isStart = split[1].equals("start");
            timestamp = Integer.valueOf(split[2]);
        }
    }
}
