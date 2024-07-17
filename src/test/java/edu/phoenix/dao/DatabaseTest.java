package edu.phoenix.dao;

import edu.phoenix.model.User;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

class DatabaseTest {
  private static final String NAME = "Name";
  private static final String LOGIN = "login";
  private static final String PASSWORD = "password";

  @Test
  void shouldAddUser() {
    assertTrue(Database.dataBase.isEmpty());

    Database.addUser(NAME, LOGIN, PASSWORD);

    assertEquals(1, Database.dataBase.size());

    User user = Database.dataBase.get(0);

    assertEquals(NAME, user.getName());
    assertEquals(LOGIN, user.getLogin());
    assertEquals(PASSWORD, user.getPassword());

    Database.dataBase.remove(0);

    assertTrue(Database.dataBase.isEmpty());
  }

  @Test
  void shouldReturnUser() {
    assertTrue(Database.dataBase.isEmpty());

    Database.dataBase.add(new User(NAME, LOGIN, PASSWORD));

    User user;

    try {
      user = Database.getUser(LOGIN, PASSWORD);
    }
    catch (NotFoundException e) {
      System.out.println(e.getMessage());
      return;
    }

    assertNotNull(user);

    assertEquals(NAME, user.getName());
    assertEquals(LOGIN, user.getLogin());
    assertEquals(PASSWORD, user.getPassword());

    Database.dataBase.remove(0);

    assertTrue(Database.dataBase.isEmpty());
  }

  @Test
  void shouldDeleteUser() {
    assertTrue(Database.dataBase.isEmpty());

    Database.dataBase.add(new User(NAME, LOGIN, PASSWORD));

    try {
      Database.deleteUser(LOGIN);
    }
    catch (NotFoundException e) {
      System.out.println(e.getMessage());
      return;
    }

    assertTrue(Database.dataBase.isEmpty());
  }

  @Test
  void shouldUpdateUser() {
    assertTrue(Database.dataBase.isEmpty());

    Database.dataBase.add(new User(NAME, LOGIN, PASSWORD));

    String updatedName = "new name";
    String updatedPassword = "new password";

    User updated = new User(updatedName, LOGIN, updatedPassword);

    try {
      Database.updateUser(updated);
    }
    catch (NotFoundException e) {
      System.out.println(e.getMessage());
      return;
    }

    User fromDB = Database.dataBase.get(0);

    assertEquals(updatedName, fromDB.getName());
    assertEquals(updatedPassword, fromDB.getPassword());

    Database.dataBase.remove(0);

    assertTrue(Database.dataBase.isEmpty());
  }
}