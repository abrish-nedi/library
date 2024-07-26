package gsu.edu.library.service;



import gsu.edu.library.entity.LibraryUser;

public interface LibraryUserService {

	public LibraryUser signup(LibraryUser lUser);
	public LibraryUser signin(String email, String password);
	public LibraryUser signout(LibraryUser lUser);
	public LibraryUser findByEmail(String email);
	public LibraryUser findById(int userId);
}
