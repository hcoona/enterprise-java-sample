package io.github.hcoona.sample.service.model;

import java.util.Objects;
import java.util.StringJoiner;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

@Entity
@Table(
    name = "CONTAINER",
    uniqueConstraints = {@UniqueConstraint(columnNames = Container_.CONTAINER_ID)})
public class Container extends AbstractEntity {

  @ManyToOne private Application application;
  private String containerId;
  private long allocationRequestId;
  private long version;

  public Application getApplication() {
    return application;
  }

  /**
   * Associate the container with an application.
   *
   * @param application The Application
   * @return The container itself
   */
  public Container setApplication(Application application) {
    if (this.application != null) {
      this.application.getContainerMap().remove(this.getContainerId());
    }
    this.application = application;
    if (application != null) {
      application.getContainerMap().put(this.getContainerId(), this);
    }
    return this;
  }

  public String getContainerId() {
    return containerId;
  }

  public Container setContainerId(String containerId) {
    this.containerId = containerId;
    return this;
  }

  public long getAllocationRequestId() {
    return allocationRequestId;
  }

  public Container setAllocationRequestId(long allocationRequestId) {
    this.allocationRequestId = allocationRequestId;
    return this;
  }

  public long getVersion() {
    return version;
  }

  public Container setVersion(long version) {
    this.version = version;
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

    Container container = (Container) o;
    return Objects.equals(containerId, container.containerId);
  }

  @Override
  public int hashCode() {
    return Objects.hash(containerId);
  }

  @Override
  public String toString() {
    return new StringJoiner(", ", Container.class.getSimpleName() + "[", "]")
        .add("containerId='" + containerId + "'")
        .add("allocationRequestId=" + allocationRequestId)
        .add("version=" + version)
        .toString();
  }
}
