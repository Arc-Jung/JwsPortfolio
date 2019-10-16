package project.service;

import project.domain.User;

public interface UserService {
	
	public void insertUser(User user) throws Exception;
	public User loginUser(String userID, String userPW) throws Exception;
	public void updateUser(User user) throws Exception;
	public boolean findUserPW(String userID, String userName, String userMail) throws Exception;
	public void deleteUser(String userID, String userPW) throws Exception;
	public void changePW(String userID, String pwd) throws Exception;
}
