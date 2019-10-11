package com.example.bpp.annotations;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

@Retention( RetentionPolicy.RUNTIME )
public @interface Between
{
  int min() default 1;

  int max() default 99;
}
