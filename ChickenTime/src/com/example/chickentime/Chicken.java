package com.example.chickentime;

public class Chicken
{
  public enum ChickenStatus{
    DOSTHG, DEAD, START
  }
  
  public ChickenStatus status;
  
  public Chicken(){
    status = ChickenStatus.START;
  }
}
