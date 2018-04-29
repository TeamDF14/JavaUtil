package util;

import org.junit.Test;

import java.lang.String;

import static org.hamcrest.core.IsNot.not;
import static org.junit.Assert.*;

public class StringTest {

    @Test
    public void notEmpty() {
        java.lang.String value = "value";
        assertTrue(util.String.notEmpty(value));

        value = null;
        assertFalse(util.String.notEmpty(value));
    }

    @Test
    public void convertToSpecialCharacters() {
        java.lang.String text = "Hello World.\tGreat job you have done. Here are some specials characters: \\, &, ', <, >\n";
        java.lang.String convertedText = "Hello World.&nbsp; &nbsp; &nbsp;Great job you have done. Here are some specials characters: \\, &amp;, ', &lt;, &gt;<br>";

        assertEquals(convertedText, util.String.convertToSpecialCharacters(text));
        assertThat(convertedText + "&amp;", not(util.String.convertToSpecialCharacters(text)));
        assertEquals(null, util.String.convertToSpecialCharacters(null));
    }

    @Test
    public void convertFromSpecialCharacters() {
        java.lang.String text = "Hello World.\tGreat job you have done. Here are some specials characters: \\, &, ', <, >\n";
        String convertedText = "Hello World.&nbsp; &nbsp; &nbsp;Great job you have done. Here are some specials characters: \\, &amp;, ', &lt;, &gt;<br>";

        assertEquals(text, util.String.convertFromSpecialCharacters(convertedText));
        assertThat(text + "Something went wrong;", not(util.String.convertFromSpecialCharacters(convertedText)));
        assertEquals(null, util.String.convertFromSpecialCharacters(null));
    }

    @Test
    public void convertIntToString() {
        assertEquals("1337", util.String.convertIntToString(1337));
        assertThat("1010", not(util.String.convertIntToString(1337)));
        assertEquals("-1", util.String.convertIntToString(-1));
        assertEquals("2147483647", util.String.convertIntToString(2147483647));
        assertEquals("-2147483648", util.String.convertIntToString(-2147483648));
    }

}