package org.cleanCode.Heading;

public enum HeaderType {
    H1, H2, H3, H4, H5, H6;

    public static HeaderType fromLevel(int level) {
        return values()[level - 1];
    }
}
