package com.greatlearning.studentmanagement.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.greatlearning.studentmanagement.entity.Student;

@Repository
public interface StudentRepository extends JpaRepository<Student, Integer> {
	
	List<Student> findByFirstNameContainingOrderByIdAsc(String keyword);

	List<Student> findByLastNameContainingOrderByIdAsc(String keyword);
	
	List<Student> findByCourseContainingOrderByIdAsc(String keyword);

	List<Student> findByCountryContainingOrderByIdAsc(String keyword);

	List<Student> findAllByOrderByIdAsc();
}
