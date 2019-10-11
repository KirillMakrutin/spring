package com.example.bpp;

import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.example.bpp.services.Greeter;
import com.example.bpp.support.StopperController;

public class BppApp
{
  public static void main( String[] args ) throws InterruptedException
  {
    ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext( "context.xml" );
    Greeter greeter = context.getBean( Greeter.class );

    while ( StopperController.newInstance().isRunning() )
    {
      greeter.greeting();
      Thread.sleep( 1000 );
    }
  }
}
