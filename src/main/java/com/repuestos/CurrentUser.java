
package com.repuestos;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import org.springframework.security.core.annotation.AuthenticationPrincipal;

/**
 *
 * @author andres
 */
 @Target({ ElementType.PARAMETER })
 @Retention(RetentionPolicy.RUNTIME)
 @AuthenticationPrincipal
 public @interface CurrentUser {
 }
