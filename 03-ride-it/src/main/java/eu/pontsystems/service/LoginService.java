package eu.pontsystems.service;

import java.util.ArrayList;




import java.util.HashSet;
import java.util.List;
import java.util.Set;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import eu.pontsystems.entity.User;
import eu.pontsystems.repository.UserRepository;

@Service("driverPassengerDetailsService")
@Transactional
public class LoginService implements UserDetailsService {
	
	
	@Autowired
	private UserRepository userRepository;
	
	@Override
	@Transactional(readOnly = true)
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		User userData = userRepository.findByUsername(username);
		List<GrantedAuthority> grantedAuthorities = new ArrayList<GrantedAuthority>();
		grantedAuthorities.add(new SimpleGrantedAuthority("ROLE_" + userData.getRole()));
		org.springframework.security.core.userdetails.User newUser = new org.springframework.security.core.userdetails.User(userData.getUsername(), userData.getPassword(), grantedAuthorities);
		return newUser;
		
	}
	
}
