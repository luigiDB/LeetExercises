package utils.numeric;

public class StringMultiplication {

    public static String mul(String a, String b) {
        int[] result = new int[a.length() + b.length()];

        for (int i = a.length() - 1; i >= 0; i--) {
            for (int j = b.length() - 1; j >= 0; j--) {
                int aInt = a.charAt(i) - '0';
                int bInt = b.charAt(j) - '0';

                result[i + j + 1] += (aInt * bInt);
                result[i + j] += result[i + j + 1] / 10;
                result[i + j + 1] %= 10;
            }
        }

        StringBuilder sb = new StringBuilder();
        for (int i : result) {
            if (i == 0 && sb.length() == 0)
                continue;
            sb.append(i);
        }
        return sb.length() == 0 ? "0" : sb.toString();
    }
}
