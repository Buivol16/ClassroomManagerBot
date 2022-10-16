package ua.denis;

import org.junit.jupiter.api.*;
import ua.denis.dev.db.DBHandler;
import ua.denis.dev.db.model.impl.Session;
import ua.denis.dev.db.model.impl.User;

import static org.junit.jupiter.api.Assertions.*;
import static ua.denis.dev.db.DBHandler.getInstance;

public class DatabaseTest {
  private static final long TEST_ID = 1566;

  @Test
  void shouldFindTestUser() {
    User user = new User(TEST_ID, null, null);
    getInstance().mergeObject(user);
    assertTrue(getInstance().getObject(TEST_ID, user) != null);
    getInstance().deleteObject(user);
  }

  @Test
  void shouldNotFindUser() {
    User user = new User();
    assertTrue(getInstance().getObject(1512, user) == null);
  }

  @Test
  void shouldCreateSession() {
    Session testSession = new Session(TEST_ID, 0, Session.SECOND);
    getInstance().mergeObject(testSession);
    assertTrue(getInstance().getObject(TEST_ID, testSession) != null);
    getInstance().deleteObject(testSession);
  }

  @Test
  void shouldBackSessionIsExpired() {
    Session testSession = new Session(TEST_ID, 0, Session.SECOND);
    getInstance().mergeObject(testSession);
    assertTrue(DBHandler.getInstance().getObject(TEST_ID, testSession).isSessionExpired(TEST_ID));
    getInstance().deleteObject(testSession);
  }
}
