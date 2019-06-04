package io.github.hcoona.sample.service.util;

import java.util.Enumeration;
import java.util.Iterator;
import java.util.NoSuchElementException;

public final class EnumerationUtils {

  private EnumerationUtils() {
  }

  /**
   * Convert the obsoleted Enumeration to Iterable.
   *
   * @param enumeration The Enumeration
   * @return The Iterable
   */
  public static <T> Iterable<T> toIterable(Enumeration<T> enumeration) {
    return () -> new Iterator<T>() {
      @Override
      public boolean hasNext() {
        return enumeration.hasMoreElements();
      }

      @Override
      public T next() {
        if (enumeration.hasMoreElements()) {
          return enumeration.nextElement();
        } else {
          throw new NoSuchElementException();
        }
      }
    };
  }

  /**
   * Convert the Iterator to obsoleted Enumeration for compatibility.
   *
   * @param iterator The Iterator
   * @return The Enumeration
   */
  public static <T> Enumeration<T> fromIterator(Iterator<T> iterator) {
    return new Enumeration<T>() {
      @Override
      public boolean hasMoreElements() {
        return iterator.hasNext();
      }

      @Override
      public T nextElement() {
        if (iterator.hasNext()) {
          return iterator.next();
        } else {
          throw new NoSuchElementException();
        }
      }
    };
  }

  /**
   * Convert the Iterable to obsoleted Enumeration for compatibility.
   *
   * @param iterable The Iterable
   * @return The Enumeration
   */
  public static <T> Enumeration<T> fromIterable(Iterable<T> iterable) {
    return fromIterator(iterable.iterator());
  }
}
