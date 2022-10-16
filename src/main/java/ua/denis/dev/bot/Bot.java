package ua.denis.dev.bot;

import com.pengrad.telegrambot.TelegramBot;
import ua.denis.dev.bot.listeners.MyOwnListener;
import ua.denis.dev.bot.utils.BotConsts;
import ua.denis.dev.db.DBHandler;

public class Bot {
  private static Bot INSTANCE = null;
  private static TelegramBot bot = null;
  public Thread thread = new Thread(DBHandler::getInstance);

  private Bot() {
    createBot();
  }

  public static Bot getInstance() {
    if (INSTANCE == null) INSTANCE = new Bot();
    return INSTANCE;
  }

  public static TelegramBot getBot() {
    return bot;
  }
  public void startDBHandler(){
    if (!thread.isAlive()) thread.start();
  }
  private void createBot() {
    startDBHandler();
    if (bot == null) bot = new TelegramBot(BotConsts.TOKEN);
    bot.setUpdatesListener(new MyOwnListener());

  }
  public void terminateBot() {
    bot.shutdown();
  }
}
