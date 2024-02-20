package com.institute.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.security.core.Authentication;

import com.institute.Model.AuthRequest;
import com.institute.Model.UserInfo;
import com.institute.Service.JwtService;
import com.institute.Service.UserInfoService;


@RestController
@RequestMapping("/auth")
public class InstituteSecurityController {

	@Autowired
	private UserInfoService service;

	@Autowired
	private JwtService jwtService;

	@Autowired
	private AuthenticationManager authenticationManager;

	

	@PostMapping("/addNewUser")
	public ResponseEntity<String> addNewUser(@RequestBody UserInfo userInfo) {
		
		 String result = service.addUser(userInfo);
		 if(result!="SUCCESS")
		 {
			return ResponseEntity.badRequest().body("User already exists"); 
		 }
			 
		return ResponseEntity.ok("User Added");
	}

	@PostMapping("/user/afterLogin")
	public String afterLoginCheck()
	{
		int a =50;
		return "you'have passed security check ";
		
	}

	@PostMapping("/generateToken")
	public String authenticateAndGetToken(@RequestBody AuthRequest authRequest) {
		try {
			Authentication authentication = authenticationManager.authenticate(
					new UsernamePasswordAuthenticationToken(authRequest.getUsername(), authRequest.getPassword()));
			if (authentication.isAuthenticated()) {
				return jwtService.generateToken(authRequest.getUsername());
			} else {
				throw new UsernameNotFoundException("invalid user request !");
			}
		}
		catch(Exception e) {
			e.printStackTrace();
			return "error";
		}
		
	}
}
