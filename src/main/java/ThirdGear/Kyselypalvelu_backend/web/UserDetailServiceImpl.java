package ThirdGear.Kyselypalvelu_backend.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import ThirdGear.Kyselypalvelu_backend.domain.UserRepo;
import ThirdGear.Kyselypalvelu_backend.domain.User;

@Service
public class UserDetailServiceImpl implements UserDetailsService {
	private final UserRepo userRepo;
	
	@Autowired
	public UserDetailServiceImpl(UserRepo userRepo) {
		this.userRepo = userRepo;
	}
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		
		User curruser = userRepo.findByUsername(username);
        UserDetails user = new org.springframework.security.core.userdetails.User(username, curruser.getPasswordHash(), 
        		AuthorityUtils.createAuthorityList(curruser.getRole()));
        return user;
	}
	
}
