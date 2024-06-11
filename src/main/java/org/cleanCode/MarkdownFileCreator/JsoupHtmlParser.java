package org.cleanCode.MarkdownFileCreator;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

public class JsoupHtmlParser implements HtmlParser {

    @Override
    public String parse(String html) {
        if (html == null || html.isEmpty()) {
            throw new IllegalArgumentException("html is null or empty");
        }
        Document doc = Jsoup.parse(html);
        Element element = doc.firstElementChild();
        if(element == null){
            throw new NullPointerException("Element is null");
        }
        return element.text();
    }
}
