package ua.denis.dev.db.model.impl;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.Setter;
import ua.denis.dev.bot.utils.BotConsts;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Entity(name = "session")
public class Session {
  @Id
  @Getter
  @Setter
  @Column(name = "user_id")
  private long id;

  @Getter
  @Setter
  @Column(name = "expired_at")
  private String expiredAt;

  public static final String HOURS = "hours";
  public static final String SECOND = "second";

  public Session() {}

  public Session(long user_id, int howMuch, String timeUnit) {
    this.id = user_id;
    expiredAt = setExpiredAt(howMuch, timeUnit);
  }

  private String setExpiredAt(int howMuch, String timeUnit) {
    if (timeUnit.equals(SECOND)) expiredAt = LocalDateTime.now().plusSeconds(howMuch).format(DateTimeFormatter.ofPattern(BotConsts.TIME_PATTERN));
    else expiredAt = LocalDateTime.now().plusHours(8).format(DateTimeFormatter.ofPattern(BotConsts.TIME_PATTERN));
    return expiredAt;
  }
  public boolean isSessionExpired(long id) {
    boolean result = false;
    String now = LocalDateTime.now().format(DateTimeFormatter.ofPattern(BotConsts.TIME_PATTERN));
    if (now.compareTo(getExpiredAt()) >= 0) result = true;
    return result;
  }

}
