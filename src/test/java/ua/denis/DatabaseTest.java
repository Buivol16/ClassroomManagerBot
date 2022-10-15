import org.junit.jupiter.api.*;
import ua.denis.dev.db.model.Session;
import ua.denis.dev.db.model.User;

import static org.junit.jupiter.api.Assertions.*;
import static ua.denis.dev.db.DBHandler.getInstance;

public class DatabaseTest {
  private static final long TEST_ID = 1566;

  @Test
  void shouldFindTestUser() {
    User user = new User(TEST_ID, null, null);
    getInstance().createUser(user);
    assertTrue(getInstance().hasUser(TEST_ID));
    getInstance().deleteUser(user);
  }

  @Test
  void shouldNotFindUser() {
    assertFalse(getInstance().hasUser(1512));
  }

  @Test
  void shouldCreateSession() {
    Session testSession = new Session(TEST_ID, 0, Session.SECOND);
    getInstance().createSession(testSession);
    assertTrue(getInstance().hasSession(TEST_ID));
    getInstance().deleteSession(TEST_ID);
  }

  @Test
  void shouldCompareSession() {
    Session testSession = new Session(TEST_ID, 0, Session.SECOND);
    getInstance().createSession(testSession);
    assertTrue(getInstance().isSessionExpired(TEST_ID));
    getInstance().deleteSession(TEST_ID);
  }

  @Test
  void shouldMergeUser(){
    getInstance().createUser(new User(TEST_ID, null, null));
    getInstance().mergeUser(new User(TEST_ID, "Student", "Denis"));
    assertTrue("Student".equals(getInstance().getUser(TEST_ID).getAccountType()));
    getInstance().deleteUser(getInstance().getUser(TEST_ID));
  }
}
