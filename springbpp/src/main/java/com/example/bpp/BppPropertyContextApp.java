package com.example.bpp;

import com.example.bpp.contexts.PropertyFileApplicationContext;
import com.example.bpp.services.Greeter;

public class BppPropertyContextApp
{
  public static void main( String[] args )
  {
    PropertyFileApplicationContext context = new PropertyFileApplicationContext( "context.properties" );
    context.getBean( "greeter", Greeter.class ).greeting();
  }
}
