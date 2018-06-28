package com.lxconan.javaBasic;

import org.junit.Test;

import java.time.*;
import java.time.format.DateTimeFormatter;

import static org.junit.Assert.assertEquals;

public class DateTimeFacts {
    @SuppressWarnings({"ConstantConditions", "unused"})
    @Test
    public void should_be_able_to_format_local_date_time() {
        final LocalDateTime time = LocalDateTime.of(
            LocalDate.of(2018, 2, 15),
            LocalTime.of(12, 2, 0));

        // TODO: Please modify the following code to pass the test
        // <--start
        final String format = null;
        // --end-->

        assertEquals("2018/02/15 12:02:00 PM", format);
    }

    @SuppressWarnings("unused")
    @Test
    public void should_be_able_format_zoned_date_time() {
        final String interestingTimeZone = "America/Yakutat";

        final ZonedDateTime summer = ZonedDateTime.of(
            LocalDateTime.of(2018, 7, 15, 12, 2, 0),
            ZoneId.of(interestingTimeZone)
        );

        // TODO: Please modify the following code to pass the test
        // <--start
        final String formatted = null;
        // --end-->

        assertEquals("2018-07-15T12:02:00-08:00", formatted);
    }

    @Test
    public void should_be_able_to_tell_the_difference_between_zone_and_offset() {
        final String interestingTimeZone = "America/Yakutat";

        final ZonedDateTime summer = ZonedDateTime.of(
            LocalDateTime.of(2018, 7, 15, 12, 2, 0),
            ZoneId.of(interestingTimeZone)
        );
        final ZonedDateTime winter = ZonedDateTime.of(
            LocalDateTime.of(2018, 1, 15, 12, 2, 0),
            ZoneId.of(interestingTimeZone)
        );

        final String summerTimeString = DateTimeFormatter.ISO_OFFSET_DATE_TIME.format(summer);
        final String winterTimeString = DateTimeFormatter.ISO_OFFSET_DATE_TIME.format(winter);

        // TODO: Please modify the following codes the pass the test:
        // <--start
        final String expectedSummerTime = null;
        final String expectedWinterTime = null;
        // --end-->

        assertEquals(expectedSummerTime, summerTimeString);
        assertEquals(expectedWinterTime, winterTimeString);
    }

    @SuppressWarnings("unused")
    @Test
    public void should_be_able_find_the_difference_between_time() {
        final String gmt = "Etc/GMT";
        final String gmtPlusOne = "Etc/GMT+1";

        LocalDateTime localTime = LocalDateTime.of(2018, 7, 15, 12, 2, 0);
        final ZonedDateTime timeGmt = ZonedDateTime.of(
            localTime,
            ZoneId.of(gmt));
        final ZonedDateTime timeGmtPlusOne = ZonedDateTime.of(
            localTime,
            ZoneId.of(gmtPlusOne));

        // TODO: Please calculate the difference between timeGmt and timeGmtPlusOne
        // <--start
        long betweenBySeconds = 0;
        // --end-->

        assertEquals(3600, betweenBySeconds);
    }
}
