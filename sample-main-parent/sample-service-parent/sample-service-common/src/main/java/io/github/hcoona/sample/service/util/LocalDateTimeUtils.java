package io.github.hcoona.sample.service.util;

import static java.time.ZoneOffset.UTC;

import java.time.Instant;
import java.time.LocalDateTime;

public final class LocalDateTimeUtils {
  private LocalDateTimeUtils() {
  }

  public static long toEpochMilli(LocalDateTime localDateTime) {
    return localDateTime.toInstant(UTC).toEpochMilli();
  }

  public static LocalDateTime fromEpochMilli(long epochMilli) {
    return LocalDateTime.ofInstant(Instant.ofEpochMilli(epochMilli), UTC);
  }
}
