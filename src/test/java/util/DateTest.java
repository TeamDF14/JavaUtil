package util;

import org.junit.Test;

import java.lang.String;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

import static junit.framework.Assert.assertFalse;
import static junit.framework.Assert.assertTrue;
import static org.hamcrest.core.IsNot.not;
import static org.junit.Assert.*;

public class DateTest {

    @Test
    public void bIsDayEqual() {

        Calendar cal1 = new GregorianCalendar(util.Date.timeZone);
        Calendar cal2 = new GregorianCalendar(util.Date.timeZone);

        cal1.setTime(new Date());
        cal2.setTime(new Date());
        assertTrue("Days must be equal", util.Date.bIsDayEqual(cal1.getTime(), cal2.getTime()));

        cal2.set(Calendar.HOUR_OF_DAY, cal1.get(Calendar.HOUR_OF_DAY) +1 );
        assertTrue("Days must be equal", util.Date.bIsDayEqual(cal1.getTime(), cal2.getTime()));

        cal2.set(Calendar.DAY_OF_YEAR, cal1.get(Calendar.DAY_OF_YEAR) + 1);
        assertFalse("Days must not be equal", util.Date.bIsDayEqual(cal1.getTime(), cal2.getTime()));

        cal2.set(Calendar.MONTH, cal1.get(Calendar.MONTH) + 1);
        assertFalse("Days must not be equal", util.Date.bIsDayEqual(cal1.getTime(), cal2.getTime()));

        cal2.set(Calendar.YEAR, cal1.get(Calendar.YEAR) + 1);
        assertFalse("Days must not be equal", util.Date.bIsDayEqual(cal1.getTime(), cal2.getTime()));

        assertFalse("Days must not be equal", util.Date.bIsDayEqual(null, null));
        assertFalse("Days must not be equal", util.Date.bIsDayEqual(null, new Date()));
        assertFalse("Days must not be equal", util.Date.bIsDayEqual(new Date(), null));
    }

    @Test
    public void bIsDayEqual1() {

        Date dateNow = new Date();
        Date dayTomorrow = addDays(dateNow, 1);

        assertTrue(util.Date.bIsDayEqual(dateNow, dateNow));
        assertFalse(util.Date.bIsDayEqual(dayTomorrow, dateNow));
        assertFalse(util.Date.bIsDayEqual(null, dateNow));
        assertFalse(util.Date.bIsDayEqual(dateNow, null));
        assertFalse(util.Date.bIsDayEqual(null, null));
    }

    /**
     * Add days to date in java
     * @param date release date
     * @param days days to add
     * @return new date
     */
    public static Date addDays(Date date, int days) {
        GregorianCalendar cal = new GregorianCalendar();
        cal.setTime(date);
        cal.add(Calendar.DATE, days);

        return cal.getTime();
    }

    @Test
    public void convertTimeToString() {
        // Set time
        java.lang.String time = "13:37";
        Date date = util.Date.convertStringToTime(time);

        assertEquals(time, util.Date.convertTimeToString(date));
        assertThat("14:00", not(util.Date.convertTimeToString(date)));
        assertEquals(null, util.Date.convertTimeToString(null));
    }

    @Test
    public void convertStringToTime() {
        assertEquals("Thu Jan 01 14:00:00 CET 1970", util.Date.convertStringToTime("14:00").toString());
        assertThat("Thu Jan 01 21:00:00 CET 1970", not(util.Date.convertStringToTime("14:00").toString()));
        assertEquals(null, util.Date.convertStringToTime(null));
    }

    @Test
    public void convertStringToDateTime() {
        assertEquals("Sun Dec 24 20:15:00 CET 2017", util.Date.convertStringToDateTime("12/24/2017 20:15").toString());
        assertThat("Sun Dec 25 21:30:00 CET 2018", not(util.Date.convertStringToDateTime("12/24/2017 20:15").toString()));
        assertEquals(null, util.Date.convertStringToTime(null));
    }

    @Test
    public void convertDateTimeToString() {
        java.lang.String dateTime = "12/24/2017 20:15";
        Date date = util.Date.convertStringToDateTime(dateTime);

        assertEquals(dateTime, util.Date.convertDateTimeToString(date));
        assertThat("11/24/2017 20:15", not(util.Date.convertDateTimeToString(date)));
        assertEquals(null, util.Date.convertDateTimeToString(null));
    }

    @Test
    public void convertStringToDate() {
        assertEquals("Sun Dec 24 00:00:00 CET 2017", util.Date.convertStringToDate("12/24/2017").toString());
        assertThat("Sun Dec 24 00:00:00 CET 2018", not(util.Date.convertStringToDate("12/24/2017").toString()));
        assertEquals(null, util.Date.convertStringToDate(null));
    }

    @Test
    public void convertDateToString() {
        String dateTime = "12/24/2017 20:15";
        Date date = util.Date.convertStringToDateTime(dateTime);

        // Case US
        assertEquals("12/24/2017", util.Date.convertDateToString(date, true));
        assertThat("12/24/2018", not(util.Date.convertDateToString(date, true)));
        assertEquals(null, util.Date.convertDateToString(null, true));

        // Case not US
        assertEquals("24.12.17 - 20:15", util.Date.convertDateToString(date, false));
        assertThat("25.12.17 - 21:15", not(util.Date.convertDateToString(date, false)));
        assertEquals(null, util.Date.convertDateToString(null, false));
    }

    @Test
    public void getDifference() {
        Date date1 = util.Date.convertStringToDate("09/30/2013");
        Date date2 = util.Date.convertStringToDate("12/24/2017");

        assertEquals(1546, util.Date.getDifference(date1, date2).size());
        assertEquals(0, util.Date.getDifference(null, null).size());
    }

    @Test
    public void isDateContained() {
        Date date1 = util.Date.convertStringToDate("09/30/2013");
        Date date2 = util.Date.convertStringToDate("12/24/2017");
        Date date2check = util.Date.convertStringToDate("11/15/2013");
        Date date2fail = util.Date.convertStringToDate("12/25/2017");

        assertTrue(util.Date.isDateContained(date2check, util.Date.getDifference(date1, date2)));
        assertFalse(util.Date.isDateContained(date2fail, util.Date.getDifference(date1, date2)));
        assertFalse(util.Date.isDateContained(null, null));
        assertFalse(util.Date.isDateContained(date2check, null));
        assertFalse(util.Date.isDateContained(null, util.Date.getDifference(date1, date2)));
    }

    @Test
    public void bIsEarlier() {
        assertFalse(util.Date.bIsEarlier((Calendar) null, null));
        assertFalse(util.Date.bIsEarlier(null, new GregorianCalendar()));
        assertFalse(util.Date.bIsEarlier(new GregorianCalendar(), null));

        Calendar cal1 = new GregorianCalendar(util.Date.timeZone);
        Calendar cal2 = new GregorianCalendar(util.Date.timeZone);

        cal1.setTime(new Date());
        cal2.setTime(new Date());

        cal2.set(Calendar.HOUR_OF_DAY, cal1.get(Calendar.HOUR_OF_DAY) + 1);

        assertTrue(util.Date.bIsEarlier(cal1, cal2));
        assertFalse(util.Date.bIsEarlier(cal2, cal1));
        assertFalse(util.Date.bIsEarlier(cal1, cal1));

        cal2.set(Calendar.MINUTE, cal1.get(Calendar.MINUTE) + 1);

        assertTrue(util.Date.bIsEarlier(cal1, cal2));
        assertFalse(util.Date.bIsEarlier(cal2, cal1));

    }

    @Test
    public void getDayOfWeek() {
    }

    @Test
    public void getSeconds() {
        GregorianCalendar cal = new GregorianCalendar();
        cal.setTime(new Date());
        cal.set(Calendar.SECOND, 55);

        assertEquals("55", util.Date.getSeconds(cal.getTime()));
    }

    @Test
    public void getMinutes() {
        GregorianCalendar cal = new GregorianCalendar();
        cal.setTime(new Date());
        cal.set(Calendar.MINUTE, 12);

        assertEquals("12", util.Date.getMinutes(cal.getTime()));
    }

    @Test
    public void getHours() {
        GregorianCalendar cal = new GregorianCalendar();
        cal.setTime(new Date());
        cal.set(Calendar.HOUR_OF_DAY, 15);

        assertEquals("15", util.Date.getHours(cal.getTime()));

        cal.set(Calendar.HOUR_OF_DAY, 3);
        assertEquals("03", util.Date.getHours(cal.getTime()));
    }

    @Test
    public void getDay() {
        GregorianCalendar cal = new GregorianCalendar();
        cal.setTime(new Date());

        cal.set(Calendar.DAY_OF_MONTH, 31);
        cal.set(Calendar.MONTH, 12);

        assertEquals("31", util.Date.getDay(cal.getTime()));

        cal.set(Calendar.DAY_OF_MONTH, 1);
        assertEquals("01", util.Date.getDay(cal.getTime()));
    }

    @Test
    public void getMonth() {
        GregorianCalendar cal = new GregorianCalendar();
        cal.setTime(new Date());

        cal.set(Calendar.MONTH, 1);
        assertEquals("01", util.Date.getMonth(cal.getTime()));

        cal.set(Calendar.MONTH, 4);
        assertEquals("04", util.Date.getMonth(cal.getTime()));

        cal.set(Calendar.MONTH, 12);
        assertEquals("12", util.Date.getMonth(cal.getTime()));
    }

    @Test
    public void getYear() {
        GregorianCalendar cal = new GregorianCalendar();
        cal.setTime(new Date());

        cal.set(Calendar.YEAR, 1970);
        assertEquals("1970", util.Date.getYear(cal.getTime()));

        cal.set(Calendar.YEAR, 2030);
        assertEquals("2030", util.Date.getYear(cal.getTime()));

        cal.set(Calendar.YEAR, 0);
        assertEquals("0000", util.Date.getYear(cal.getTime()));
    }

    @Test
    public void getTime() {
    }

    @Test
    public void getDate() {
    }

    @Test
    public void getDifferenceInMinutes() {
    }

    @Test
    public void getDifferenceInDays() {
    }

    @Test
    public void getWeekList() {
    }




}