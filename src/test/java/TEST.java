import java.util.regex.Pattern;

public class TEST {
    public static void main(String[] args) {
        String string = "4 K";
        Pattern firstDigit = Pattern.compile("[0-9].*");
        int position = string.indexOf('K');
        int res = 0;
        if (firstDigit.matcher(string).matches()) {
            if (position > 0) {
                if (string.indexOf(" ") > 0) {
                    res = (int) (Float.parseFloat(string.substring(0, position)) * 1000);
                } else res = (int) (Float.parseFloat(string.substring(0, position)) * 1000);
            } else {
                res = Integer.parseInt(string);
            }
            System.out.println(res);
        }
    }
}
