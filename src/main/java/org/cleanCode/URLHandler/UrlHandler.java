package org.cleanCode.URLHandler;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class UrlHandler {
    public static boolean startsWithHttp_s(String url) {
        return url.startsWith("http://") || url.startsWith("https://");
    }

    public static boolean isRessourceUrl(String url) {
        return  url.endsWith(".jpg") ||
                url.endsWith(".png") ||
                url.endsWith(".mp4") ||
                url.endsWith(".gif") ||
                url.endsWith(".ogv") ||
                url.endsWith(".webm") ||
                url.endsWith(".js");
    }

    public static boolean isLargeSocialMedia(String url) {
        return  url.startsWith("https://www.instagram.co") ||
                url.startsWith("https://www.facebook.co") ||
                url.startsWith("https://www.pinterest.co") ||
                url.startsWith("https://www.twitter.co") ||
                url.startsWith("https://www.x.co") ||
                url.startsWith("https://www.reddit.co");
    }

    public static String getWebsiteUrl(String url) {
        // Regular expression pattern to match website URL
        Pattern pattern = Pattern.compile("^(https?://[^/]+)");
        Matcher matcher = pattern.matcher(url);

        if (matcher.find()) {
            String websiteUrl = matcher.group(1);

            if(!websiteUrl.endsWith("/")) websiteUrl = websiteUrl + "/";

            return websiteUrl;
        } else {
            return null;
        }
    }
}
