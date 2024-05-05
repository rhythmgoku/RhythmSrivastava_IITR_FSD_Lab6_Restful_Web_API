package com.greatlearning.studentmanagement.services;

import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.greatlearning.studentmanagement.entity.Student;
import com.greatlearning.studentmanagement.repository.StudentRepository;

@Service
public class StudentServiceImpl implements StudentService {

	@Autowired
	StudentRepository studentRepository;

	@Override
	public List<Student> getAllStudents() {
		return studentRepository.findAllByOrderByIdAsc();
	}

	@Override
	public Student saveOrUpdateStudent(Student student, Integer id) throws Exception {

		if (null != id && null == getStudentById(id)) {
			throw new Exception(
					"Student with provided Student Id does not exists in the database, Please Add the Student before editing ");
		}

		return studentRepository.save(student);
	}

	@Override
	public void deleteStudentById(int id) {
		studentRepository.deleteById(id);

	}

	@Override
	public Student getStudentById(int id) {
		return studentRepository.findById(id).get();
	}

	@Override
	public Set<Student> findAllStudentsBykeyword(String keyword) {

		List<Student> students = new ArrayList<>();

		if (StringUtils.isBlank(keyword)) {
			students = getAllStudents();
		} else {
			students.addAll(studentRepository.findByFirstNameContainingOrderByIdAsc(keyword));
			students.addAll(studentRepository.findByLastNameContainingOrderByIdAsc(keyword));
			students.addAll(studentRepository.findByCourseContainingOrderByIdAsc(keyword));
			students.addAll(studentRepository.findByCountryContainingOrderByIdAsc(keyword));
		}
		return new LinkedHashSet<>(students);
	}

}
