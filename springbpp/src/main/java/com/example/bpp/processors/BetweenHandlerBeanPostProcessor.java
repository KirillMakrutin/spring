package com.example.bpp.processors;

import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.Random;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;

import com.example.bpp.annotations.Between;

public class BetweenHandlerBeanPostProcessor implements BeanPostProcessor
{
  @Override
  public Object postProcessBeforeInitialization( Object bean, String beanName ) throws BeansException
  {
    Arrays.stream( bean.getClass().getDeclaredFields() ).forEach( field -> {
          if ( field.isAnnotationPresent( Between.class ) )
          {
            setBetweenParameter( bean, field );
          }
        }
    );

    return bean;
  }

  private void setBetweenParameter( Object bean, Field field )
  {
    Between between = field.getAnnotation( Between.class );
    int min = between.min();
    int max = Math.max( min, between.max() );
    int times = new Random().nextInt( max ) + min;
    try
    {
      field.setAccessible( true );
      field.set( bean, times );
    }
    catch ( IllegalAccessException e )
    {
      e.printStackTrace();
    }
  }
}
