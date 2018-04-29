package util;

public class String {

    /**
     * <p>Checks if the given string is empty or null.</p>
     * @param s The string to check
     * @return True if the string is empty or null, false if not.
     */
    public static boolean notEmpty(final java.lang.String s) {
        return s != null  && !s.isEmpty();
    }

    /**
     * <p>Converts the special characters \, &, ', <, > to HTML characters</p>
     * @param textToConvert The text to be converted.
     * @return The converted text.
     */
    public static java.lang.String convertToSpecialCharacters(java.lang.String textToConvert){
        if(!notEmpty(textToConvert)){
            return null;
        }

        StringBuilder builder = new StringBuilder();
        boolean previousWasASpace = false;
        for( char c : textToConvert.toCharArray() ) {
            if( c == ' ' ) {
                if( previousWasASpace ) {
                    builder.append("&nbsp;");
                    previousWasASpace = false;
                    continue;
                }
                previousWasASpace = true;
            } else {
                previousWasASpace = false;
            }
            switch(c) {
                case '<': builder.append("&lt;"); break;
                case '>': builder.append("&gt;"); break;
                case '&': builder.append("&amp;"); break;
                case '"': builder.append("&quot;"); break;
                case '\n': builder.append("<br>"); break;
                // We need Tab support here, because we print StackTraces as HTML
                case '\t': builder.append("&nbsp; &nbsp; &nbsp;"); break;
                default: builder.append(c);
            }
        }
        return builder.toString();
    }

    /**
     * <p>Converts the special HTML characters &quot; &amp; &apos; &&lt; &gt; to normal/standard characters</p>
     * @param textToConvert The text to be converted.
     * @return The converted text.
     */
    public static java.lang.String convertFromSpecialCharacters(java.lang.String textToConvert){
        if(!notEmpty(textToConvert)){
            return null;
        }

        textToConvert = textToConvert
                .replace("&nbsp; &nbsp; &nbsp;", "\t")
                .replace("&nbsp;", " ")
                .replace("&quot;", "\"")
                .replace("&amp;", "&")
                .replace("&apos;", "'")
                .replace("&lt;", "<")
                .replace("&gt;", ">")
                .replace("<br>", "\n");

        return textToConvert;
    }

    /**
     * <p>Converts an integer value to a string.</p>
     * @param number An integer value to be converted.
     * @return The integer value, converted to a string.
     */
    public static java.lang.String convertIntToString(int number){
        StringBuilder sb = new StringBuilder();
        sb.append("");
        sb.append(number);

        return sb.toString();
    }
}
