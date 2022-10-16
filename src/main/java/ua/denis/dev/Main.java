package ua.denis.dev;

import java.util.Scanner;
import ua.denis.dev.bot.Bot;

public class Main {
  public static void main(String[] args) {
      Scanner scanner = new Scanner(System.in);
      Bot.getInstance();
      if (scanner.nextLine().equals("/stop")){
          Bot.getInstance().terminateBot();
          System.exit(0);
      }
  }
}
