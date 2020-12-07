package util;

public class StringUtils {
    public static String extractSneakersInfo(String rawSneakersString) {
        int vendorCodeStartIndex = rawSneakersString.indexOf("\n");

        return rawSneakersString.substring(0, vendorCodeStartIndex);
    }
}
