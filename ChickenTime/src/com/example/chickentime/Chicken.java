package com.example.chickentime;

public class Chicken
{
  public enum ChickenStatus{
    DOSTHG, DEAD, START
  }
  
  public ChickenStatus status;
  public ChickenStatus prev;
  
  public Chicken(){
    prev = ChickenStatus.START;
    status = ChickenStatus.START;
  }
}
