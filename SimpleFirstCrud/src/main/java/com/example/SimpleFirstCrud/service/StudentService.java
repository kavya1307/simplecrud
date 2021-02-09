package com.example.SimpleFirstCrud.service;

import org.springframework.http.ResponseEntity;

import com.example.SimpleFirstCrud.dto.StudentDTO;

public interface StudentService {
	
	public ResponseEntity<?> createstudent(StudentDTO dto);
	public ResponseEntity<?> getallstudent();
	public ResponseEntity<?> deletestudent(long id);
	public ResponseEntity<?> updatestudent(long id,StudentDTO dto);
	public void sendmail(String recipient,String firstname,String lastname,String branch);

}
