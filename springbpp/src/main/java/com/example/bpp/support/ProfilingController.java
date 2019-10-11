package com.example.bpp.support;

import java.lang.management.ManagementFactory;

import javax.management.ObjectName;

// to use MBean features it should implement an interface if the MBean name in the end and a set of accessible method that
// we are going to manage in MBean console
public class ProfilingController implements ProfilingControllerMBean
{
  private static ProfilingController instance;

  private boolean enabled;

  private ProfilingController()
  {
    try
    {
      ManagementFactory.getPlatformMBeanServer().registerMBean( this, new ObjectName( "profiling", "name", "controller" ) );
    }
    catch ( Exception e )
    {
      throw new RuntimeException( e );
    }
  }

  public static synchronized ProfilingController newInstance()
  {
    if ( instance == null )
    {
      instance = new ProfilingController();
    }

    return instance;
  }

  public boolean isEnabled()
  {
    return enabled;
  }

  @Override
  public void setEnabled( boolean enabled )
  {
    this.enabled = enabled;
  }
}
