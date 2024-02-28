package com.security.studentManagement.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.security.studentManagement.entity.Student;

public interface StudentRepository extends JpaRepository<Student, Integer> 
{

}
