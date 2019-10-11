package com.example.bpp.services;

import com.example.bpp.annotations.AfterAll;
import com.example.bpp.annotations.Between;
import com.example.bpp.annotations.DeprecatedClass;
import com.example.bpp.annotations.Profiling;

@Profiling
@DeprecatedClass(newImpl = T1000Greeter.class)
public class EchoGreeter implements Greeter
{
  @Between( min = 3, max = 10 )
  private int times;

  @Override
  public void greeting()
  {
    for ( int i = 1; i <= times; i++ )
      System.out.println( "Вижу тебя, как наяву..." );
  }

  @AfterAll
  public void whenFinallyReady()
  {
    System.out.println( "Я готов" );
  }

}
