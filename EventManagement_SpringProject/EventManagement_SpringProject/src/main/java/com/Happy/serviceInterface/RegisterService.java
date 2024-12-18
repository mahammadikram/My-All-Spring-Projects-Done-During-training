package com.Happy.serviceInterface;

import java.util.List;

import com.Happy.entity.Form;
import com.Happy.entity.RegisterEntity;

import jakarta.servlet.http.HttpSession;

public interface RegisterService {

	Integer saveUser(RegisterEntity entity);

	String loginUser(String email, String password, HttpSession session);

	boolean isCodeAvailable(String useremail);

	boolean checkUser(String email);

	String forgotPassword(String userEmail, String userPassword);

	List<RegisterEntity> data();

	void deleteUser(Integer id);

    List<Form> getAllBookings();

	void deleteUserBookingByAdmin(Integer id);

	

}
