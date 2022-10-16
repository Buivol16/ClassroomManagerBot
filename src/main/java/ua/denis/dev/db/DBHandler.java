package ua.denis.dev.db;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.jetbrains.annotations.NotNull;
import ua.denis.dev.db.model.impl.*;

public class DBHandler {
  private static DBHandler INSTANCE = null;
  private Configuration configuration =
      new Configuration()
          .addAnnotatedClass(User.class)
          .addAnnotatedClass(TimeTable.class)
          .addAnnotatedClass(Teacher.class)
          .addAnnotatedClass(ua.denis.dev.db.model.impl.Session.class)
          .addAnnotatedClass(Classroom.class)
          .addAnnotatedClass(Student.class);
  private SessionFactory sessionFactory = configuration.buildSessionFactory();
  private Session session;

  private DBHandler() {
    if (session == null) openSession();
    else if (!session.isOpen()) openSession();
  }

  public static DBHandler getInstance() {
    if (INSTANCE == null) INSTANCE = new DBHandler();
    return INSTANCE;
  }

  public <T> void mergeObject(T t) {
    openSession();
    session.beginTransaction();
    session.merge(t);
    session.getTransaction().commit();
    session.close();
  }

  public <T> void deleteObject(T t) {
    openSession();
    session.beginTransaction();
    session.remove(t);
    session.getTransaction().commit();
    session.close();
  }

  public <T> T getObject(long id, T t) {
    T object = t;
    openSession();
    session.beginTransaction();
    object = (T) session.get(t.getClass(), id);
    session.getTransaction().commit();
    session.close();
    return object;
  }

  private void openSession() {
    session = sessionFactory.openSession();
  }
}
