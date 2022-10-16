package ua.denis.dev.bot.utils;

import com.pengrad.telegrambot.model.request.InlineKeyboardButton;
import com.pengrad.telegrambot.model.request.InlineKeyboardMarkup;
import com.pengrad.telegrambot.model.request.KeyboardButton;
import com.pengrad.telegrambot.model.request.ReplyKeyboardMarkup;

import java.time.Instant;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Keyboard {
  public static final InlineKeyboardMarkup typeChooseKeyboard =
      new InlineKeyboardMarkup(
          new InlineKeyboardButton[] {
            new InlineKeyboardButton(
                    "\uD83D\uDC69\u200D\uD83C\uDFEB Вчитель \uD83D\uDC68\u200D\uD83C\uDFEB")
                .callbackData("Teacher"),
            new InlineKeyboardButton(
                    "\uD83D\uDC69\u200D\uD83C\uDF93 Учень \uD83E\uDDD1\u200D\uD83C\uDF93")
                .callbackData("Student")
          });
  public static final ReplyKeyboardMarkup actionKeyboardStudent =
      new ReplyKeyboardMarkup(
              new KeyboardButton("\uD83D\uDCDAДомашнє завдання\uD83D\uDCDA"),
              new KeyboardButton("\uD83D\uDD51Розклад дзвінків\uD83D\uDD51"))
          .addRow(new KeyboardButton("\uD83D\uDD04Змінити тип аккаунту\uD83D\uDD04"))
          .resizeKeyboard(true)
          .oneTimeKeyboard(true);
  public static final ReplyKeyboardMarkup actionKeyboardTeacher =
      new ReplyKeyboardMarkup(
              new KeyboardButton("➕Додати новий клас"),
              new KeyboardButton("✏Змінити вже існуючий клас"))
          .addRow(new KeyboardButton("\uD83D\uDD04Змінити тип аккаунту\uD83D\uDD04"))
          .resizeKeyboard(true)
          .oneTimeKeyboard(true);
  public static final InlineKeyboardMarkup dateKeyboard =
      new InlineKeyboardMarkup();

}
