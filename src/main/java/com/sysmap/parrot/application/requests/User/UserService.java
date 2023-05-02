package com.sysmap.parrot.application.requests.User;

import com.sysmap.parrot.application.requests.User.CreateUser.CreateUserRequest;
import com.sysmap.parrot.application.requests.User.GetUser.GetUserResponse;
import com.sysmap.parrot.application.requests.User.UpdateUser.UpdateUserRequest;
import com.sysmap.parrot.application.requests.User.UpdateUser.UpdateUserResponse;
import com.sysmap.parrot.domain.entities.User;
import com.sysmap.parrot.infrastructure.data.IUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class UserService implements IUserService {

    @Autowired
    private IUserRepository _userRepository;

    PasswordEncoder passwordEncoder;

    public String createUser(CreateUserRequest request) {

        this.passwordEncoder = new BCryptPasswordEncoder();
        String encodedPassword = this.passwordEncoder.encode(request.password);

        var user = new User(request.name, request.email, encodedPassword, null);

        _userRepository.save(user);

        return user.getId().toString();
    }

    public GetUserResponse getUserById(String id) {
        var uuid = UUID.fromString(id);
        var user = _userRepository.findById(uuid).get();

        var response = new GetUserResponse(user.getId(), user.getName(), user.getEmail());

        return response;
    }

    public UpdateUserResponse updateUserById(String id, UpdateUserRequest request) {
        var uuid = UUID.fromString(id);
        var user = _userRepository.findById(uuid).get();

        this.passwordEncoder = new BCryptPasswordEncoder();
        String encodedPassword = this.passwordEncoder.encode(request.password);

        user.setName(request.name);
        user.setEmail(request.email);
        user.setPassword(encodedPassword);
        user.setPictureUrl(request.pictureUrl);

        _userRepository.save(user);

        var response = new UpdateUserResponse(
                user.getId(),
                user.getName(),
                user.getEmail(),
                user.getPassword(),
                user.getPictureUrl()
        );

        return response;
    }
}
