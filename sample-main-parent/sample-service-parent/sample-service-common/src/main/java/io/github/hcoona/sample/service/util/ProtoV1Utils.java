package io.github.hcoona.sample.service.util;

import io.github.hcoona.sample.service.model.Application;
import java.util.Optional;

public final class ProtoV1Utils {
  private ProtoV1Utils() {
  }

  /**
   * Convert Application from JPA model into proto format.
   *
   * @param application JPA Application Model
   * @return Proto Application
   */
  public static io.github.hcoona.sample.service.api.v1.Application convertFromJpaModel(
      Application application) {
    io.github.hcoona.sample.service.api.v1.Application.Builder builder =
        io.github.hcoona.sample.service.api.v1.Application.newBuilder();
    Optional.ofNullable(application.getApplicationId()).ifPresent(builder::setApplicationId);
    Optional.ofNullable(application.getUser()).ifPresent(builder::setUser);
    Optional.ofNullable(application.getQueue()).ifPresent(builder::setQueue);
    Optional.ofNullable(application.getName()).ifPresent(builder::setName);
    Optional.ofNullable(application.getTrackingUrl()).ifPresent(builder::setTrackingUrl);
    Optional.ofNullable(application.getStartTime())
        .map(LocalDateTimeUtils::toEpochMilli)
        .ifPresent(builder::setStartTime);
    Optional.ofNullable(application.getFinishTime())
        .map(LocalDateTimeUtils::toEpochMilli)
        .ifPresent(builder::setFinishTime);
    Optional.ofNullable(application.getApplicationType()).ifPresent(builder::setApplicationType);
    return builder.build();
  }

  /**
   * Convert Application from proto format into JPA model.
   *
   * @param application Proto Application
   * @return JPA Application Model
   */
  public static Application convertToJpaModel(
      io.github.hcoona.sample.service.api.v1.Application application) {
    return new Application()
        .setApplicationId(application.getApplicationId())
        .setUser(application.getUser())
        .setQueue(application.getQueue())
        .setName(application.getName())
        .setTrackingUrl(application.getTrackingUrl())
        .setStartTime(LocalDateTimeUtils.fromEpochMilli(application.getStartTime()))
        .setFinishTime(LocalDateTimeUtils.fromEpochMilli(application.getFinishTime()))
        .setApplicationType(application.getApplicationType());
  }
}
