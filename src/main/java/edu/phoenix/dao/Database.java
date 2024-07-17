package edu.phoenix.dao;

import edu.phoenix.model.User;

import java.util.ArrayList;
import java.util.List;
import java.util.ListIterator;
import java.util.Objects;

public class Database {
  public static List<User> dataBase = new ArrayList<>();

  public static void addUser(String name, String login, String password) {
    User new_user = new User(name, login, password);
    dataBase.add(new_user);
  }

  public static User getUser(String login, String password) {
    for (User user : dataBase) {
      if (Objects.equals(user.getLogin(), login) &&
          Objects.equals(user.getPassword(), password))
        return user;
    }
    return null;
  }

  public static void deleteUser(String login) {
    ListIterator<User> iter = dataBase.listIterator();
    while (iter.hasNext()) {
      User user = iter.next();
      if (Objects.equals(user.getLogin(), login)) {
        iter.remove();
        return;
      }
    }
  }

  public static void updateUser(User updated_user) {
    ListIterator<User> iter = dataBase.listIterator();
    while (iter.hasNext()) {
      User old_user = iter.next();
      if (Objects.equals(old_user.getLogin(), updated_user.getLogin())) {
        iter.remove();
        iter.add(updated_user);
        return;
      }
    }
  }

}
