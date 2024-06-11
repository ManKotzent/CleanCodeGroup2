package org.cleanCode.MarkdownFileCreator;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

public class JsoupHtmlParser implements HtmlParser {

    @Override
    public String parse(String html) {
        Document doc = Jsoup.parse(html);
        Element element = doc.firstElementChild();
        if(element == null){
            throw new NullPointerException("Element is null");
        }
        return element.text();
    }
}
