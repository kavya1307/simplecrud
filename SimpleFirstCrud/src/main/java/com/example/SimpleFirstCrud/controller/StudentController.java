package com.example.SimpleFirstCrud.controller;

import javax.websocket.server.PathParam;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.SimpleFirstCrud.dto.StudentDTO;
import com.example.SimpleFirstCrud.service.StudentService;
import com.example.SimpleFirstCrud.serviceimpl.StudentServiceImpl;

@Controller
@RequestMapping("/student")
public class StudentController {
	
	@Autowired
	StudentService Service;
//	@Autowired
//	StudentServiceImpl impl;
//	
	@PostMapping("/addstudent")
	public ResponseEntity<?> createstudent(@RequestBody StudentDTO dto){
		return Service.createstudent(dto);
	}
	
	@GetMapping("/getallstudents")
	public ResponseEntity<?> getall(){
		return Service.getallstudent();
	}

	@DeleteMapping("/deletestudent/{id}")
	public ResponseEntity<?> deletestudent(@RequestParam long id)
	{
		return  Service.deletestudent(id);
	}
	
	@PutMapping("/u/{id}")
	public ResponseEntity<?> updatestudent(@PathVariable long id,@RequestBody  StudentDTO dto)
	
	{
		return Service.updatestudent(id, dto);
	}
}
