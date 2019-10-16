package project.service.logic;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import project.domain.User;
import project.service.UserService;
import project.store.UserStore;

@Service
public class UserServiceLogic implements UserService{
	
	@Autowired
	private UserStore store;
	
	

	@Override
	public void insertUser(User user) throws Exception {
		// TODO Auto-generated method stub

		store.insertUser(user);
		
		
	}

	@Override
	public void updateUser(User user) throws Exception {
		// TODO Auto-generated method stub
		
		store.updateUser(user);
		
	}

	@Override
	public User loginUser(String userID, String userPW) throws Exception {
		// TODO Auto-generated method stub
		return store.loginUser(userID, userPW);
	}

	@Override
	public boolean findUserPW(String userID, String userName, String userMail) throws Exception {
		// TODO Auto-generated method stub
		return store.findUserPW(userID, userName, userMail);
	}

	@Override
	public void deleteUser(String userID, String userPW) throws Exception {
		// TODO Auto-generated method stub
		
		store.deleteUser(userID, userPW);
		
	}

	@Override
	public void changePW(String userID, String pwd) throws Exception {
		// TODO Auto-generated method stub
		store.changePW(userID, pwd);
	}
	
	


}
