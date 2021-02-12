package com.javastackspaceit.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.javastackspaceit.dto.ApiResponse;
import com.javastackspaceit.dto.AuthRequest;
import com.javastackspaceit.dto.SuccessResponse;
import com.javastackspaceit.dto.UserResponse;
import com.javastackspaceit.exception.UserNotCreatedException;
import com.javastackspaceit.exception.UserRecordsNotFoundException;
import com.javastackspaceit.model.User;
import com.javastackspaceit.model.UserDetails;
import com.javastackspaceit.repository.UserDetailsRepository;
import com.javastackspaceit.repository.UserRepository;
import com.javastackspaceit.util.JwtUtil;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
public class UserController {

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private UserDetailsRepository userDetailsRepository;

	@Autowired
	private AuthenticationManager authenticationManager;

	@Autowired
	private JwtUtil jwtUtil;

	@GetMapping(value = "/hello")
	public String hello() {

		return " Hello world";
	}

	@PostMapping(value = "/authenticate")
	public ResponseEntity<SuccessResponse> generateToken(@RequestBody AuthRequest authRequest) throws Exception {
		try {
			authenticationManager.authenticate(
					new UsernamePasswordAuthenticationToken(authRequest.getUserName(), authRequest.getPassword()));

		} catch (Exception exception) {
			throw new Exception("Invalid username/password");
		}
		SuccessResponse response = new SuccessResponse();
		response.setStatus(HttpStatus.OK.value());
		response.setMessage("success");
		response.setResult(jwtUtil.generateToken(authRequest.getUserName()));
		return new ResponseEntity<SuccessResponse>(response, HttpStatus.OK);

	}

	@DeleteMapping(value = "/delete")
	public String delete() {
		return " delete method";
	}

	@GetMapping("/user/{userName}")
	public ResponseEntity<ApiResponse> getUser(@PathVariable("userName") final String userName) {
		User userData = null;
		Optional<User> user = userRepository.findByUserName(userName);
		if (user.isPresent()) {
			userData = user.get();
		}

		ApiResponse response = new ApiResponse();
		response.setMessage("success");
		response.setStatus(HttpStatus.OK.value());
		UserResponse userResponse = new UserResponse();
		userResponse.setFirstName("Rakesh");
		userResponse.setLastName("Yadav");
		userResponse.setLastName(50000 + "");
		List<UserResponse> userList = new ArrayList<UserResponse>();
		userList.add(userResponse);
		response.setResult(userList);
		return new ResponseEntity<ApiResponse>(response, HttpStatus.OK);
	}

	@GetMapping
	public ResponseEntity<ApiResponse> getUserDetails() {
		List<UserDetails> user = userDetailsRepository.findAll();
		ApiResponse response = new ApiResponse();
		if (user != null) {
			List<UserResponse> userList = new ArrayList<UserResponse>();
			UserResponse userResponse = null;
			for (UserDetails userDeatils : user) {
				response.setMessage("success");
				response.setStatus(HttpStatus.OK.value());
				userResponse = new UserResponse();
				userResponse.setFirstName(userDeatils.getFirstName());
				userResponse.setLastName(userDeatils.getLastName());
				userResponse.setId(userDeatils.getId());
				userResponse.setEmailAddress(userDeatils.getEmailAddress());
				userResponse.setMobileNo(userDeatils.getMobileNo());
				userList.add(userResponse);
				response.setResult(userList);
			}
		} else {
			throw new UserRecordsNotFoundException("User Not found.");
		}
		return new ResponseEntity<ApiResponse>(response, HttpStatus.OK);
	}

	@GetMapping("/details/{id}")
	public ResponseEntity<ApiResponse> getUserDetailsById(@PathVariable("id") String id) {
		Optional<UserDetails> user = userDetailsRepository.findById(Integer.parseInt(id));
		System.err.println("user  :" + user);
		ApiResponse apiResponse = new ApiResponse();
		if (user.isPresent()) {
			UserDetails userDetails = user.get();
			apiResponse.setMessage("success");
			apiResponse.setStatus(HttpStatus.OK.value());
			UserResponse userResponse = new UserResponse();
			BeanUtils.copyProperties(userDetails, userResponse);
			List<UserResponse> list = new ArrayList<UserResponse>();
			list.add(userResponse);
			apiResponse.setResult(list);
		} else {
			throw new UserRecordsNotFoundException("User Not found");
		}
		return new ResponseEntity<ApiResponse>(apiResponse, HttpStatus.OK);
	}

	@PutMapping("/update")
	public ResponseEntity<SuccessResponse> updateUserdetails(@RequestBody UserDetails userDetails) {
		UserDetails user = userDetailsRepository.save(userDetails);
		SuccessResponse response = new SuccessResponse();
		if (user != null) {
			response.setMessage("success");
			response.setStatus(HttpStatus.OK.value());
		}
		return new ResponseEntity<SuccessResponse>(response, HttpStatus.OK);
	}

	@DeleteMapping("/delete/{id}")
	public ResponseEntity<SuccessResponse> deleteUserDetails(@PathVariable("id") Integer id) {
		boolean status = false;
		SuccessResponse successResponse=new SuccessResponse();
		userDetailsRepository.deleteById(id);
		status = true;
		if (status) {
		successResponse.setMessage("deleted");
		successResponse.setStatus(HttpStatus.OK.value());
		}
		return new ResponseEntity<SuccessResponse>(successResponse, HttpStatus.OK);
	}
	@PostMapping("/create")
	public ResponseEntity<SuccessResponse> create(@RequestBody UserDetails userDetails){
		SuccessResponse response=new SuccessResponse();
		UserDetails user = userDetailsRepository.save(userDetails);
		 if(user!= null) {
			 response.setMessage("created");
			 response.setStatus(HttpStatus.OK.value());
		 }else {
			 throw new UserNotCreatedException("UserNot created");
			 
		 }
		 
		return null;
	}
	@PostMapping(value = "/signup")
	public User signUpUser(User user) {
		return userRepository.save(user);
	}
}
