package leetfree;

import org.junit.Assert;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

/*
Design a logger system that receive stream of messages along with its timestamps, each message should be printed if and
only if it is not printed in the last 10 seconds.
Given a message and a timestamp (in seconds granularity), return true if the message should be printed in the given
timestamp, otherwise returns false.
It is possible that several messages arrive roughly at the same time.
Example:
Logger logger = new Logger();
// logging string "foo" at timestamp 1
logger.shouldPrintMessage(1, "foo"); returns true;
// logging string "bar" at timestamp 2
logger.shouldPrintMessage(2,"bar"); returns true;
// logging string "foo" at timestamp 3
logger.shouldPrintMessage(3,"foo"); returns false;
// logging string "bar" at timestamp 8
logger.shouldPrintMessage(8,"bar"); returns false;
// logging string "foo" at timestamp 10
logger.shouldPrintMessage(10,"foo"); returns false;
// logging string "foo" at timestamp 11
logger.shouldPrintMessage(11,"foo"); returns true;
 */
public class __359_LoggerRateLimiter {

    @Test
    public void given() {
        Logger logger = new Logger();
        Assert.assertTrue(logger.shouldPrintMessage(1, "foo"));
        Assert.assertTrue(logger.shouldPrintMessage(2,"bar"));
        Assert.assertFalse(logger.shouldPrintMessage(3,"foo"));
        Assert.assertFalse(logger.shouldPrintMessage(8,"bar"));
        Assert.assertFalse(logger.shouldPrintMessage(10,"foo"));
        Assert.assertTrue(logger.shouldPrintMessage(11,"foo"));
    }

    class Logger {

        private final Map<String, Integer> lastOccurrences;

        public Logger() {
            lastOccurrences = new HashMap<>();
        }

        public boolean shouldPrintMessage(int timestamp, String message) {
            if(!lastOccurrences.containsKey(message)) {
                lastOccurrences.put(message, timestamp);
                return true;
            } else {
                Integer lastOccurrence = lastOccurrences.get(message);
                if(timestamp - lastOccurrence >= 10) {
                    lastOccurrences.put(message, timestamp);
                    return true;
                } else {
                    return false;
                }
            }
        }
    }
}
