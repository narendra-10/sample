package com.security.studentManagement.controller;

import java.security.Principal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.security.studentManagement.entity.Student;
import com.security.studentManagement.service.StudentService;

@Controller
@RequestMapping("/student")
public class StudentController 
{
	@Autowired
	private StudentService studentService;
	
	@RequestMapping("/listStudents")
	public String listStudents(Model model)
	{
		List<Student> students = studentService.findAll();
		
		model.addAttribute("students" , students);
		
		return "list-Students";
	}
	
	@RequestMapping("/showFormForAdd")
	public String showFormForAdd(Model model)
	{
		Student student = new Student();
		
		model.addAttribute("student",student);
		
		return "Student-form";
	}
	
	@RequestMapping("/showFormForUpdate")
	public String  showFormForUpdate(@RequestParam("studentId") int id,Model model) 
	{
		Student student = studentService.findById(id);
		
		model.addAttribute("student",student);
		
		return "Student-form";
	}
	
	@PostMapping("/saveStudent")
	public String saveStudent(@RequestParam("id") int id,@RequestParam("firstName") String firstName
			,@RequestParam("lastName") String lastName,@RequestParam("course") String course,@RequestParam("country") String country)
	{
		System.out.println(id);
		
		Student student;
		
		if(id!=0)
		{
			student = studentService.findById(id);
			
			student.setFirstName(firstName);
			student.setLastName(lastName);
			student.setCourse(course);
			student.setCountry(country);
		}
		else
		{
			student = new Student(firstName, lastName, course, country);
		}
		
		studentService.save(student);
		
		return "redirect:/student/list";
	}
	
	@RequestMapping("/delete")
	public String delete(@RequestParam("studentId") int id) 
	{
		studentService.deleteById(id);

		return "redirect:/student/list";
	}
	
	@RequestMapping(value = "/403")
	public ModelAndView accessDenied(Principal user)
	{
		ModelAndView model = new ModelAndView();

		if (user != null) 
		{
			model.addObject("msg", "Hi " + user.getName() + ", you do not have permission to access this page!");
		} 
		else 
		{
			model.addObject("msg", "You do not have permission to access this page!");
		}

		model.setViewName("403");
		return model;
	}
	
}
