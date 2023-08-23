package ex02;

interface UsersList {
  public void addUser(User user);

  public User getUserById(long id) throws UserNotFoundException;

  public User getUserByIndex(int index);

  public int getCountUsers();
}