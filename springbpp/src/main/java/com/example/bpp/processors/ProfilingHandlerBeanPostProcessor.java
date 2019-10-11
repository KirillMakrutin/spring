package com.example.bpp.processors;

import java.lang.reflect.Proxy;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;

import com.example.bpp.annotations.Profiling;
import com.example.bpp.support.ProfilingController;

public class ProfilingHandlerBeanPostProcessor implements BeanPostProcessor
{
  private final ProfilingController profilingController = ProfilingController.newInstance();

  private final Map<String, Class> profilingBeans = new HashMap<>();

  @Override
  public Object postProcessBeforeInitialization( Object bean, String beanName ) throws BeansException
  {
    if ( bean.getClass().isAnnotationPresent( Profiling.class ) )
    {
      profilingBeans.put( beanName, bean.getClass() );
    }

    return bean;
  }

  @Override
  public Object postProcessAfterInitialization( Object bean, String beanName ) throws BeansException
  {
    if ( profilingBeans.containsKey( beanName ) )
    {
      Class beanClass = profilingBeans.get( beanName );

      return Proxy.newProxyInstance( beanClass.getClassLoader(), beanClass.getInterfaces(),
          ( proxy, method, args ) -> {
            if ( profilingController.isEnabled() )
            {
              System.out.println( "Начало" );
              long start = System.nanoTime();

              Object result = method.invoke( bean, args );

              long end = System.nanoTime();
              System.out.println( "Выполнялся " + ( end - start ) + " наносекунд" );
              System.out.println( "Конец" );

              return result;
            }
            else
            {
              return method.invoke( bean, args );
            }
          } );
    }

    return bean;
  }
}
