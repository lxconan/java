package com.cultivation.javaBasic;

import org.junit.jupiter.api.Test;

import java.time.*;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class DateTimeTest {
    @SuppressWarnings({"UnusedAssignment", "ConstantConditions"})
    @Test
    void should_be_able_to_use_absolute_time_to_measure_duration() throws Exception {
        Duration elapsed = null;

        // TODO: please measure the execution time of `delayOneSecond` using `Instant` and `Duration`
        // <--start
        // --end-->

        delayOneSecond();

        // TODO: please measure the execution time of `delayOneSecond` using `Instant` and `Duration`
        // <--start
        // --end-->

        assertEquals(1, elapsed.getSeconds());
    }

    @Test
    void should_be_careful_when_adding_by_month() {
        LocalDate endOfJan = LocalDate.of(2016, 1, 31);
        LocalDate localDate = endOfJan.plusMonths(1);

        // TODO: please modify the following code to pass the test
        // <--start
        final LocalDate expected = null;
        // --end-->

        assertEquals(expected, localDate);
    }

    @SuppressWarnings({"ConstantConditions", "unused"})
    @Test
    void should_get_the_next_or_the_same_tuesday() {
        LocalDate date = LocalDate.of(2016, 1, 1);

        // TODO: please get the next Tuesday or the same day if today is Tuesday
        // <--start
        LocalDate nextTuesday = null;
        // --end-->

        final LocalDate expected = LocalDate.of(2016, 1, 5);

        assertEquals(expected, nextTuesday);
    }

    @Test
    void should_turn_around_if_exceeds_24_hours_for_local_time() {
        LocalTime bedTime = LocalTime.of(22, 30);
        LocalTime wakeUpTime = bedTime.plusHours(8);

        // TODO: please give expected local time directly.
        // <--start
        final LocalTime expected = null;
        // --end-->

        assertEquals(expected, wakeUpTime);
    }

    @SuppressWarnings("UnnecessaryLocalVariable")
    @Test
    void should_correctly_calculate_daylight_saving_time() {
        ZonedDateTime meeting = ZonedDateTime.of(
            LocalDateTime.of(2013, 10, 24, 8, 0, 0),
            ZoneId.of("Europe/Berlin"));

        // TODO: please arrange the meeting to 7 days later.
        // <--start
        ZonedDateTime actual = meeting;
        // --end-->

        final ZonedDateTime expected = ZonedDateTime.of(
            LocalDateTime.of(2013, 10, 31, 8, 0, 0),
            ZoneId.of("Europe/Berlin"));

        assertEquals(expected, actual);
    }

    @SuppressWarnings({"unused", "ConstantConditions"})
    @Test
    void should_format_to_iso_date_time() {
        ZonedDateTime beijingTime = ZonedDateTime.of(
            LocalDateTime.of(2018, 8, 3, 0, 0, 0),
            ZoneId.of("Asia/Shanghai"));

        // TODO: please format date time to ISO 8601 Date Time with Offset Information
        // <--start
        String formatted = null;
        // --end-->

        final String expect = "2018-08-03T00:00:00+08:00";

        assertEquals(expect, formatted);
    }

    @SuppressWarnings({"unused", "ConstantConditions"})
    @Test
    void should_be_able_to_parse_date_time() {
        final String expect = "2018-08-03T00:00:00+08:00";

        // TODO: please parse the date time string (ISO Offset format).
        // <--start
        ZonedDateTime parsed = null;
        // --end-->

        ZonedDateTime expected = ZonedDateTime.of(
            LocalDateTime.of(2018, 8, 3, 0, 0, 0),
            ZoneId.of("Asia/Shanghai"));

        // What is the behavior if we use assertEquals?
        assertTrue(parsed.isEqual(expected));
    }

    private static void delayOneSecond() throws InterruptedException {
        Thread.sleep(1000);
    }
}

/*
 * - What is an `Instant` anyway.
 * - How do you convert `LocalDateTime/LocalDate` to `Instant`.
 *
 */