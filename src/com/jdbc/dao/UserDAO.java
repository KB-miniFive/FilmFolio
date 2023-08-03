package com.jdbc.dao;

import java.sql.SQLException;


import com.jdbc.vo.User;

public interface UserDAO {
	void addUser(User user)throws SQLException;
	
	void deleteUser(String userId)throws SQLException;
	
	void updateUser(User user)throws SQLException;
	

}
