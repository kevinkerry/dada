package com.youyisi.vote.infrastructure.annotation;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
/**
 * 
 * @author shuye
 *
 */
@Retention(RetentionPolicy.SOURCE)
@Target({java.lang.annotation.ElementType.TYPE,java.lang.annotation.ElementType.METHOD})
public @interface ThreadSafe {

}
