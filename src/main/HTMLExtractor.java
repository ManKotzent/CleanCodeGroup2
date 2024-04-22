import java.util.LinkedList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Class serves as an extractor object that receives a HTML String and returns lists of certain contents
 * */
public class HTMLExtractor {
    private String html;

    public HTMLExtractor(String html) {
        this.html = html;
    }

    public List<String> getUrls() {
        List<String> urls = new LinkedList<>();

        // Regular expression to match URLs
        String regex = "https?:\\/\\/(www\\.)?[-a-zA-Z0-9@:%._\\+~#=]{1,256}\\.[a-zA-Z0-9()]{1,6}\\b([-a-zA-Z0-9()@:%_\\+.~#?&//=]*)";
        //Regex as per: https://stackoverflow.com/questions/3809401/what-is-a-good-regular-expression-to-match-a-url
        //Note: Will detect URLs in comments, specifications don't tell whether the crawler should detect those, however since
        //      this implementation proves to be easier and a crawler also checking comments for potential links seems sensible
        //      this solution was chosen.
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(html);

        // Find URLs and add them to the list
        while (matcher.find()) {
            urls.add(matcher.group());
        }

        return urls;
    }

    //Potential function for removing comments to avoid getUrls reading out URLs in comments
    public String getHtmlWithNoComments() {
        // Regular expression to match HTML comments
        String regex = "<!--(.*?)-->";
        // Replace comments with an empty string
        return this.html.replaceAll(regex, "");
    }

    public List<Heading> getHeadings() {
        List<Heading> headings = new LinkedList<>();

        // Regular expression to match headings
        String regex = "<h([1-6])>(.*?)</h\\1>";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(html);

        // Find headings and add them to the list
        while (matcher.find()) {
            String headingText = matcher.group(2); // Text inside the heading tag
            int headerLevel = Integer.parseInt(matcher.group(1)); // Heading level

            // Determine the HeaderType based on the heading level
            HeaderType headerType = HeaderType.fromLevel(headerLevel);

            // Create and add the Heading object to the list
            headings.add(new Heading(headingText, headerType));
        }

        return headings;
    }
}
