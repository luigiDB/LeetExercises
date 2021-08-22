package utils.numeric;

public class StringSum {

    public static String sum(String a, String b) {

        int carry = 0;
        StringBuilder sb = new StringBuilder();
        for (int i = a.length() - 1, j = b.length() - 1; i >= 0 || j >= 0; i--, j--) {
            int partial = carry;
            partial += i >= 0 ? a.charAt(i) - '0' : 0;
            partial += j >= 0 ? b.charAt(j) - '0' : 0;
            sb.append(partial % 10);
            carry = partial / 10;
        }

        if(carry!=0)
            sb.append(carry);

        return sb.reverse().toString();
    }
}
