package com.kodnest.tunehub.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.kodnest.tunehub.entity.Song;
import com.kodnest.tunehub.entity.User;
import com.kodnest.tunehub.repository.UserRepository;

@Service
public interface UserService {

	 void saveUser(User user);

	boolean emailExsists(User user);

	boolean validUser(String email, String password);

	String getRole(String email);

	boolean emailExists(User user);

	User getUser(String mail);

	void updateUser(User user);

	

}
