package org.cleanCode.URLHandler;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class UrlHandler {
    public static boolean startsWithHttp_s(String url) {
        return url.startsWith("http://") || url.startsWith("https://");
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
