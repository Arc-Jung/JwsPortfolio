package project.store;

import project.domain.User;

public interface UserStore {
	
	public void insertUser(User user);
	public User loginUser(String userID, String userPW);
	public void updateUser(User user);
	public boolean findUserPW(String userID, String userName, String userMail);
	public void deleteUser(String userID, String userPW);
	public void changePW(String userID, String pwd);
}
