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

    //For testing purposes
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
}
