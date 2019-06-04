package io.github.hcoona.sample.service.server.restful.filter;

import static io.github.hcoona.sample.service.server.restful.filter.Constants.REQUEST_ID;

import io.github.hcoona.sample.service.util.EnumerationUtils;
import java.io.IOException;
import java.util.Enumeration;
import java.util.Optional;
import java.util.Set;
import java.util.UUID;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;
import org.slf4j.MDC;

@WebFilter(filterName = "RequestIdFilter", urlPatterns = "/*")
public class RequestIdFilter implements Filter {

  @Override
  public void init(FilterConfig filterConfig) throws ServletException {
    // Do nothing.
  }

  @Override
  public void doFilter(
      ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain)
      throws IOException, ServletException {
    if (servletRequest instanceof HttpServletRequest) {
      final HttpServletRequest httpServletRequest = (HttpServletRequest) servletRequest;
      final String requestId = Optional
          .ofNullable(httpServletRequest.getHeader(REQUEST_ID))
          .orElseGet(() -> UUID.randomUUID().toString());
      MDC.put("requestId", requestId);
      filterChain.doFilter(
          new HttpServletRequestWrapper(httpServletRequest) {
            @Override
            public Enumeration<String> getHeaderNames() {
              Set<String> headerNames = StreamSupport
                  .stream(EnumerationUtils.toIterable(super.getHeaderNames()).spliterator(), false)
                  .collect(Collectors.toSet());
              if (headerNames.stream().anyMatch(n -> n.equalsIgnoreCase(REQUEST_ID))) {
                return super.getHeaderNames();
              } else {
                headerNames.add(REQUEST_ID);
                return EnumerationUtils.fromIterable(headerNames);
              }
            }

            @Override
            public String getHeader(String name) {
              if (name.equalsIgnoreCase(REQUEST_ID)) {
                return requestId;
              } else {
                return super.getHeader(name);
              }
            }
          },
          servletResponse);
    } else {
      filterChain.doFilter(servletRequest, servletResponse);
    }
  }

  @Override
  public void destroy() {
    // Do nothing.
  }
}
