package leetfree;

import org.junit.Assert;
import org.junit.Test;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.ListIterator;

public class _253_MeetingRoomsII {
    /*Given an array of meeting time intervals consisting of start and end times [[s1,e1],[s2,e2],...] (si < ei), find the minimum number of conference rooms required.

    For example,
    Given [[0, 30],[5, 10],[15, 20]],
    return 2.*/

    @Test
    public void testNoRoom() {
        List<Schedule> orders = new LinkedList<>();
        int rooms = determineSpace(orders);
        Assert.assertEquals(0, rooms);
    }

    @Test
    public void testOneRoom() {
        List<Schedule> orders = new LinkedList<>();
        orders.add(new Schedule(5,10));
        orders.add(new Schedule(15,20));
        int rooms = determineSpace(orders);
        Assert.assertEquals(1, rooms);
    }

    @Test
    public void testTwoRooms() {
        List<Schedule> orders = new LinkedList<>();
        orders.add(new Schedule(0,30));
        orders.add(new Schedule(5,10));
        orders.add(new Schedule(15,20));
        int rooms = determineSpace(orders);
        Assert.assertEquals(2, rooms);
    }

    @Test
    public void worstCase() {
        List<Schedule> orders = new LinkedList<>();
        orders.add(new Schedule(0,30));
        orders.add(new Schedule(0,30));
        orders.add(new Schedule(0,30));
        int rooms = determineSpace(orders);
        Assert.assertEquals(3, rooms);
    }

    private int determineSpace(List<Schedule> orders) {
        Collections.sort(orders);

        int occupiedRooms = 0;

        while (fillARoom(orders)) {
            occupiedRooms++;
        }

        return occupiedRooms;
    }

    private boolean fillARoom(List<Schedule> orders) {
        boolean scheduledANewRoom = false;
        int lastOccupiedSlot = 0;

        ListIterator<Schedule> listIterator = orders.listIterator();
        while(listIterator.hasNext()) {
            Schedule next = listIterator.next();
            if(next.getStartTime() >= lastOccupiedSlot) {
                lastOccupiedSlot = next.getOutTime();
                scheduledANewRoom = true;
                listIterator.remove();
            }
        }

        return  scheduledANewRoom;
    }

    class Schedule implements Comparable{
        Integer startTime;
        Integer outTime;

        public Schedule(Integer startTime, Integer outTime) {
            this.startTime = startTime;
            this.outTime = outTime;
        }

        public Integer getStartTime() {
            return startTime;
        }

        public Integer getOutTime() {
            return outTime;
        }

        @Override
        public int compareTo(Object o) {
            return this.getOutTime().compareTo(((Schedule)o).getOutTime());
        }
    }
}
