package com.institute.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails; 
import org.springframework.security.core.userdetails.UserDetailsService; 
import org.springframework.security.core.userdetails.UsernameNotFoundException; 
import org.springframework.security.crypto.password.PasswordEncoder; 
import org.springframework.stereotype.Service; 

import com.institute.Model.UserInfo;
import com.institute.Repository.UserInfoRepository;

import java.util.Optional;

@Service
public class UserInfoService implements UserDetailsService {


    @Autowired
    private UserInfoRepository repository; 
  
    @Autowired
    private PasswordEncoder encoder;
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

		Optional<UserInfo> userDetail = repository.findByName(username);

		// Converting userDetail to UserDetails
		
		return userDetail.map(UserInfoDetails::new)
				.orElseThrow(() -> new UsernameNotFoundException("User not found " + username));
	}

	public String addUser(UserInfo userInfo) {
		userInfo.setPassword(encoder.encode(userInfo.getPassword()));
		Optional<UserInfo> findByName = repository.findByName(userInfo.getName());
		if(findByName.isPresent()){
			return "User already exists";
		}
	
		repository.save(userInfo);
		return "SUCCESS";
	}

}
