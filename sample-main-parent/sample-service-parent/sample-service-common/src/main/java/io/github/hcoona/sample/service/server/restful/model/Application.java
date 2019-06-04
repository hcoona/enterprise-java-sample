package io.github.hcoona.sample.service.server.restful.model;

import java.time.LocalDateTime;

public class Application {
  private String applicationId;

  private String user;

  private String queue;

  private String name;

  private String trackingUrl;

  private LocalDateTime startTime;

  private LocalDateTime finishTime;

  private String applicationType;

  public String getApplicationId() {
    return applicationId;
  }

  public Application setApplicationId(String applicationId) {
    this.applicationId = applicationId;
    return this;
  }

  public String getUser() {
    return user;
  }

  public Application setUser(String user) {
    this.user = user;
    return this;
  }

  public String getQueue() {
    return queue;
  }

  public Application setQueue(String queue) {
    this.queue = queue;
    return this;
  }

  public String getName() {
    return name;
  }

  public Application setName(String name) {
    this.name = name;
    return this;
  }

  public String getTrackingUrl() {
    return trackingUrl;
  }

  public Application setTrackingUrl(String trackingUrl) {
    this.trackingUrl = trackingUrl;
    return this;
  }

  public LocalDateTime getStartTime() {
    return startTime;
  }

  public Application setStartTime(LocalDateTime startTime) {
    this.startTime = startTime;
    return this;
  }

  public LocalDateTime getFinishTime() {
    return finishTime;
  }

  public Application setFinishTime(LocalDateTime finishTime) {
    this.finishTime = finishTime;
    return this;
  }

  public String getApplicationType() {
    return applicationType;
  }

  public Application setApplicationType(String applicationType) {
    this.applicationType = applicationType;
    return this;
  }
}
