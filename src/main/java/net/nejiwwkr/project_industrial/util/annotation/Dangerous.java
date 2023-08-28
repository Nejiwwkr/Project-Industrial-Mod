package net.nejiwwkr.project_industrial.util.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * <p>主要是以下情况：</p>
 * <p>含有不确定因素的方法<br>
 * 尚不完全清楚内部机理的方法<br>
 * 细节决定成败的方法(调用顺序)，域<br>
 * </p>
 */
@Target({ElementType.METHOD, ElementType.FIELD,ElementType.CONSTRUCTOR,ElementType.ANNOTATION_TYPE})
@Retention(RetentionPolicy.SOURCE)
public @interface Dangerous {
    int level() default 0;
}
