package io.github.hcoona.sample.service.util;

import io.github.hcoona.sample.service.server.restful.model.Application;
import java.util.Optional;

public class PresentationModelUtils {
  private PresentationModelUtils() {
  }

  /**
   * Convert Application from proto format into presentation layer model.
   *
   * @param application Proto Application
   * @return Presentation layer model Application
   */
  public static Application convertFromProto(
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

  /**
   * Convert Application from presentation layer model into proto format.
   *
   * @param application Presentation layer model Application
   * @return Proto Application
   */
  public static io.github.hcoona.sample.service.api.v1.Application convertToProto(
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
}
