package net.nejiwwkr.project_industrial.util.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.SOURCE)
@Target({ElementType.LOCAL_VARIABLE,ElementType.PARAMETER,ElementType.FIELD})
public @interface Nickname {
    String nickname() default "";
}
