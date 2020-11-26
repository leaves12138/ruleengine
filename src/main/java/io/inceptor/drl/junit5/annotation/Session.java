package io.inceptor.drl.junit5.annotation;

import javafx.application.Application;
import javafx.stage.Stage;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
public @interface Session {
    String[] value();
}
