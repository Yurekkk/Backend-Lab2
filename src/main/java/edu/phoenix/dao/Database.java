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

  public static User getUser(String login, String password) throws NotFoundException {
    for (User user : dataBase) {
      if (Objects.equals(user.getLogin(), login) &&
          Objects.equals(user.getPassword(), password))
        return user;
    }
    throw new NotFoundException("Пользователя с указанными данными нет в базе.");
  }

  public static void deleteUser(String login) throws NotFoundException {
    ListIterator<User> iter = dataBase.listIterator();
    while (iter.hasNext()) {
      User user = iter.next();
      if (Objects.equals(user.getLogin(), login)) {
        iter.remove();
        return;
      }
    }
    throw new NotFoundException("Пользователя с указанными данными нет в базе.");
  }

  public static void updateUser(User updated_user) throws NotFoundException {
    ListIterator<User> iter = dataBase.listIterator();
    while (iter.hasNext()) {
      User old_user = iter.next();
      if (Objects.equals(old_user.getLogin(), updated_user.getLogin())) {
        iter.remove();
        iter.add(updated_user);
        return;
      }
    }
    throw new NotFoundException("Пользователя с указанными данными нет в базе.");
  }

}
