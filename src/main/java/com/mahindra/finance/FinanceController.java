package com.mahindra.finance;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class FinanceController {

	@Autowired
	private AdvisorRepository advisorRepository;

	@Autowired
	private UserRepository userRepository;

	/*
	 * API-1 : To add an Advisor
	 */
	@PostMapping("/admin/advisor")
	public ResponseEntity<?> addAdvisor(@RequestBody Advisor advisor) {

		if (advisor.getAdvisorName() == null || advisor.getPhotoURL() == null || advisor.getAdvisorName().isEmpty()
				|| advisor.getPhotoURL().isEmpty()) {
			return ResponseEntity.badRequest().build();
		} else {
			advisorRepository.save(advisor);
			return ResponseEntity.ok().build();
		}
	}

	/*
	 * API-2 : To Register an User
	 */
	@PostMapping("/user/register")
	public ResponseEntity<?> createUser(@RequestBody User user) {

		if ((user.getName() == null || user.getName().isEmpty())
				&& (user.getEmail() == null || user.getEmail().isEmpty())
				&& (user.getPassword() == null || user.getPassword().isEmpty())) {
			return ResponseEntity.badRequest().build();
		} else {
			// Hash the password before saving it to the database
			user.setPassword(user.getPassword());
			userRepository.save(user);
			return ResponseEntity.ok().build();
		}
	}

	/*
	 * API-3 : Can Login as a USER
	 */
	@PostMapping("/user/login")
	public String loginUser(@RequestBody LoginUser loginUser) {
		User user = userRepository.findByEmail(loginUser.getEmail());

		if (user != null && user.checkPassword(loginUser.getPassword())) {
			return "Login Successfull";
		} else {
			return "Invalid credentials";
		}
	}

	/*
	 * API-4 : Get the list of all Advisors
	 * 
	 */
	@GetMapping("/advisors")
	public List<Advisor> getAdvisors() {
		return advisorRepository.findAll();
	}
	
	/*
	 * API-Can book a call with an advisor
	 * 
	 */
	@PostMapping("/user/{userId}/advisor/{advisorId}")
	public ResponseEntity<?> bookAdvisor(@RequestParam Long userId, @RequestParam Long advisorId, @RequestBody String bookingTime) {
		
		try {
			LocalDateTime dateTime = LocalDateTime.parse(bookingTime);
			
			return ResponseEntity.ok().build();
		}catch(Exception e) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
		}
	}
	
	/**
	 * API - To get all the booked calls
	 */
	@GetMapping("/user/userId/advisor/booking/")
	public List<Advisor> getAllTheBookedCalls(@RequestParam Long userId){
		return null;
	}
	
	

	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
