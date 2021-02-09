package com.example.SimpleFirstCrud.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.SimpleFirstCrud.entity.Student;

public interface Studentrepository extends JpaRepository<Student, Long> {

	Student findById(long id);

	Student findByEmail(String email);
	
	
	}
