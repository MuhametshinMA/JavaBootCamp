package ex04;

import java.util.Arrays;
import ex02.UserNotFoundException;

public class UsersArrayList implements UsersList {

  private User[] users;

  private int size;

  private void addCapacity() {
    users = Arrays.copyOf(users, 2 * users.length);
  }

  public UsersArrayList() {
    users = new User[10];
    size = 0;
  }

  public UsersArrayList(int count) {
    users = new User[count];
    size = 0;
  }

  public int getSize() {
    return size;
  }

  public User[] getUsers() {
    return users;
  }

  public void addUser(User user) {
    if (size == users.length) {
      addCapacity();
    }

    users[size] = user;
    ++size;
  }

  public User getUserById(long id) throws UserNotFoundException {
    for (User user : users) {
      if (user.getId() == id) {
        return user;
      }
    }
    throw new UserNotFoundException("User not found with id: " + id);
  }

  public User getUserByIndex(int index) {
    if (index < 0 || index >= size) {
      throw new IndexOutOfBoundsException("Index is out of range");
    }

    return users[index];
  }

  public int getCountUsers() {
    return size;
  }

  public int getCapacity() {
    return users.length;
  }

}
