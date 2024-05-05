package com.greatlearning.studentmanagement.services;

import java.util.List;
import java.util.Set;

import com.greatlearning.studentmanagement.entity.Student;

public interface StudentService {

	List<Student> getAllStudents();

	Student saveOrUpdateStudent(Student student, Integer id) throws Exception;

	void deleteStudentById(int id);

	Student getStudentById(int id);

	Set<Student> findAllStudentsBykeyword(String keyword);

}
