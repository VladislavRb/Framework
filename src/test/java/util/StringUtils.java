package util;

import java.util.List;

public class StringUtils {
    public static String extractSneakersInfo(String rawSneakersString) {
        int vendorCodeStartIndex = rawSneakersString.indexOf("\n");

        return rawSneakersString.substring(0, vendorCodeStartIndex);
    }

    public static String getFullPageURL(String urlWithoutLanguagePart, String language) {
        return new StringBuilder(urlWithoutLanguagePart).append(language).append("/").toString();
    }

    public static boolean stringsListIsSorted(List<String> stringsList) {
        String maxString = "";

        for (String string: stringsList) {
            if (string.compareTo(maxString) >= 0) {
                maxString = string;
            }
            else {
                return false;
            }
        }

        return true;
    }
}
