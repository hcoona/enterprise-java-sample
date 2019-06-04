package io.github.hcoona.sample.service.repository;

import static org.assertj.core.api.Assertions.assertThat;

import io.github.hcoona.sample.service.model.Application;
import java.time.LocalDateTime;
import javax.inject.Inject;
import org.apache.deltaspike.jpa.api.transaction.TransactionScoped;
import org.apache.deltaspike.jpa.api.transaction.Transactional;
import org.jboss.weld.environment.se.Weld;
import org.jboss.weld.junit5.EnableWeld;
import org.jboss.weld.junit5.WeldInitiator;
import org.jboss.weld.junit5.WeldSetup;
import org.junit.jupiter.api.Test;

@EnableWeld
@Transactional
class ApplicationRepositoryTest {

  @WeldSetup
  WeldInitiator weld = WeldInitiator.from(new Weld()).activate(TransactionScoped.class).build();

  @Inject ApplicationRepository applicationRepository;

  @Test
  void testInjection() {
    assertThat(applicationRepository.toString()).isNotEmpty();
  }

  @Test
  void findByApplicationId() {
    Application expected =
        new Application()
            .setApplicationId("application_1558682950229_0001")
            .setUser("hcoona")
            .setQueue("default")
            .setName("test_job_1")
            .setTrackingUrl("http://127.0.0.1:8060/cluster/app/application_1558682950229_0001")
            .setStartTime(LocalDateTime.parse("2019-05-24T15:30:25"))
            .setFinishTime(LocalDateTime.parse("2019-05-24T15:30:43"))
            .setApplicationType("DistributedShell");
    Application actual =
        applicationRepository.findByApplicationId("application_1558682950229_0001");
    assertThat(actual)
        .isEqualToIgnoringGivenFields(expected, "id", "containerMap")
        .matches(app -> app.getId() == 1);
  }
}
