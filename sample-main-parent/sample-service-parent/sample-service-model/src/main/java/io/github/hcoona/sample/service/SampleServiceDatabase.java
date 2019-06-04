package io.github.hcoona.sample.service;

import javax.enterprise.util.AnnotationLiteral;
import javax.inject.Qualifier;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.ElementType.METHOD;
import static java.lang.annotation.ElementType.PARAMETER;
import static java.lang.annotation.ElementType.TYPE;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

@Target({TYPE, METHOD, PARAMETER, FIELD})
@Retention(RUNTIME)
@Qualifier
public @interface SampleServiceDatabase {
  final class Literal
    extends AnnotationLiteral<SampleServiceDatabase>
    implements SampleServiceDatabase {
    public static final Literal INSTANCE = new Literal();
    private static final long serialVersionUID = 1L;
  }
}
