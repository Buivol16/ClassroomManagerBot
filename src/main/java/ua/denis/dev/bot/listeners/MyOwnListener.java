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

  private TelegramBot bot = Bot.getBot();

  @Override
  public int process(List<Update> list) {
    list.forEach(
        update -> {
          if (update.message() != null) {
            message = update.message().text();
            chatId = update.message().chat().id();
            name = update.message().from().firstName();
            userId = update.message().from().id();
            user = getUser();
            session = getSession();
            if (user != null) {
              if (session == null) {
                mergeSession();
                hello();
                selectAction();
              }
              if (user.isStudent()){
                if (message.equals("\uD83D\uDCDAДомашнє завдання\uD83D\uDCDA")) {

                }if (message.equals("\uD83D\uDD51Розклад дзвінків\uD83D\uDD51")){

                }else sendMessage("Невідома команда. Спробуйте змінити тип акаунту.");
              }else if (user.isTeacher()){
                if (message.equals("➕Додати новий клас")) {

                }else if (message.equals("✏Змінити вже існуючий клас")){

                }else sendMessage("Невідома команда. Спробуйте змінити тип акаунту.");
              }else if (message.equals("\uD83D\uDD04Змінити тип аккаунту\uD83D\uDD04")) chooseType();

            } else if (user == null) {
              mergeSession();
              mergeUser();
              chooseType();
            }
          }
          if (update.callbackQuery() != null) {
            chatId = update.callbackQuery().message().chat().id();
            name = update.callbackQuery().message().chat().username();
            callBackData = update.callbackQuery().data();
            userId = update.callbackQuery().from().id();
            user = getUser();
            session = getSession();
            setType();
            if (user != null) {
              if (user.isStudent())
                bot.execute(message("Які Ваші наступні дії: ").replyMarkup(actionKeyboardStudent));
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

  private void selectAction(){
    if (user.isStudent()) bot.execute(message("Що ви хочете дізнатись?").replyMarkup(actionKeyboardStudent));
    else if (user.isTeacher()) bot.execute(message("Що Ви хочете зробити?: ").replyMarkup(actionKeyboardTeacher));
  }

  private void hello() {
    sendMessage("Вітаю Вас, " + name);
    sendMessage("\uD83D\uDC4B");
  }

  private void chooseDate(){
    bot.execute(message("\uD83D\uDCC5Оберіть дату\uD83D\uDCC5: ").replyMarkup(dateKeyboard));
  }

  private void mergeSession() {
    try {
      DBHandler.getInstance().mergeObject(new Session(userId, 8, Session.HOURS));
    } catch (Exception e) {

    }
  }

  private Session getSession() {
    Session session1 = new Session();
    session1 = DBHandler.getInstance().getObject(userId, session1);
    if (session1 != null) return session1;
    else return null;
  }

  private void deleteSession() {
    if (getSession() != null) DBHandler.getInstance().deleteObject(session);
  }

  private void mergeUser() {
    DBHandler.getInstance().mergeObject(user);
  }

  private User getUser() {
    User user = new User();
    user = DBHandler.getInstance().getObject(userId, user);
    if (user != null) return user;
    else return null;
  }

  private void sendMessage(String message) {
    bot.execute(new SendMessage(chatId, message));
  }

  private SendMessage message(String message) {
    return new SendMessage(chatId, message);
  }
}
