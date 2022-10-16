package ua.denis.dev.bot.listeners;

import com.pengrad.telegrambot.TelegramBot;
import com.pengrad.telegrambot.UpdatesListener;
import com.pengrad.telegrambot.model.Update;
import com.pengrad.telegrambot.request.SendMessage;
import ua.denis.dev.bot.Bot;
import ua.denis.dev.db.DBHandler;
import ua.denis.dev.db.model.impl.Session;
import ua.denis.dev.db.model.impl.Teacher;
import ua.denis.dev.db.model.impl.User;

import java.util.List;

import static ua.denis.dev.bot.utils.Keyboard.*;

public class MyOwnListener implements UpdatesListener {

  private String message = "";
  private long chatId = 0;
  private long userId = 0;
  private String callBackData = "";
  private String name = "";

  private User user = null;
  private Teacher teacher = null;
  private Session session = null;

  private final TelegramBot bot = Bot.getInstance().getBot();

  @Override
  public int process(List<Update> list) {
    list.forEach(
        update -> {
          if (update.message() != null) {
            message = update.message().text();
            chatId = update.message().chat().id();
            name = update.message().from().username();
            userId = update.message().from().id();
            user = getUser();
            session = DBHandler.getInstance().getObject(userId, session);

            if (user != null) {
              if (hasSession() && hasType()) {
                if (getUser().getAccountType().equals("Teacher")) {
                  if (message.equals("\uD83D\uDD04Змінити тип аккаунту\uD83D\uDD04")) {
                    chooseType();
                    return;
                  }

                  bot.execute(
                      message("Що Ви пропонуєте зробити?").replyMarkup(actionKeyboardTeacher));
                } else if (getUser().getAccountType().equals("Student")) {
                  if (message.equals("\uD83D\uDD04Змінити тип аккаунту\uD83D\uDD04")) {
                    chooseType();
                    return;
                  }

                  if (message.equals("\uD83D\uDCDAДомашнє завдання\uD83D\uDCDA"))
                    sendMessage("Оберіть домашнє завдання по дням: ");
                  else if (message.equals("\uD83D\uDD51Розклад дзвінків\uD83D\uDD51"))
                    sendMessage("Ваш розклад дзвінків: ");
                }
                chooseAction();
              } else if (hasSession() && !hasType()) {
                chooseType();
              } else {
                hello();
                chooseType();
              }
            } else {
              deleteSession();
              chooseType();
              createSession();
            }
          }
          if (update.callbackQuery() != null) {
            chatId = update.callbackQuery().message().chat().id();
            name = update.callbackQuery().message().chat().username();
            callBackData = update.callbackQuery().data();
            userId = update.callbackQuery().from().id();
            user = getUser();
            session = DBHandler.getInstance().getObject(userId, session);
            setType();
            if (user != null) {
              if (user.isStudent())
                bot.execute(message("Що Ви хочете дізнатись: ").replyMarkup(actionKeyboardStudent));
              else if (user.isTeacher())
                bot.execute(message("Які Ваші наступні дії: ").replyMarkup(actionKeyboardTeacher));
            }
          }
        });
    return UpdatesListener.CONFIRMED_UPDATES_ALL;
  }

  private void chooseType() {
    if (user == null) mergeUser();
    bot.execute(
        message("Оберіть, будь ласка, тип облікового запису нижче: ")
            .replyMarkup(typeChooseKeyboard));
    message = "";
  }

  private void setType() {
    if (callBackData.equals("Teacher")) {
      DBHandler.getInstance().mergeObject(new User(userId, callBackData, name));
      callBackData = "";
    } else if (callBackData.equals("Student")) {
      DBHandler.getInstance().mergeObject(new User(userId, callBackData, name));
      callBackData = "";
    }
  }

  private void chooseAction() {
    if (user.isStudent()) {

    } else if (user.isTeacher()) {

    } else chooseType();
  }

  private void hello() {
    sendMessage("Вітаю Вас, " + name);
    sendMessage("\uD83D\uDC4B");
  }

  private boolean hasSession() {
    var answer = false;
    if (session != null) answer = true;
    return answer;
  }

  private boolean hasType() {
    var answer = false;
    if (user.getAccountType() != null) answer = true;
    return answer;
  }

  private void createSession() {
    DBHandler.getInstance().mergeObject(new Session(userId, 8, Session.HOURS));
  }

  private Session getSession() {
    return DBHandler.getInstance().getObject(userId, session);
  }

  private void deleteSession() {
    if (getSession() != null) DBHandler.getInstance().deleteObject(session);
  }

  private void mergeUser() {
    DBHandler.getInstance().mergeObject(new User(userId, callBackData, name));
  }

  private User getUser() {
    return DBHandler.getInstance().getObject(userId, user);
  }

  private void sendMessage(String message) {
    bot.execute(new SendMessage(chatId, message));
  }

  private SendMessage message(String message) {
    return new SendMessage(chatId, message);
  }
}
