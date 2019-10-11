package com.example.bpp.processors;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.config.BeanFactoryPostProcessor;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;

import com.example.bpp.annotations.DeprecatedClass;

public class DeprecationHandlerBeanFactoryPostProcessor implements BeanFactoryPostProcessor
{
  @Override
  public void postProcessBeanFactory( ConfigurableListableBeanFactory beanFactory ) throws BeansException
  {
    for ( String name : beanFactory.getBeanDefinitionNames() )
    {
      BeanDefinition beanDefinition = beanFactory.getBeanDefinition( name );
      String beanClassName = beanDefinition.getBeanClassName();
      try
      {
        Class<?> beanClass = Class.forName( beanClassName );
        if ( beanClass.isAnnotationPresent( DeprecatedClass.class ) )
        {
          DeprecatedClass deprecatedClass = beanClass.getAnnotation( DeprecatedClass.class );
          beanDefinition.setBeanClassName( deprecatedClass.newImpl().getName() );
        }
      }
      catch ( Exception e )
      {
        throw new RuntimeException( e );
      }
    }
  }
}
