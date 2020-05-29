package io.dreamstudio.ordering;

import io.dreamstudio.ordering.util.DateUtils;
import org.junit.Test;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;

/**
 * @author Ricky Fung
 */
public class AppTest {

    @Test
    public void testStartAndEnd() {
        LocalDateTime now = LocalDateTime.now().withMonth(2);

        System.out.println(DateUtils.getStartOfDay(now));
        System.out.println(DateUtils.getEndOfDay(now));

        LocalDate dateNow = now.toLocalDate();

        System.out.println("-----------周----------");
        System.out.println(DateUtils.getStartOfWeek(dateNow));
        System.out.println(DateUtils.getEndOfWeek(dateNow));

        System.out.println("-----------月----------");
        System.out.println(DateUtils.getStartOfMonth(dateNow));
        System.out.println(DateUtils.getEndOfMonth(dateNow));

        System.out.println("-----------年----------");
        System.out.println(DateUtils.getStartOfYear(dateNow));
        System.out.println(DateUtils.getEndOfYear(dateNow));

    }

    @Test
    public void testParseDateTime() {

        System.out.println(DateUtils.parseDateTime("2020-05-29 09:16:51"));
        System.out.println(DateUtils.parseLocalDate("2020-05-29"));

        System.out.println(DateUtils.parseDate("2020-05-29 09:16:51"));
    }

    @Test
    public void testFormatTime() {

        System.out.println(DateUtils.formatTime(new Date()));
        System.out.println(DateUtils.formatTime(new Date(), DateUtils.TIME_STANDARD_FORMAT));

        //
        LocalDateTime now = LocalDateTime.now();
        System.out.println(DateUtils.formatTime(now));
        System.out.println(DateUtils.formatTime(now, DateUtils.TIME_STANDARD_FORMAT));
    }

    @Test
    public void testFormatDate() {

        System.out.println(DateUtils.formatDate(new Date()));
        System.out.println(DateUtils.formatDate(new Date(), DateUtils.DATE_STANDARD_FORMAT));

        //
        LocalDateTime now = LocalDateTime.now();
        System.out.println(DateUtils.formatDate(now));
        System.out.println(DateUtils.formatDate(now, DateUtils.DATE_STANDARD_FORMAT));
    }

    @Test
    public void testFormat() {
        System.out.println(DateUtils.format(new Date()));
        System.out.println(DateUtils.format(new Date(), DateUtils.STANDARD_FORMAT));

        //
        LocalDateTime now = LocalDateTime.now();
        System.out.println(DateUtils.format(now));
        System.out.println(DateUtils.format(now, DateUtils.DATE_STANDARD_FORMAT));
    }
}
