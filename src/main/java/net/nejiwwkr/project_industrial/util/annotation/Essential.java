package net.nejiwwkr.project_industrial.util.annotation;

import net.nejiwwkr.project_industrial.ProjectIndustrialMod;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * <p>被该注解标记的:</p>
 * <i>方法</i> 必须被该类的<strong>每一个</strong>实例<strong>最后</strong><u>至少调用一次</u><br>
 * <i>类</i> 必须直接或间接地被{@link ProjectIndustrialMod#onInitialize()}引用<br>
 * <strike>不要觉得这些都是废话，忽略一个@Essential注解就可能引起晦涩难通的错误信息</strike>
 */
@Target({ElementType.METHOD,ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Dangerous
public @interface Essential {
}
