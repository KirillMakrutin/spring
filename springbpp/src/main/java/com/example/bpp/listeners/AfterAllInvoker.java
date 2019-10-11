package com.example.bpp.listeners;

import java.lang.reflect.Method;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.BeanFactoryAware;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;

import com.example.bpp.annotations.AfterAll;

public class AfterAllInvoker implements ApplicationListener<ContextRefreshedEvent>, BeanFactoryAware
{
  private ConfigurableListableBeanFactory factory;

  @Override
  public void onApplicationEvent( ContextRefreshedEvent contextRefreshedEvent )
  {
    ApplicationContext context = contextRefreshedEvent.getApplicationContext();
    String[] beanDefinitionNames = context.getBeanDefinitionNames();

    for ( String name : beanDefinitionNames )
    {
      // we need ConfigurableListableBeanFactory to find original classes
      BeanDefinition beanDefinition = factory.getBeanDefinition( name );
      String originalClassName = beanDefinition.getBeanClassName();
      try
      {
        Class<?> originalClass = Class.forName( originalClassName );
        for ( Method originalMethod : originalClass.getMethods() )
        {
          if ( originalMethod.isAnnotationPresent( AfterAll.class ) )
          {
            Object bean = context.getBean( name );
            Method currentMethod = bean.getClass().getMethod( originalMethod.getName(), originalMethod.getParameterTypes() );
            currentMethod.invoke( bean );
          }
        }
      }
      catch ( Exception e )
      {
        throw new RuntimeException( e );
      }
    }
  }

  @Override
  public void setBeanFactory( BeanFactory beanFactory ) throws BeansException
  {
    this.factory = ( ConfigurableListableBeanFactory ) beanFactory;
  }
}
