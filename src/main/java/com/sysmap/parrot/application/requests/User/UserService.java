package com.sysmap.parrot.application.requests.User;

import com.sysmap.parrot.application.requests.User.CreateUser.CreateUserRequest;
import com.sysmap.parrot.application.requests.User.GetUser.GetUserResponse;
import com.sysmap.parrot.application.requests.User.UpdateUser.UpdateUserRequest;
import com.sysmap.parrot.application.requests.User.UpdateUser.UpdateUserResponse;
import com.sysmap.parrot.domain.entities.User;
import com.sysmap.parrot.infrastructure.data.IUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class UserService implements IUserService {

    @Autowired
    private IUserRepository _userRepository;

    @Autowired
    private PasswordEncoder _passwordEncoder;

    public String createUser(CreateUserRequest request) {

        var encodedPassword = _passwordEncoder.encode(request.password);

        var user = new User(request.name, request.email);
        user.setPassword(encodedPassword);

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

        var encodedPassword = _passwordEncoder.encode(request.password);

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

    public String deleteUserById(String id) {
        var uuid = UUID.fromString(id);
        var user = _userRepository.findById(uuid).get();

        _userRepository.delete(user);

        var response = "Usuário excluído com sucesso";
        return response;
    }
}
