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
     * @param time Contains the time as String (HH:mm)
     * @return The time of type Date
     */
    public static final java.util.Date convertStringToTime(final java.lang.String time){
        if (util.String.isEmpty(time)){
            return null;
        }

        SimpleDateFormat sdf = new SimpleDateFormat("HH:mm");
        java.util.Date date = null;

        try {
            date = sdf.parse(time);
        } catch (ParseException e) {
            //logger.log(Level.INFO, "Can not convert Date to String");
        }

        return date;
    }

    /**
     * Converts the date time (MM/dd/yyyy HH:mm) of type String to the format Date.
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
     * Convert the date of type Date to type String (MM/dd/yyyy HH:mm)
     *
     * @param date expect the date when the program was last opened
     * @return a String in format (MM/dd/yyyy HH:mm)
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
     * Determines if the time of the first calendar is smaller than the one from the second. It Calculates if the time of the first calendar is smaller than or equal to the time of the second calendar
     * @param calCurrent The first calendar
     * @param calStored  The second calendar
     * @return The result of the comparison.
     */
    public static final boolean bIsEarlier(Calendar calCurrent, Calendar calStored) {

        if(calCurrent == null || calStored == null){
            return false;
        }

        /*
        // is smaller
        if (calCurrent.get(Calendar.HOUR_OF_DAY) < calStored.get(Calendar.HOUR_OF_DAY)) {
            return true;
        }
        // is greater
        else if (calCurrent.get(Calendar.HOUR_OF_DAY) > calStored.get(Calendar.HOUR_OF_DAY)) {
            return false;
        }
        // Is equal
        else {
            // is smaller or equal
            return calCurrent.get(Calendar.MINUTE) <= calStored.get(Calendar.MINUTE);
        }
        */
        return calCurrent.getTime().before(calStored.getTime());
    }

}
