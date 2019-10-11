package com.example.bpp.services;

public class MessageGreeter implements Greeter
{
  private String message;

  @Override
  public void greeting()
  {
    System.out.println( message );
  }

  @Override
  public void whenFinallyReady()
  {
  }

  public void setMessage( String message )
  {
    this.message = message;
  }
}
