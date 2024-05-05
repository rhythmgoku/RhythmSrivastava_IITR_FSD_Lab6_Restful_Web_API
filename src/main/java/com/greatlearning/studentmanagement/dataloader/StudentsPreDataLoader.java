package com.greatlearning.studentmanagement.dataloader;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import com.greatlearning.studentmanagement.entity.Student;
import com.greatlearning.studentmanagement.repository.StudentRepository;

@Component
public class StudentsPreDataLoader implements CommandLineRunner {

	@Autowired
	StudentRepository studentRepository;

	@Override
	public void run(String... args) throws Exception {

		Student student1 = Student.builder().firstName("Suresh").lastName("Reddy").course("B.Tech").country("India").gender("Male").build();
		Student student2 = Student.builder().firstName("Murali").lastName("Mohan").course("B.Arch").country("Canada").gender("Male").build();
		Student student3 = Student.builder().firstName("Daniel").lastName("Denson").course("B.Tech").country("New Zealand").gender("Female").build();
		Student student4 = Student.builder().firstName("Tanya").lastName("Gupta").course("B.Com").country("USA").gender("Female").build();

		List<Student> students = List.of(student1, student2, student3, student4);

		students.parallelStream().forEach(
				student -> student.setEmail(student.getLastName() + "." + student.getFirstName() + "@gmail.com"));

		studentRepository.saveAll(students);

	}

}
