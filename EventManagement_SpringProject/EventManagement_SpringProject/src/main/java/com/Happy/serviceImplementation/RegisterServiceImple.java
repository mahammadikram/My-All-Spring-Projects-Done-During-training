package com.Happy.serviceImplementation;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.Happy.entity.Form;
import com.Happy.entity.RegisterEntity;
import com.Happy.repository.FormRepo;
import com.Happy.repository.RegisterRepo;
import com.Happy.serviceInterface.RegisterService;

import jakarta.servlet.http.HttpSession;

@Service
public class RegisterServiceImple implements RegisterService {
    @Autowired
    private RegisterRepo repo;

    @Override
    public Integer saveUser(RegisterEntity entity) {

        return repo.save(entity).getUserId();
    }

    @Override
    public String loginUser(String email, String password, HttpSession session) {
        System.out.println(email + "this is in imple");
        String b;
        if ((repo.findByUserEmail(email).getUserEmail().equals(email))
                && (repo.findByUserEmail(email).getUserPassword().equals(password))) {
            System.out.println("login Succesfull...........");
            session.setAttribute("umail", repo.findByUserEmail(email).getUserEmail());
            session.setAttribute("uname", repo.findByUserEmail(email).getUserName());
            session.setAttribute("uphone", repo.findByUserEmail(email).getUserPhone());
            b = "success";
        } else {
            System.out.println("Login unsuccess..");
            b = "fail";
        }
        return b;

    }

    @Override
    public boolean isCodeAvailable(String useremail) {

        return !repo.existsByUserEmail(useremail);
    }

    @Override
    public boolean checkUser(String email) {
        boolean result = repo.existsByUserEmail(email);
        return result;
    }

    @Override
    public String forgotPassword(String userEmail, String userPassword) {
        String status = "";
        boolean result = repo.existsByUserEmail(userEmail);
        if (result) {
            int i = repo.updateUserPassword(userEmail, userPassword);
            if (i > 0) {
                status = "success";
                System.out.println(status);
            } else {
                status = "updatation failure";
                System.out.println(status);
            }
        } else {
            System.out.println("No such mail found");
            status = "failure";
            System.out.println(status);
        }
        return status;
    }

    @Override
    public List<RegisterEntity> data() {

        List<RegisterEntity> list = repo.findAll();
        return list;
    }

    @Override
    public void deleteUser(Integer id) {
        repo.deleteById(id);
    }
    
    @Autowired
    private FormRepo fRepo;
    @Override
    public List<Form> getAllBookings() {
       List<Form> list =fRepo.findAll();
        return list;
    }

	@Override
	public void deleteUserBookingByAdmin(Integer id) {
		fRepo.deleteById(id);
		
	}

}
