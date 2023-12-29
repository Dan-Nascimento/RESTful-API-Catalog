package mb.dabm.servcatapi.helper;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Helper {

    public static String unixEpochToDate(Long unix){

        return new java.text.SimpleDateFormat("dd.MM.yyyy HH:mm:ss")
            .format(new java.util.Date (unix));
    }


    /**
     * Responsavel por VRF se a String eh numerica
     *
     * @param str
     * @return
     */
    public static boolean isNumeric(String str) {
        return str.matches("-?\\d+(\\.\\d+)?");
    }

    /**
     * Responsavel por VRF se eh soh letras
     *
     * @param str
     * @return
     */
    public static boolean isAlphabeticAndLengh4(String str) {
        return str.matches("[A-Z]{4}");
    }

    /**
     * PI nos seguintes: BR9999999, BR99999-99, 199999999 OU 1999999-99
     *
     * @param cmdPi
     * @return
     */
    public static String formatarPI(String cmdPi) {
        Pattern pattern = Pattern.compile("((\\w){9}|(\\w{7}-\\w{2}))");
        Matcher match = pattern.matcher(cmdPi);
        String result = "";

        if (match.find()) {
            result = match.group();
        }

        return result;
    }

    /**
     * Responsavel por VRf a PI se tem o tam=9 carcteres ex. PI: 190063485
     * Ou se o NSN tem tam=13 ex. NSN: 2590190063485
     *
     * @param pi
     * @return
     */
    public static boolean piIsvalid(String pi) {
        boolean result = false;
        if (pi.length() == 9 || pi.length() == 13) {
            result = true;
        }

        return result;
    }
}
