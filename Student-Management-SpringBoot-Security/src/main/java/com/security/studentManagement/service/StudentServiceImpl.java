package com.security.studentManagement.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.security.studentManagement.entity.Student;
import com.security.studentManagement.repository.StudentRepository;

import javax.transaction.Transactional;

import java.util.List;

@Repository
public class StudentServiceImpl implements StudentService 
{

	@Autowired
	StudentRepository studentRepository;

	@Transactional
	public List<Student> findAll() 
	{

		List<Student> students = studentRepository.findAll();

		return students;
	}

	@Transactional
	public Student findById(int id) 
	{

		Student student = new Student();

		// find record with Id from the database table
		student = studentRepository.findById(id).get();

		return student;
	}

	@Transactional
	public void save(Student theStudent) 
	{

		studentRepository.save(theStudent);

	}

	@Transactional
	public void deleteById(int id) 
	{

		studentRepository.deleteById(id);

	}

}
