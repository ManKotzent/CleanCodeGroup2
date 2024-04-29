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
        return toStringFormattedPrivate(0);
    }

    //Note: This function was hacked together last minute to provide a more readable structure for use
    //      in the MarkdownFileCreator. Future implementations will outsource this function to its
    //      own class.
    private String toStringFormattedPrivate(int depth) {
        StringBuilder builder = new StringBuilder();
        builder.append(getTabsString(depth));
        builder.append("CrawlerRecord {\n");
        builder.append(getTabsString(depth));
        builder.append("\tURL='").append(URL).append("',\n");

        // Convert headings to string
        if (headings != null) {
            builder.append(getTabsString(depth));
            builder.append("\theadings=[\n");
            for(Heading heading : headings) {
                builder.append(getTabsString(depth + 2));
                builder.append(heading);
                builder.append(",\n");
            }
            builder.append(getTabsString(depth));
            builder.append("\t],\n");
        } else {
            builder.append(getTabsString(depth));
            builder.append("\theadings=null,\n");
        }

        // Convert subSites to string
        if (subSites != null) {
            builder.append(getTabsString(depth));
            builder.append("\tsubSites=[\n");
            for(int i = 0; i < subSites.size(); i++) {
                builder.append(subSites.get(i).toStringFormattedPrivate(depth + 2));
                if(i != subSites.size() - 1) builder.append(",\n");
            }
            builder.append("\n");
            builder.append(getTabsString(depth));
            builder.append("\t],\n");
        } else {
            builder.append(getTabsString(depth));
            builder.append("\tsubSites=null,\n");
        }

        builder.append(getTabsString(depth));
        builder.append("\tisBroken=").append(isBroken).append("\n");
        builder.append(getTabsString(depth));
        builder.append("}");

        return builder.toString();
    }

    private String getTabsString(int numOfTabs) {
        return "\t".repeat(Math.max(0, numOfTabs));
    }
}
