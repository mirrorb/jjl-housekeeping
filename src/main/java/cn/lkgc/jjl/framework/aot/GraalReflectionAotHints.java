package cn.lkgc.jjl.framework.aot;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 在使用了 lambda 表达式的类上面标记这个注解
 * @author ztp
 * @date 2023/8/18 11:51
*/
@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.TYPE})
public @interface GraalReflectionAotHints {
}