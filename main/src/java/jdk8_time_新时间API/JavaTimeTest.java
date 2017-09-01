package jdk8_time_新时间API;
import java.time.Clock;
import java.time.Duration;
import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.Month;
import java.time.ZoneId;
import java.time.ZonedDateTime;

import org.junit.Test;

public class JavaTimeTest {

	@Test
	public void test0() {
		Clock clock = Clock.systemUTC();
		// 一个时间线上的瞬时时间点
		Instant instant = clock.instant();
		System.out.println(instant);
		System.out.println(clock.millis());
	}

	@Test
	public void test1() {
		Clock clock = Clock.systemUTC();

		LocalDate localDate = LocalDate.now();
		LocalDate localDateFromClock = LocalDate.now(clock);

		System.out.println("localDate = " + localDate);
		System.out.println("localDateFromClock = " + localDateFromClock);
	}

	@Test
	public void test2() {
		Clock clock = Clock.systemUTC();

		final LocalTime time = LocalTime.now();
		final LocalTime timeFromClock = LocalTime.now(clock);

		System.out.println("time = " + time);
		System.out.println("timeFromClock = " + timeFromClock);
	}

	@Test
	public void test3() {
		Clock clock = Clock.systemUTC();

		final LocalDateTime datetime = LocalDateTime.now();
		final LocalDateTime datetimeFromClock = LocalDateTime.now(clock);

		System.out.println(datetime);
		System.out.println(datetimeFromClock);
	}

	// Get the zoned date/time
	@Test
	public void test4() {
		Clock clock = Clock.systemUTC();

		final ZonedDateTime zonedDatetime = ZonedDateTime.now();
		final ZonedDateTime zonedDatetimeFromClock = ZonedDateTime.now(clock);
		final ZonedDateTime zonedDatetimeFromZone = ZonedDateTime.now(ZoneId.of("America/Los_Angeles"));

		System.out.println(zonedDatetime);
		System.out.println(zonedDatetimeFromClock);
		System.out.println(zonedDatetimeFromZone);
	}

	@Test
	public void test5() {
		// Get duration between two dates
		final LocalDateTime from = LocalDateTime.of(2014, Month.APRIL, 16, 0, 0, 0);
		final LocalDateTime to = LocalDateTime.of(2015, Month.APRIL, 16, 23, 59, 59);

		final Duration duration = Duration.between(from, to);
		System.out.println("Duration in days: " + duration.toDays());
		System.out.println("Duration in hours: " + duration.toHours());
	}

}
