package com.pest.demo;

public abstract class Observer
{
   protected Subject subject;
   public abstract void update(Position pos);
}