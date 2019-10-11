package com.example.bpp.support;

import java.lang.management.ManagementFactory;

import javax.management.ObjectName;

public class StopperController implements StopperControllerMBean
{
  private static StopperController instance;

  private boolean running = true;

  public StopperController()
  {
    try
    {
      ManagementFactory.getPlatformMBeanServer().registerMBean( this, new ObjectName( "application", "name", "stopper" ) );
    }
    catch ( Exception e )
    {
      throw new RuntimeException( e );
    }
  }

  public static synchronized StopperController newInstance()
  {
    if ( instance == null )
    {
      instance = new StopperController();
    }

    return instance;
  }

  public boolean isRunning()
  {
    return running;
  }

  @Override
  public void setRunning( boolean running )
  {
    this.running = running;
  }
}
