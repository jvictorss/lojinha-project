package main.java;

import main.java.rest.LojinhaController;

import java.io.FileNotFoundException;

public class Main {
  public static void main(String[] args) throws FileNotFoundException {
    LojinhaController initializer = new LojinhaController();
    initializer.iniciar();
  }
}