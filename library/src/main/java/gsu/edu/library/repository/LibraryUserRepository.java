package gsu.edu.library.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import gsu.edu.library.entity.LibraryUser;

@Repository
public interface LibraryUserRepository extends JpaRepository<LibraryUser, Integer>{
    public LibraryUser findByEmail(String email);
}
