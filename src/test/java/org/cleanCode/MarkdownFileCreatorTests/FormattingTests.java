package org.cleanCode.MarkdownFileCreatorTests;

import org.cleanCode.Heading.HeaderType;
import org.cleanCode.Heading.Heading;
import org.cleanCode.MarkdownFileCreator.Formatting;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class FormattingTests {

    @Test
    public void testHeaderTypeToHashtags1() {
        Heading heading = new Heading("test", HeaderType.H1);
        assertEquals("#",Formatting.headerTypeToHashtags(heading));
    }

    @Test
    public void testHeaderTypeToHashtags2() {
        Heading heading = new Heading("test", HeaderType.H2);
        assertEquals("##",Formatting.headerTypeToHashtags(heading));
    }

    @Test
    public void testHeaderTypeToHashtags3() {
        Heading heading = new Heading("test", HeaderType.H3);
        assertEquals("###",Formatting.headerTypeToHashtags(heading));
    }

    @Test
    public void testHeaderTypeToHashtags4() {
        Heading heading = new Heading("test", HeaderType.H4);
        assertEquals("####",Formatting.headerTypeToHashtags(heading));
    }

    @Test
    public void testHeaderTypeToHashtags5() {
        Heading heading = new Heading("test", HeaderType.H5);
        assertEquals("#####",Formatting.headerTypeToHashtags(heading));
    }

    @Test
    public void testHeaderTypeToHashtags6() {
        Heading heading = new Heading("test", HeaderType.H6);
        assertEquals("######",Formatting.headerTypeToHashtags(heading));
    }

    @Test
    public void testCreateUrlWithArrowString1(){
        assertEquals("<br>--> link to <a>url</a>\n",Formatting.createUrlWithArrowString(1,"url",false));
    }

    @Test
    public void testCreateUrlWithArrowString2(){
        assertEquals("<br>----> link to <a>url</a>\n",Formatting.createUrlWithArrowString(2,"url",false));
    }

    @Test
    public void testCreateUrlWithArrowString3(){
        assertEquals("<br>--> broken link <a>url</a>\n",Formatting.createUrlWithArrowString(1,"url",true));
    }
}
