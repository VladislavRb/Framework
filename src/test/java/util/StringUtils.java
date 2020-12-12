package util;

import java.util.List;

public class StringUtils {
    public static String extractSneakersInfo(String rawSneakersString) {
        int vendorCodeStartIndex = rawSneakersString.indexOf("\n");

        return rawSneakersString.substring(0, vendorCodeStartIndex);
    }

    public static String getFullPageURL(String urlWithoutLanguagePart, String language) {
        return new StringBuilder(urlWithoutLanguagePart)
                .append(language)
                .append("/")
                .toString();
    }

    public static String getShopLinkXpathByAddress(String address) {
        return new StringBuilder("//a[@target='mainframe'][contains(text(), '")
                .append(address)
                .append("')]")
                .toString();
    }

    public static String extractLangFromLandingPageURL(String landingPageURL) {
        int urlStringLength = landingPageURL.length();

        if (landingPageURL.charAt(urlStringLength - 1) == '/') {
            return landingPageURL.substring(urlStringLength - 2);
        }

        return landingPageURL.substring(urlStringLength - 3, 2);
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
