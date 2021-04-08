package com.zz.annotations;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.RetentionPolicy.CLASS;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

/**
 * Created by zz on 4/6/21.
 */
@Retention(RUNTIME)
@Target(ElementType.FIELD)
public @interface MyBindView {
    int value();
}