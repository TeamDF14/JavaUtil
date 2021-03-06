package util;

import java.lang.String;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.concurrent.TimeUnit;

public class Date {

    /**
     * <p>Stores the german timeZone.</p>
     * <p>For timeZone reference, see <a href="https://garygregory.wordpress.com/2013/06/18/what-are-the-java-timezone-ids/">this link</a>. </p>
     */
    public static final TimeZone timeZone = TimeZone.getTimeZone("Europe/Berlin");

    /**
     * Compares the two dates.
     *
     * @param d1 The first date to compare.
     * @param d2 The second date to compare.
     * @return True if they represent the same day, month and year, false if not.
     */
    public static final boolean bIsDayEqual(java.util.Date d1, java.util.Date d2){

        if ((d1 == null) || (d2 == null)){
            return false;
        }

        Calendar calD1 = new GregorianCalendar(timeZone);
        Calendar calD2 = new GregorianCalendar(timeZone);

        calD1.setTime(d1);
        calD2.setTime(d2);

        return (calD1.get(Calendar.DAY_OF_YEAR) == calD2.get(Calendar.DAY_OF_YEAR))
                && (calD1.get(Calendar.MONTH) == calD2.get(Calendar.MONTH))
                && (calD1.get(Calendar.YEAR) == calD2.get(Calendar.YEAR));
    }

    /**
     * Converts a given time of type Date to String (HH:mm).
     *
     * @param date The time to be converted to a string.
     * @return The time (HH:mm) of type String
     */
    public static final java.lang.String convertTimeToString(java.util.Date date){

        if(date == null){
            return null;
        }

        java.lang.String sDate;
        SimpleDateFormat sdF = new SimpleDateFormat("HH:mm");
        sDate = sdF.format(date);

        return sDate;
    }

    /**
     * Converts the time of type String (HH:mm) to a date.
     *
     * @param sTime Contains the time as String (HH:mm)
     * @return The time of type Date
     */
    public static final java.util.Date convertStringToTime(final java.lang.String sTime){
        if (util.String.isEmpty(sTime)){
            return null;
        }
        SimpleDateFormat sdf = new SimpleDateFormat("HH:mm");
        java.util.Date date = null;
        try {
            date = sdf.parse(sTime);
        } catch (ParseException e) {
            //logger.log(Level.INFO, "Can not convert Date to String");
        }
        return date;
    }

    /**
     * <p>Converts the given date  (MM/dd/yyyy HH:mm) to its string expression.</p>
     *
     * @param date contains the time as String (MM/dd/yyyy HH:mm)
     * @return the time as Date
     */
    public static final java.util.Date convertStringToDateTime(final java.lang.String date){
        SimpleDateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy HH:mm");
        java.util.Date convertedDate = new java.util.Date();
        try {
            convertedDate = dateFormat.parse(date);
        } catch (ParseException e) {
            //logger.log(Level.INFO, "Cannot convert Date to String");
        }
        return convertedDate;
    }

    /**
     * <p>Converts the given date string to its date expression, using the provided format.</p>
     * <p>The format can be e.g. (MM/dd/yyyy HH:mm)</p>
     * @param sDate The date to be converted formatted as string.
     * @param format The desired date format.
     * @return The date string formatted as date by using the provided format.
     */
    public static final java.util.Date convertStringToDateTime(final java.lang.String sDate, String format){
        SimpleDateFormat dateFormat = new SimpleDateFormat(format);
        dateFormat.setTimeZone(timeZone);

        java.util.Date convertedDate = null;
        try {
            convertedDate = dateFormat.parse(sDate);
        } catch (ParseException e) {
            //logger.log(Level.INFO, "Cannot convert Date to String");
        }
        return convertedDate;
    }

    /**
     * Convert the date of type Date to type String (MM/dd/yyyy HH:mm)
     *
     * @param date The date to be converted.
     * @return A string in format (MM/dd/yyyy HH:mm)
     */
    public static final java.lang.String convertDateTimeToString(final java.util.Date date) {
        if (date == null) {
            return null;
        }

        // Create an instance of SimpleDateFormat used for formatting
        // the string representation of date (month/day/year)
        DateFormat df = new SimpleDateFormat("MM/dd/yyyy HH:mm");

        // Using DateFormat format method we can create a string
        // representation of a date with the defined format.
        return df.format(date);
    }

    /**
     * <p>Converts the given date to its string expression, using the provided format.</p>
     * <p>The format can be e.g. (MM/dd/yyyy HH:mm)</p>
     * @param date The date to be converted.
     * @param format The desired date format.
     * @return The date as string, converted using the given format.
     */
    public static final java.lang.String convertDateTimeToString(final java.util.Date date, final String format) {
        if (date == null || util.String.isEmpty(format)) {
            return null;
        }
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(format);
        simpleDateFormat.setTimeZone(timeZone);
        return simpleDateFormat.format(date);
    }



    /**
     * Converts the time of type String (MM/dd/yyyy) to type Date.
     *
     * @param date contains the time as String (MM/dd/yyyy HH:mm)
     * @return the time as Date
     */
    public static final java.util.Date convertStringToDate(final java.lang.String date){
        if(util.String.isEmpty(date)){
            return null;
        }

        SimpleDateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy");
        java.util.Date convertedDate = null;
        try {
            convertedDate = dateFormat.parse(date);
        } catch (ParseException e) {
            //logger.log(Level.INFO, "Cannot convert String to Date");
        }

        return convertedDate;
    }

    /**
     * <p>Converts the time of type date to the type String (MM/dd/yyyy).</p>
     * <ul>
     *     <li>Case US true: MM/dd/yyyy</li>
     *     <li>Case US false: dd.MM.YY - HH:mm</li>
     * </ul>
     *
     * @param date expect the date when the program was last opened
     * @return the time of type String
     */
    public static final String convertDateToString(final java.util.Date date, boolean US) {
        if(date == null){
            return null;
        }

        // Create an instance of SimpleDateFormat used for formatting
        // the string representation of date (month/day/year)
        DateFormat df;

        // US format
        if(US){
            df = new SimpleDateFormat("MM/dd/yyyy");
        }
        // European format
        else{
            df = new SimpleDateFormat("dd.MM.YY - HH:mm");
        }

        // Using DateFormat format method we can create a string
        // representation of a date with the defined format.
        return df.format(date);
    }

    /**
     * <p>Returns the string expression of the given weekday of type int.</p>
     * <p>For example, if <i>value</i> is set to <i>1</i>, <b>Sunday</b> is returned.</p>
     * <p>Values less than 1 or greater than 7 aren't expected and an empty string will be returned.</p>
     * @param value A weekday coded as integer
     * @param shortDesc True if the description of the day should be short (e.g. Fr. instead of Friday) or not.
     * @return The string expression of the weekday.
     */
    public static String getDayOfWeek(final int value, final boolean shortDesc) {

        String day = "";

        switch (value) {
            case 1:
                day = shortDesc ? "So." : "Sonntag";
                break;
            case 2:
                day = shortDesc ? "Mo." : "Montag";
                break;
            case 3:
                day = shortDesc ? "Di." : "Dienstag";
                break;
            case 4:
                day = shortDesc ? "Mi." : "Mittwoch";
                break;
            case 5:
                day = shortDesc ? "Do." : "Donnerstag";
                break;
            case 6:
                day = shortDesc ? "Fr." : "Freitag";
                break;
            case 7:
                day = shortDesc ? "Sa." : "Samstag";
                break;
            default:
                return day;
        }
        return day;
    }


    /**
     * Extracts the seconds out of a given date.
     * @param d The date to extract the seconds from. This date must not be null.
     * @return The seconds of the given date, e.g. <b>08</b>. If the date is null, an empty string is returned.
     */
    public static final String getSeconds(java.util.Date d){

        if (d == null){
            return "";
        }

        String sDate;
        SimpleDateFormat sdF = new SimpleDateFormat("ss");
        sDate = sdF.format(d);

        return sDate;
    }

    /**
     * Extracts the getMinutes out of a given date.
     * @param d The date to extract the getMinutes from. This date must not be null.
     * @return The getMinutes of the given date, e.g. <b>35</b>. If the date is null, an empty string is returned.
     */
    public static final String getMinutes(java.util.Date d){

        if (d == null){
            return "";
        }

        String sDate;
        SimpleDateFormat sdF = new SimpleDateFormat("mm");
        sDate = sdF.format(d);

        return sDate;
    }

    /**
     * Extracts the hours out of a given date.
     * @param d The date to extract the hours from. This date must not be null.
     * @return The hours of the given date, e.g. <b>02</b>. If the date is null, an empty string is returned.
     */
    public static final String getHours(java.util.Date d){

        if (d == null){
            return "";
        }

        String sDate;
        SimpleDateFormat sdF = new SimpleDateFormat("HH");
        sDate = sdF.format(d);

        return sDate;
    }

    /**
     * Extracts the day out of a given date.
     * @param d The date to extract the day from. This date must not be null.
     * @return The day of the given date, e.g. <b>03</b>. If the date is null, an empty string is returned.
     */
    public static final String getDay(java.util.Date d){

        if (d == null){
            return "";
        }

        String sDate;
        SimpleDateFormat sdF = new SimpleDateFormat("dd");
        sDate = sdF.format(d);

        return sDate;
    }

    /**
     * Extracts the month out of a given date.
     * @param d The date to extract the month from. This date must not be null.
     * @return The month of the given date, e.g. <b>11</b>. If the date is null, an empty string is returned.
     */
    public static final String getMonth(java.util.Date d){

        if (d == null){
            return "";
        }

        String sDate;
        SimpleDateFormat sdF = new SimpleDateFormat("MM");
        sDate = sdF.format(d);

        return sDate;
    }

    /**
     * Extracts the year out of a given date.
     * @param d The date to extract the year from. This date must not be null.
     * @return The year of the given date, e.g. <b>1992</b>. If the date is null, an empty string is returned.
     */
    public static final String getYear(java.util.Date d){

        if (d == null){
            return "";
        }

        String sDate;
        SimpleDateFormat sdF = new SimpleDateFormat("YYYY");
        sDate = sdF.format(d);

        return sDate;
    }

    /**
     * <p>Converts the given date into a format that contains hours and minutes, e.g.: </p>
     * <ul>
     *     <li>16:05</li>
     * </ul>
     * where <i>16</i> is the amount of hours and <i>05</i> is the amount of minutes.
     * <p>The given date must not be null.</p>
     * @param d The date to be converted.
     * @return A string in the specified format (see above) that contains hours and minutes. If null is provided as a date, an empty string is returned.
     */
    public static final String getTime(java.util.Date d){

        if (d == null){
            return "";
        }

        String sDate;
        SimpleDateFormat sdF = new SimpleDateFormat("HH:mm");
        sDate = sdF.format(d);

        return sDate;
    }

    /**
     * <p>Converts the given date into a format that contains the date info, e.g.: </p>
     * <ul>
     *     <li>16. Februar 2018</li>
     *     <li>16. Februar (if showYear is set to false)</li>
     * </ul>
     *
     * <p>The given date must not be null.</p>
     * @param d The date to be converted.
     * @param showYear True if the year should be shown, false if not.
     * @return A string in the specified format (see above). If null is provided as a date, an empty string is returned.
     */
    public static final String getDate(java.util.Date d, final boolean showYear){

        if (d == null){
            return "";
        }

        String sDate;
        SimpleDateFormat sdF;
        if (showYear){
            sdF = new SimpleDateFormat("dd. MMMM YYYY");
        }
        else{
            sdF = new SimpleDateFormat("dd. MMMM");
        }

        sDate = sdF.format(d);

        return sDate;
    }

    /**
     * <p>Calculates the difference in days between two dates. </p>
     * <p>The order of the two dates is not relevant for the result.</p>
     * <p>Therefore, The fields DAY_OF_YEAR, MONTH and YEAR are compared.</p>
     * @param d1 The first date.
     * @param d2 The second date.
     * @return The difference in days, sorted ascending by date.
     */
    public static ArrayList<java.util.Date> getDifference(java.util.Date d1, java.util.Date d2){

        ArrayList<java.util.Date> difference = new ArrayList<>();

        if ((d1 == null) || (d2 == null)){
            return difference;
        }

        Calendar calD1 = new GregorianCalendar(timeZone);
        Calendar calD2 = new GregorianCalendar(timeZone);
        Calendar calTemp = new GregorianCalendar(timeZone);

        calTemp.setTime(d1);
        calD1.clear();
        calD1.set(Calendar.DAY_OF_YEAR, calTemp.get(Calendar.DAY_OF_YEAR));
        calD1.set(Calendar.MONTH, calTemp.get(Calendar.MONTH));
        calD1.set(Calendar.YEAR, calTemp.get(Calendar.YEAR));

        calTemp.setTime(d2);
        calD2.clear();
        calD2.set(Calendar.DAY_OF_YEAR, calTemp.get(Calendar.DAY_OF_YEAR));
        calD2.set(Calendar.MONTH, calTemp.get(Calendar.MONTH));
        calD2.set(Calendar.YEAR, calTemp.get(Calendar.YEAR));

        java.util.Date date1 = calD2.getTime();
        java.util.Date date2 = calD1.getTime();
        long diff = date2.getTime() - date1.getTime();

        int differenceInDays = Math.abs((int) TimeUnit.DAYS.convert(diff, TimeUnit.MILLISECONDS));

        boolean d1IsEarlier = false;
        // If d1 is earlier than d2, we take calD2 to loop through the dates
        if (bIsEarlier(calD1, calD2)){
            d1IsEarlier = true;
        }

        for (int i = differenceInDays; i > 0; i--){

            // Add date to result list
            difference.add(d1IsEarlier ? calD2.getTime() : calD1.getTime());

            // Decrement the date
            (d1IsEarlier ? calD2 : calD1).set(Calendar.DAY_OF_YEAR, (d1IsEarlier ? calD2 : calD1).get(Calendar.DAY_OF_YEAR) - 1);
        }

        Collections.sort(difference);

        return difference;
    }

    /**
     * Calculates the difference between two date in minutes
     * @param d1
     * @param d2
     * @return
     */
    public static int getDifferenceInMinutes(java.util.Date d1, java.util.Date d2){
        int differenceInMinutes = 0;

        if ((d1 == null) || (d2 == null)){
            return differenceInMinutes;
        }

        long duration;
        if (d1.before(d2))
            duration  = d2.getTime() - d1.getTime();
        else
            duration  = d1.getTime() - d2.getTime();

        long longDiff = TimeUnit.MILLISECONDS.toMinutes(duration);
        return (int) (long) longDiff;
    }

    /**
     * Calculates the difference between two dates in days.
     * The order of the two dates doesn't matter.
     * @param d1 The first date.
     * @param d2 The second date.
     * @return The difference of the two dates in minutes.
     */
    public static int getDifferenceInDays(final java.util.Date d1, final java.util.Date d2){
        int diff = 0;

        if ((d1 == null) || (d2 == null)){
            return diff;
        }

        // Convert the dates to prevent a comparison with wrong formats
        java.util.Date firstDate = new java.util.Date();
        firstDate.setTime(d1.getTime());
        java.util.Date secondDate = new java.util.Date();
        secondDate.setTime(d2.getTime());

        long duration;
        if (firstDate.before(secondDate))
            duration  = secondDate.getTime() - firstDate.getTime();
        else
            duration  = firstDate.getTime() - secondDate.getTime();

        return (int) (long) TimeUnit.MILLISECONDS.toDays(duration);
    }

    /**
     * <p>Extracts all dates from the given list, going back till the latest monday and puts it in the resulting list.</p>
     * <p>The list contains at most seven unique dates, always starting from monday.</p>
     * @param list A sorted list containing unique dates. The last date in the list ist the most actual one.
     * @param lastVisibleDate The most actual date. All days before this date till the most latest monday are put into the list.
     * @param isFirstWeek True if the list is required to start from the latest possible date (which is the most actual week, including today), false if not.
     * @param switchLeft True if the older dates are requested, false if the newer dates are requested.
     * @return A list containing all dates from a calendar week.
     */
    public static ArrayList<java.util.Date> getWeekList(ArrayList<java.util.Date> list, java.util.Date lastVisibleDate, boolean isFirstWeek, boolean switchLeft){

        ArrayList<java.util.Date> newList = new ArrayList<>();
        Calendar calToStartFrom = new GregorianCalendar(timeZone);
        calToStartFrom.setFirstDayOfWeek(Calendar.MONDAY);

        if (list.isEmpty() || (lastVisibleDate == null)){
            return newList;
        }

        // Get position of lastVisibleDate in list
        int position = -1;
        for (int d = 0; d < list.size(); d++){
            if (list.get(d) == lastVisibleDate){
                position = d;
            }
        }

        // Return if the date wasn't found.
        if (position == -1){
            return newList;
        }

        // Depending on the value of switchLeft, the inrementor is set.
        int sign = (switchLeft ? -1 : 1);

        // Add the lastVisibleDate to the list if it is the most actual week, including today.
        if (isFirstWeek){
            newList.add(lastVisibleDate);
        }

        // Increment or decrement the given date by one position in the list
        position = position + sign;

        // Check if the position is still valid
        if ((position < 0) || (position >= list.size())){
            return newList;
        }


        // Set the time of the calendar to the date at the new position in the list
        calToStartFrom.setTime(list.get(position));

        // Get the week of the year from the calendar
        int woy = calToStartFrom.get(Calendar.WEEK_OF_YEAR);

        // Get all the dates of the respecting week of year, starting from the date that was set to the calendar 'calToStartFrom'
        while (calToStartFrom.get(Calendar.WEEK_OF_YEAR) == woy){

            // Add the date to the return list
            newList.add(list.get(position));

            // Change the list index
            position = position + sign;
            if ((position >= 0) && (position < list.size())){
                calToStartFrom.setTime(list.get(position));
            }
            else{
                break;
            }
        }

        // Sort the list, so that the newest date is at the last position
        Collections.sort(newList);

        return newList;
    }

    /**
     * This method checks if the given date is contained within the given array list of type date.
     * @param dToCheck The date to search for.
     * @param list The list, containing several dates.
     * @return True if the date was found in the list, false if not or the list is null.
     */
    public static boolean isDateContained (java.util.Date dToCheck, ArrayList<java.util.Date> list){

        if (list == null || dToCheck == null){
            return false;
        }

        for (java.util.Date d : list){
            if (bIsDayEqual(d,  dToCheck)){
                return true;
            }
        }
        return false;
    }

    /**
     * <p>Determines if the time of the first calendar is smaller than the one from the second.</p>
     * <p>It calculates if the time of the first calendar is smaller than or equal to the time of the second calendar</p>
     * <p><b>The correct result depends on the equal formatting of the dates!</b></p>
     * @param calCurrent The first calendar
     * @param calStored  The second calendar
     * @return The result of the comparison.
     */
    public static final boolean bIsEarlier(final Calendar calCurrent, final Calendar calStored) {

        if(calCurrent == null || calStored == null){
            return false;
        }
        return calCurrent.getTime().before(calStored.getTime());
    }

    /**
     * Checks if the first date is earlier than the second one.
     * @param d1 The first date.
     * @param d2 The second date.
     * @return True if the first date is earlier than the second one.
     */
    public static final boolean bIsEarlier(final java.util.Date d1, final java.util.Date d2){
        if (d1 == null || d2 == null)
            return false;
        return d1.before(d2);
    }

    /**
     * <p>Get the number of the day in the current year, e.g. '203'.</p>
     * @return The number of the day in the current year
     */
    public static int getDayOfCurrentYear(){
        GregorianCalendar cal = new GregorianCalendar();
        return cal.get(Calendar.DAY_OF_YEAR);
    }

    /**
     * <p>Get the week of the given date, e.g. date is '2018-01-10', the week is '02'.</p>
     * @param date The date to get the week from
     * @return The week of the given date.
     */
    public static int getWeekOfDate(final java.util.Date date){
        if (date == null)
            return 0;
        Calendar cal = new GregorianCalendar(timeZone);
        cal.setTime(date);
        return cal.get(Calendar.WEEK_OF_YEAR);
    }

    /**
     * <p>Get the first day of the given year.</p>
     * @param year The year
     * @return The first day of the given year
     */
    public static java.util.Date getFirstDayOfYear(final int year){
        GregorianCalendar cal = new GregorianCalendar();
        cal.set(Calendar.DAY_OF_YEAR, 01);
        cal.set(Calendar.MONTH, 01);
        cal.set(Calendar.YEAR, year);

        return cal.getTime();
    }

    /**
     * <p>Modifies the given type of the date by the given amount.</p>
     * <p>The type of the date can be e.g.:</p>
     * <ol>
     *     <li>Calendar.YEAR</li>
     *     <li>Calendar.MONTH</li>
     *     <li>Calendar.DAY_OF_YEAR</li>
     *     <li>etc..</li>
     * </ol>
     * <p>For example, if the year of the given date is '2018', the given type is Calendar.YEAR and the given amount is equal to '-2', the year of the resulting date will be '2016'.</p>
     * @param date The date to modify.
     * @param type The type of the date that should be modified.
     * @param amount The number of units to add/subtract.
     * @return The date which type is modified by the given amount. If there was an error, the original date is returned.
     */
    public static java.util.Date modifyDate(final java.util.Date date, final int type, final int amount){
        if (date == null || amount == 0)
            return date;

        GregorianCalendar cal = new GregorianCalendar();
        cal.setTime(date);
        cal.set(type,cal.get(type) + amount);

        java.util.Date returnDate = null;
        try{
            returnDate = cal.getTime();
        }catch (Exception e){
            return date;
        }

        return returnDate;
    }

}
