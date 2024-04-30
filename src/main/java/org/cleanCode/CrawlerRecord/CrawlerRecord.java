package org.cleanCode.CrawlerRecord;

import org.cleanCode.Heading.Heading;

import java.util.List;
import java.util.StringJoiner;

public class CrawlerRecord {
    private String URL;
    private List<Heading> headings;
    private List<CrawlerRecord> subSites;
    private boolean isBroken; //Specifies if URL still works

    //Regular Constructor
    public CrawlerRecord(String URL, List<Heading> headings, List<CrawlerRecord> subSites) {
        this.URL = URL;
        this.headings = headings;
        this.subSites = subSites;
        this.isBroken = false; //If record is created with headings and subSites, this URL definitely works
    }

    //Constructor for lowest level of Record
    public CrawlerRecord(String URL, boolean isBroken) {
        this.URL = URL;
        this.isBroken = isBroken;
    }

    public String getURL() {
        return URL;
    }

    public void setURL(String URL) {
        this.URL = URL;
    }

    public List<Heading> getHeadings() {
        return headings;
    }

    public void setHeadings(List<Heading> headings) {
        this.headings = headings;
    }

    public List<CrawlerRecord> getSubSites() {
        return subSites;
    }

    public void setSubSites(List<CrawlerRecord> subSites) {
        this.subSites = subSites;
    }

    public boolean isBroken() {
        return isBroken;
    }

    public void setBroken(boolean broken) {
        isBroken = broken;
    }

    @Override
    public String toString() {
        StringJoiner joiner = new StringJoiner(", ", "CrawlerRecord{", "}");
        joiner.add("URL='" + URL + "'");

        // Convert headings to string
        if (headings != null) {
            joiner.add("headings=" + headings);
        } else {
            joiner.add("headings=null");
        }

        // Convert subSites to string
        if (subSites != null) {
            joiner.add("subSites=" + subSites);
        } else {
            joiner.add("subSites=null");
        }

        joiner.add("isBroken=" + isBroken);

        return joiner.toString();
    }

    public String toFormattedString() {
        return toFormattedString(0, 0);
    }

    private String toFormattedString(int baseNumOfTabs, int depth) {
        StringBuilder builder = new StringBuilder();

        //Convert depth, URL and isBroken to string
        appendBuilderWithNewLine(builder, baseNumOfTabs, "CrawlerRecord {");
        appendBuilderWithNewLine(builder, baseNumOfTabs, "Depth=" + depth);
        appendBuilderWithNewLine(builder, baseNumOfTabs + 1, "URL='" + URL + "',");
        appendBuilderWithNewLine(builder, baseNumOfTabs + 1, "isBroken=" + isBroken + ",");

        // Convert headings to string
        if (headings != null && subSites.size() != 0) {
            appendBuilderWithNewLine(builder, baseNumOfTabs + 1, "headings=[");
            for(int i = 0; i < headings.size(); i++) {
                appendBuilderWithNewLine(builder, baseNumOfTabs + 2, listObjectToString(headings, i));
            }
            appendBuilderWithNewLine(builder, baseNumOfTabs + 1, "],");
        } else {
            appendBuilderWithNewLine(builder, baseNumOfTabs + 1, "headings=null,");
        }

        // Convert subSites to string
        if (subSites != null && subSites.size() != 0) {
            appendBuilderWithNewLine(builder, baseNumOfTabs + 1, "subSites=[");
            for(int i = 0; i < subSites.size(); i++) {
                CrawlerRecord subSite = subSites.get(i);
                String subSiteString = subSite.toFormattedString(baseNumOfTabs + 2, depth + 1);
                builder.append(subSiteString);

                if(i < subSites.size() - 1) builder.append(",\n");
            }
            appendBuilderWithString(builder, 0, "\n");
            appendBuilderWithNewLine(builder, baseNumOfTabs + 1, "]");
        } else {
            appendBuilderWithNewLine(builder, baseNumOfTabs + 1, "subSites=null");
        }

        appendBuilderWithString(builder, baseNumOfTabs, "}");

        return builder.toString();
    }

    private void appendBuilderWithNewLine(StringBuilder builder, int numOfTabs, String lineContent) {
        builder.append(getTabsString(numOfTabs)).append(lineContent).append("\n");
    }

    private void appendBuilderWithString(StringBuilder builder, int numOfTabs, String lineContent) {
        builder.append(getTabsString(numOfTabs)).append(lineContent);
    }

    private String getTabsString(int numOfTabs) {
        return "\t".repeat(Math.max(0, numOfTabs));
    }

    private <T> String listObjectToString(List<T> list, int i) {
        if (i < list.size() - 1)    return list.get(i).toString() + ", ";
        else                        return list.get(i).toString();
    }
}
