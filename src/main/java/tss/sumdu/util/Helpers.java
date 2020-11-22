package tss.sumdu.util;

public class Helpers {
    public static String normalizeURL(String url) {
        if (url.charAt(0) != '/') {
            url = "/" + url;
        }
        return url.replaceFirst("/+$", "");
    }
}
