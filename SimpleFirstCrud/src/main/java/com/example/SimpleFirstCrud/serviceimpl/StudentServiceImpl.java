package com.example.SimpleFirstCrud.serviceimpl;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.hibernate.graph.internal.parse.HEGLTokenTypes;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.stereotype.Service;

import com.example.SimpleFirstCrud.dto.StudentDTO;
import com.example.SimpleFirstCrud.entity.Student;
import com.example.SimpleFirstCrud.repository.Studentrepository;
import com.example.SimpleFirstCrud.service.StudentService;

@Service
public class StudentServiceImpl implements StudentService{

	@Autowired
	Studentrepository repository;
	@Autowired
	JavaMailSenderImpl senderimpl;
	
	@Autowired
	JavaMailSender sender;
	@Override
	public ResponseEntity<?> createstudent(StudentDTO dto) {
		Student s = new Student();
		Map<String, Object> map = new HashMap<String, Object>();
		//long id = dto.getId();
		String firstname = dto.getFirstname();
		String lastname = dto.getLastname();
		String email = dto.getEmail();
		String branch = dto.getBranch();
		
		if (dto.getId() > 0) {
			s.setId(dto.getId());
		}

		String regex ="^[a-zA-Z]*$";
		if (firstname .isEmpty()) {
			 map.put("code", 404);
			 map.put("message", "firstname not valid");
			 map.put("firstname", firstname);
			return ResponseEntity.status(HttpStatus.NON_AUTHORITATIVE_INFORMATION).body(map); 
		}
		//--------------------------------------------------------------------------------
		if(lastname .isEmpty()) {
			 map.put("code", 404);
			 map.put("message", "lastname not valid");
			 map.put("firstname", lastname);
			return ResponseEntity.status(HttpStatus.NON_AUTHORITATIVE_INFORMATION).body(map);

		}
		//--------------------------------------------------------------------------------
		if(branch.isEmpty())
		{
			map.put("code", 404);
			map.put("message", "not valid... ");
			map.put("branch",branch);
			return ResponseEntity.status(HttpStatus.NON_AUTHORITATIVE_INFORMATION).body(map);
			
		}
		//---------------------------------------------------------------------------------
		String emailRegex ="^[\\w-_\\.+]*[\\w-_\\.]\\@([\\w]+\\.)+[\\w]+[\\w]$";

		boolean emailValid = email.matches(emailRegex);
		
		if(!(emailValid)) {
			map.put("code", 404);
			map.put("message", "Email is not Valid.");
			map.put("status", false);
			map.put("email", email);
			return ResponseEntity.status(HttpStatus.NON_AUTHORITATIVE_INFORMATION).body(map);
		}
		
		if(email.isEmpty())
		{
			map.put("code", 404);
			map.put("message", "not valid... ");
			map.put("email",email);
			return ResponseEntity.status(HttpStatus.NON_AUTHORITATIVE_INFORMATION).body(map);
			
		}
		
		Student detail = repository.findByEmail(email);
		
		if(null != detail) {
			map.put("code", 404);
			map.put("message", "Email is Already Exist.");
			map.put("status", false);
			return ResponseEntity.status(HttpStatus.NON_AUTHORITATIVE_INFORMATION).body(map);
		}
		//------------------------------------------------------------------------------------
	
		else
		{
			ModelMapper modelMapper = new ModelMapper();
			Student student = modelMapper.map(dto, Student.class);
			
			Map<String, Object> map1 = new HashMap<String, Object>();
//			map1.put("id",s.getId());
			map1.put("firstname", dto.getFirstname());
			map1.put("lastname", dto.getLastname());
			map1.put("email",dto.getEmail());
			map1.put("branch", dto.getBranch());
			
			map.put("code", 200);
			map.put("message", "data inserted...");
			map.put("status", true);
			map.put("data", repository.save(student));
			sendmail(email, firstname, lastname, branch);
			
			return ResponseEntity.status(HttpStatus.OK).body(map);
		}
}


	@Override
	public ResponseEntity<?> getallstudent() {
		List<Student> list = repository.findAll();
		LinkedHashMap<String , Object> datamap = new LinkedHashMap<String , Object>(); 
		datamap.put("code", 200);
		datamap.put("status", true);
		datamap.put("message", "GetAll User....");
		datamap.put("data", list);
		return ResponseEntity.ok().body(list);
	}

	@Override
	public ResponseEntity<?> deletestudent(long id) {
		Student student = repository.findById(id);


		Map<String, Object> map = new HashMap<String, Object>();
		if (!repository.existsById(id)) {
			map.put("code", 404);
			map.put("message", "Userid not found");
			map.put("status", false);
			map.put("userid", id);
			return ResponseEntity.ok(map);
		} else {

			map.put("code", 200);
			map.put("message", "Delete Successfully");
			map.put("status", true);
			map.put("deleted-data", id);
			repository.delete(student);
			map.put("above data", "deleted");
			return ResponseEntity.ok(map);
		}
		}


	@Override
	public ResponseEntity<?> updatestudent(long id,StudentDTO dto) {
		Student s = new Student();
		Map<String, Object> map = new HashMap<String, Object>();
		String firstname = dto.getFirstname();
		String lastname = dto.getLastname();
		String email = dto.getEmail();
		String branch = dto.getBranch();
		dto.setId(id);
			
		String regex ="^[a-zA-Z]*$";
		if (firstname .isEmpty()) {
			 map.put("code", 404);
			 map.put("message", "firstname not valid");
			 map.put("firstname", firstname);
			return ResponseEntity.status(HttpStatus.NON_AUTHORITATIVE_INFORMATION).body(map); 
		}
		if(lastname .isEmpty()) {
			 map.put("code", 404);
			 map.put("message", "lastname not valid");
			 map.put("firstname", lastname);
			return ResponseEntity.status(HttpStatus.NON_AUTHORITATIVE_INFORMATION).body(map);

		}
		//-------------------------------------------------------------------------------------
		if(branch.isEmpty())
		{
			map.put("code", 404);
			map.put("message", "not valid... ");
			map.put("branch",branch);
			return ResponseEntity.status(HttpStatus.NON_AUTHORITATIVE_INFORMATION).body(map);
			
		}
		//-----------------------------------------------------------------------------------
		if(email.isEmpty())
		{
			map.put("code", 404);
			map.put("message", "not valid... ");
			map.put("email",email);
			return ResponseEntity.status(HttpStatus.NON_AUTHORITATIVE_INFORMATION).body(map);	
		}
		
		Student detail = repository.findByEmail(email);
		
		if(null != detail) {
			map.put("code", 404);
			map.put("message", "Email is Already Exist.");
			map.put("status", false);
			repository.findByEmail(email);
			return ResponseEntity.status(HttpStatus.NON_AUTHORITATIVE_INFORMATION).body(map);
		}
	//---------------------------------------------------------------------------------------
		else
		{
			ModelMapper modelMapper = new ModelMapper();
			Student student = modelMapper.map(dto, Student.class);
				
	
		map.put("code", 200);
		map.put("message", "data updated...");
		map.put("status", true);
		map.put("data", repository.save(student));
		return ResponseEntity.status(HttpStatus.OK).body(map);
	}
	}


	public void sendmail(String recipient,String firstname, String lastname, String branch) {
		
		System.out.println("mail sending....");
		SimpleMailMessage msg = new SimpleMailMessage();
		msg.setTo(recipient);
		msg.setSubject("hii,How are you..."+firstname+ " "+lastname);
		msg.setText("hello" +firstname);
		
		
		senderimpl.send(msg);
		System.out.println("mail sent...");
	 
	}			
}
