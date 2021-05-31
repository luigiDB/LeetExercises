package facebook;

import org.junit.Test;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

import static org.junit.Assert.assertEquals;

/**
 * Given a list of N triangles with integer side lengths, determine how many different triangles there are. Two
 * triangles are considered to be the same if they can both be placed on the plane such that their vertices occupy
 * exactly the same three points.
 */
public class CountingTriangles {

    @Test
    public void a() {
        ArrayList<Sides> arr_1 = new ArrayList<>();
        arr_1.add(new Sides(7, 6, 5));
        arr_1.add(new Sides(5, 7, 6));
        arr_1.add(new Sides(8, 2, 9));
        arr_1.add(new Sides(2, 3, 4));
        arr_1.add(new Sides(2, 4, 3));
        assertEquals(3, countDistinctTriangles(arr_1));
    }

    class Sides{
        int a;
        int b;
        int c;
        Sides(int a,int b,int c){
            this.a = a;
            this.b = b;
            this.c = c;
        }
    }

    int countDistinctTriangles(ArrayList<Sides> arr) {
        // Write your code here
        Set<Set<Integer>> distinct = new HashSet<>();

        for(Sides tri: arr) {
            Set<Integer> tmp = new HashSet<>();
            tmp.add(tri.a);
            tmp.add(tri.b);
            tmp.add(tri.c);
            distinct.add(tmp);
        }
        return distinct.size();
    }

}
