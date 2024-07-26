package gsu.edu.library.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import gsu.edu.library.entity.Rent;

@Repository
public interface RentRepository extends JpaRepository<Rent,Integer> {
	public List<Rent> findAllByUserId(int usetid);
}
