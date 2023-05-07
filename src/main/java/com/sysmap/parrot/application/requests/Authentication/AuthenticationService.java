package com.sysmap.parrot.application.requests.Authentication;

import com.sysmap.parrot.application.requests.Authentication.Authenticate.AuthenticateRequest;
import com.sysmap.parrot.application.requests.Authentication.Authenticate.AuthenticateResponse;
import com.sysmap.parrot.application.requests.Security.IJwtService;
import com.sysmap.parrot.application.requests.User.IUserService;
import com.sysmap.parrot.infrastructure.data.IUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.security.core.userdetails.UserDetails;
//import org.springframework.security.core.userdetails.UserDetailsService;
//import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthenticationService implements IAuthenticationService {

//    @Autowired
//    private IUserRepository _userRepository;
//    @Override
//    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
//        return _userRepository.getUserByEmail(username).get();
//    }

    @Autowired
    private IUserService _userService;

    @Autowired
    private IJwtService _jwtService;

    @Autowired
    private PasswordEncoder _passwordEncoder;

    public AuthenticateResponse authenticate(AuthenticateRequest request) throws Exception {
        var user = _userService.getUser(request.email);

        if(user == null) {
            return null;
        }

        if (!_passwordEncoder.matches(request.password, user.getPassword())) {
            throw new Exception("Credenciais inválidas!");
        }

        var token = _jwtService.generateToken(user.getId());

        var response = new AuthenticateResponse();

        response.setUserId(user.getId());
        response.setToken(token);

        return response;

    }
}
