package io.github.hcoona.sample.service.server.restful;

import java.io.File;
import java.net.URL;
import java.util.Objects;
import org.eclipse.jetty.annotations.AnnotationConfiguration;
import org.eclipse.jetty.plus.webapp.EnvConfiguration;
import org.eclipse.jetty.plus.webapp.PlusConfiguration;
import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.util.resource.Resource;
import org.eclipse.jetty.webapp.Configuration;
import org.eclipse.jetty.webapp.FragmentConfiguration;
import org.eclipse.jetty.webapp.JettyWebXmlConfiguration;
import org.eclipse.jetty.webapp.MetaInfConfiguration;
import org.eclipse.jetty.webapp.WebAppContext;
import org.eclipse.jetty.webapp.WebInfConfiguration;
import org.eclipse.jetty.webapp.WebXmlConfiguration;
import org.slf4j.bridge.SLF4JBridgeHandler;

public final class App {

  static {
    SLF4JBridgeHandler.removeHandlersForRootLogger();
    SLF4JBridgeHandler.install();
  }

  private App() {
  }

  /**
   * Sample Service RESTful-only Server Application.
   *
   * @param args Commandline arguments
   * @throws Exception Any exception
   */
  public static void main(String[] args) throws Exception {
    final ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
    final File webAppResourceFile =
        new File(App.class.getProtectionDomain().getCodeSource().getLocation().toURI().getPath());
    final URL webXmlUrl = Objects.requireNonNull(classLoader.getResource("WEB-INF/web.xml"));

    WebAppContext webapp = new WebAppContext();
    webapp.setConfigurations(
        new Configuration[]{
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
    webapp.setResourceBase(webAppResourceFile.getPath());
    webapp.setDescriptor(webXmlUrl.toExternalForm());
    webapp.getMetaData().addContainerResource(Resource.newResource(webAppResourceFile));

    Server server = new Server(8080);
    server.setHandler(webapp);
    server.start();
    server.join();
  }
}
