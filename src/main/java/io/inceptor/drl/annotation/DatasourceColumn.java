package io.inceptor.drl.annotation;

import java.lang.annotation.*;

@Target({ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@Inherited
public @interface DatasourceColumn {
    boolean id() default false;

    boolean key() default false;

    String column();
}
