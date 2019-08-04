package utils.numeric;

public class GCD {

    static public int find(int a, int b) {
        if (a == 0 || b == 0)
            return 0;
        if (a == b)
            return a;
        if (a > b)
            return find(a - b, b);
        return find(a, b - a);
    }
}
