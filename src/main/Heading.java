public class Heading {
    private String heading;
    private HeaderType headerType;

    public Heading(String heading, HeaderType headerType) {
        this.heading = heading;
        this.headerType = headerType;
    }

    public String getHeading() {
        return heading;
    }

    public void setHeading(String heading) {
        this.heading = heading;
    }

    public HeaderType getHeaderType() {
        return headerType;
    }

    public void setHeaderType(HeaderType headerType) {
        this.headerType = headerType;
    }

    public String toString() {
        return "Heading{" +
                "heading='" + heading + '\'' +
                ", headerType=" + headerType +
                '}';
    }
}
