package gsu.edu.library.service.Impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import gsu.edu.library.entity.LibraryUser;
import gsu.edu.library.repository.LibraryUserRepository;
import gsu.edu.library.service.LibraryUserService;

@Service
public class LibraryUserServiceImpl implements LibraryUserService{
	
	private LibraryUserRepository userRepository;
	
    @Autowired
	public LibraryUserServiceImpl(LibraryUserRepository userRepository) {
		this.userRepository = userRepository;
	}

	@Override
	public LibraryUser signup(LibraryUser lUser) {
		return userRepository.save(lUser);
	}

	@Override
	public LibraryUser signin(String email, String password) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public LibraryUser signout(LibraryUser lUser) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public LibraryUser findByEmail(String email) {
		return userRepository.findByEmail(email);
	}

}
