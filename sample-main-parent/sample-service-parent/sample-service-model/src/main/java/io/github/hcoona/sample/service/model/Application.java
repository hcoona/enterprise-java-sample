package io.github.hcoona.sample.service.model;

import java.time.LocalDateTime;
import java.util.Map;
import java.util.Objects;
import java.util.StringJoiner;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Index;
import javax.persistence.MapKey;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

@Entity
@Table(
    name = "APPLICATION",
    indexes = {
      @Index(columnList = Application_.USER),
      @Index(columnList = Application_.QUEUE),
      @Index(columnList = Application_.START_TIME),
      @Index(columnList = Application_.FINISH_TIME),
      @Index(columnList = Application_.APPLICATION_TYPE),
    },
    uniqueConstraints = {@UniqueConstraint(columnNames = Application_.APPLICATION_ID)})
public class Application extends AbstractEntity {

  @Column(length = 80)
  private String applicationId;

  @Column(length = 80)
  private String user;

  @Column(length = 120)
  private String queue;

  private String name;

  private String trackingUrl;

  private LocalDateTime startTime;

  private LocalDateTime finishTime;

  @Column(length = 40)
  private String applicationType;

  @OneToMany(mappedBy = Container_.APPLICATION, cascade = CascadeType.ALL, orphanRemoval = true)
  @MapKey(name = Container_.CONTAINER_ID)
  private Map<String, Container> containerMap;

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

  public Map<String, Container> getContainerMap() {
    return containerMap;
  }

  public Application setContainerMap(Map<String, Container> containerMap) {
    this.containerMap = containerMap;
    return this;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }

    Application that = (Application) o;
    return Objects.equals(applicationId, that.applicationId);
  }

  @Override
  public int hashCode() {
    return Objects.hash(applicationId);
  }

  @Override
  public String toString() {
    return new StringJoiner(", ", Application.class.getSimpleName() + "[", "]")
        .add("applicationId='" + applicationId + "'")
        .add("user='" + user + "'")
        .add("queue='" + queue + "'")
        .add("name='" + name + "'")
        .add("trackingUrl='" + trackingUrl + "'")
        .add("startTime=" + startTime)
        .add("finishTime=" + finishTime)
        .add("applicationType='" + applicationType + "'")
        .toString();
  }
}
