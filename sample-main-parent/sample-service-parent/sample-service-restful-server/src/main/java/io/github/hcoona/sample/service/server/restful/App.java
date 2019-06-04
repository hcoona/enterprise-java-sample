package io.github.hcoona.sample.service.server.restful;

import java.net.URL;
import java.util.Objects;
import org.eclipse.jetty.annotations.AnnotationConfiguration;
import org.eclipse.jetty.plus.webapp.EnvConfiguration;
import org.eclipse.jetty.plus.webapp.PlusConfiguration;
import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.util.resource.PathResource;
import org.eclipse.jetty.webapp.Configuration;
import org.eclipse.jetty.webapp.FragmentConfiguration;
import org.eclipse.jetty.webapp.JettyWebXmlConfiguration;
import org.eclipse.jetty.webapp.MetaInfConfiguration;
import org.eclipse.jetty.webapp.WebAppContext;
import org.eclipse.jetty.webapp.WebInfConfiguration;
import org.eclipse.jetty.webapp.WebXmlConfiguration;

public final class App {
  /**
   * Sample Service RESTful-only Server Application.
   *
   * @param args Commandline arguments
   * @throws Exception Any exception
   */
  public static void main(String[] args) throws Exception {
    ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
    URL selfResourceUri = Objects.requireNonNull(classLoader.getResource(""));

    WebAppContext webapp = new WebAppContext();
    webapp.setConfigurations(
        new Configuration[] {
          new AnnotationConfiguration(),
          new WebInfConfiguration(),
          new WebXmlConfiguration(),
          new MetaInfConfiguration(),
          new FragmentConfiguration(),
          new EnvConfiguration(),
          new PlusConfiguration(),
          new JettyWebXmlConfiguration()
        });
    webapp.setContextPath("/");
    webapp.setClassLoader(classLoader);
    webapp.setResourceBase(selfResourceUri.toExternalForm());
    webapp.getMetaData().addContainerResource(new PathResource(selfResourceUri));

    Server server = new Server(8080);
    server.setHandler(webapp);
    server.start();
    server.join();
  }
}
