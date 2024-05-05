package com.greatlearning.studentmanagement.controller;

import java.util.LinkedHashSet;
import java.util.Map;
import java.util.Set;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.greatlearning.studentmanagement.entity.Student;
import com.greatlearning.studentmanagement.services.CustomUserDetailsService;
import com.greatlearning.studentmanagement.services.StudentService;
import com.greatlearning.studentmanagement.util.UserUtil;

@Controller
@RequestMapping("/students")
public class StudentController {

	@Autowired
	CustomUserDetailsService customUserDetailsService;

	@Autowired
	private StudentService studentService;

	@Autowired
	Map<Integer, String> roleMap;

	private String role;

	private String userName;

	@GetMapping(value = { "", "/" })
	public String getAllStudents(Model model) {
		
		this.role = UserUtil.getHighestRole(customUserDetailsService.geEffectiveUser(), roleMap).toLowerCase();
		this.userName = StringUtils.capitalize(customUserDetailsService.geEffectiveUser().getUsername());
		
		Set<Student> students = new LinkedHashSet<>(studentService.getAllStudents());
		populateModleAttributes(model, null, students, "");

		return "students";

	}

	@PostMapping("/search")
	public String searchStudent(@ModelAttribute("search") String keyword, Model model) {
		Set<Student> studentsList = studentService.findAllStudentsBykeyword(keyword);
		populateModleAttributes(model, null, studentsList, keyword);

		return "students";
	}

	@GetMapping("/addnew")
	public String createStudent(Model model) {
		populateModleAttributes(model, new Student(), null, null);
		return "create_student";
	}

	@PostMapping("/save")
	public String saveStudent(@ModelAttribute("student") Student student) throws Exception {
		studentService.saveOrUpdateStudent(student, null);
		return "redirect:/students";
	}

	@GetMapping("/edit/{id}")
	public String editStudent(@PathVariable("id") int id, Model model) {
		Student student = studentService.getStudentById(id);
		populateModleAttributes(model, student, null, null);
		return "edit_student";
	}

	@PostMapping("/{id}")
	public String updateStudent(@PathVariable("id") String id, @ModelAttribute("student") Student updatedStudent)
			throws Exception {
		studentService.saveOrUpdateStudent(updatedStudent, Integer.parseInt(id));
		return "redirect:/students";
	}

	@GetMapping("/view/{id}")
	public String viewStudent(@PathVariable("id") int id, Model model) {
		Student student = studentService.getStudentById(id);
		populateModleAttributes(model, student, null, null);
		return "view_student";
	}

	@GetMapping("/delete/{id}")
	public String deleteStudent(@PathVariable("id") String id) {
		studentService.deleteStudentById(Integer.parseInt(id));
		return "redirect:/students";
	}

	@GetMapping("/health-check")
	public String healthCheck() {
		return "UP";

	}

	private void populateModleAttributes(Model model, Student student, Set<Student> students, String search) {

		model.addAttribute("role", role);
		model.addAttribute("username", userName);

		if (null != students && !students.isEmpty()) {
			model.addAttribute("students", students);
		}
		if (null != search) {
			model.addAttribute("search", search);
		}
		if (null != student) {
			model.addAttribute("student", student);

		}

	}
}
