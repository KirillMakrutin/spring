package com.example.bpp.services;


import com.example.bpp.annotations.AfterAll;

public class T1000Greeter extends EchoGreeter implements Greeter
{
  @Override
  public void greeting()
  {
    System.out.println( "Привет! Я жиджкий" );
  }

  @AfterAll
  @Override
  public void whenFinallyReady()
  {
    System.out.println("Я из будущего");
  }
}
