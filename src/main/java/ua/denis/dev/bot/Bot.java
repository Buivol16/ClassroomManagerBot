package ua.denis.dev.bot;

import com.pengrad.telegrambot.TelegramBot;
import ua.denis.dev.bot.listeners.MyOwnListener;
import ua.denis.dev.bot.utils.BotConsts;
import ua.denis.dev.db.DBHandler;

public class Bot {
  private static Bot INSTANCE = null;
  private static TelegramBot bot = null;

  private Bot() {
    new Thread(() -> DBHandler.getInstance()).start();
    createBot();
    bot.setUpdatesListener(new MyOwnListener());
  }

  public static Bot getInstance() {
    if (INSTANCE == null) INSTANCE = new Bot();
    return INSTANCE;
  }

  public TelegramBot getBot() {
    return bot;
  }

  private void createBot() {
    bot = new TelegramBot(BotConsts.TOKEN);
  }

  public void terminateBot() {
    bot.shutdown();
  }
}
