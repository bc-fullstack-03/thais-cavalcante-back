package com.sysmap.parrot.application.requests.User;

import com.sysmap.parrot.application.requests.User.CreateUser.CreateUserRequest;
import com.sysmap.parrot.application.requests.User.GetUser.GetUserResponse;
import com.sysmap.parrot.domain.entities.User;
import com.sysmap.parrot.infrastructure.data.IUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    private IUserRepository _userRepository;

    PasswordEncoder passwordEncoder;

    public String createUser(CreateUserRequest request) {

        this.passwordEncoder = new BCryptPasswordEncoder();
        String encodedPassword = this.passwordEncoder.encode(request.password);

        var user = new User(request.name, request.email, encodedPassword);

        _userRepository.save(user);

        return user.getId().toString();
    }

    public GetUserResponse getUserByEmail(String email) {
        var user = _userRepository.getUserByEmail(email).get();

        var response = new GetUserResponse(user.getId(), user.getName(), user.getEmail());

        return response;
    }
}
