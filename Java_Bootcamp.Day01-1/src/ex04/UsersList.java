package ex04;

import ex02.UserNotFoundException;

public interface UsersList {
  public int getSize();

  public User[] getUsers();

  public void addUser(User user);

  public User getUserById(long id) throws UserNotFoundException;

  public User getUserByIndex(int index);

  public int getCountUsers();
}
