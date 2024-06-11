package org.cleanCode.MarkdownFileCreator;

import org.cleanCode.Heading.HeaderType;
import org.cleanCode.Heading.Heading;

public class Formatting {

    public static String createUrlWithArrowString(int depth, String url, boolean broken){
        StringBuilder result = new StringBuilder("<br>");
        result.append("--".repeat(Math.max(0, depth)));
        result.append("> ");
        if(broken){
            result.append("broken link <a>");
        } else{
            result.append("link to <a>");
        }
        result.append(url);
        result.append("</a>\n");
        return result.toString();
    }

    public static String headerTypeToHashtags(Heading heading){
        HeaderType headerType = heading.getHeaderType();
        int amount = 0;
        switch (headerType){
            case H1 -> amount = 1;
            case H2 -> amount = 2;
            case H3 -> amount = 3;
            case H4 -> amount = 4;
            case H5 -> amount = 5;
            case H6 -> amount = 6;
        }
        return "#".repeat(Math.max(1, amount));
    }
}
