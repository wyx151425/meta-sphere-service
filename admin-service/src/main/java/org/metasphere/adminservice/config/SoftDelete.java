package org.metasphere.adminservice.config;

import org.hibernate.annotations.Where;
import org.springframework.core.annotation.AliasFor;
import org.springframework.stereotype.Controller;

import java.lang.annotation.*;

/**
 * @Author: WangZhenqi
 * @Description:
 * @Date: Created in 2023-02-21 19:48
 * @Modified By:
 */
@Target({ElementType.TYPE, ElementType.METHOD, ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@Inherited
@Where(clause = "status = 1")
public @interface SoftDelete {
    @AliasFor(
            annotation = Where.class
    )
    String clause() default "status = 1";
}
