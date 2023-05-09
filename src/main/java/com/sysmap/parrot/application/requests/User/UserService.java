package com.sysmap.parrot.application.requests.User;

import com.sysmap.parrot.application.requests.User.CreateUser.CreateUserRequest;
import com.sysmap.parrot.application.requests.User.GetUser.GetUserResponse;
import com.sysmap.parrot.application.requests.User.UpdateUser.UpdateUserRequest;
import com.sysmap.parrot.application.requests.User.UpdateUser.UpdateUserResponse;
import com.sysmap.parrot.application.requests.fileUpload.IFileUploadService;
import com.sysmap.parrot.domain.entities.Connection;
import com.sysmap.parrot.domain.entities.User;
import com.sysmap.parrot.exception.badRequestException.BadRequestException;
import com.sysmap.parrot.exception.notFoundException.NotFoundException;
import com.sysmap.parrot.infrastructure.data.IUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.UUID;

@Service
public class UserService implements IUserService {

    @Autowired
    private IUserRepository _userRepository;

    @Autowired
    private PasswordEncoder _passwordEncoder;

    @Autowired
    private IFileUploadService _fileUploadService;

    public String createUser(CreateUserRequest request) {

        var registeredUser = _userRepository.getUserByEmail(request.email);

        if (registeredUser.isPresent()) {
            throw new BadRequestException("This email is already in use. Try another one!");
        }
        var encodedPassword = _passwordEncoder.encode(request.password);

        var user = new User(request.name, request.email);
        user.setPassword(encodedPassword);

        _userRepository.save(user);

        return user.getId().toString();
    }

    public GetUserResponse getUserById(String id) {
        var userById = _userRepository.findById(UUID.fromString(id));

        if (userById.isEmpty()) {
            throw new NotFoundException("There is no user with this id!");
        }

        var user = userById.get();

        var response = new GetUserResponse(user.getId(), user.getName(), user.getEmail(), user.getPictureUrl());

        return response;
    }

    public UpdateUserResponse updateUserById(String id, UpdateUserRequest request) {
        var userById = _userRepository.findById(UUID.fromString(id));

        if(userById.isEmpty()) {
            throw new NotFoundException("There is no user with this id!");
        }

        var user = userById.get();

        var encodedPassword = _passwordEncoder.encode(request.password);

        user.setName(request.name);
        user.setEmail(request.email);
        user.setPassword(encodedPassword);

        _userRepository.save(user);

        var response = new UpdateUserResponse(
                user.getId(),
                user.getName(),
                user.getEmail(),
                user.getPassword()
        );

        return response;
    }

    public String deleteUserById(String id) {
        var userById = _userRepository.findById(UUID.fromString(id));

        if(userById.isEmpty()) {
            throw new NotFoundException("There is no user with this id!");
        }

        var user = userById.get();

        _userRepository.delete(user);

        return "Usuário excluído com sucesso";
    }

    public void uploadPhoto(String id, MultipartFile photo) {
        var userById = _userRepository.findById(UUID.fromString(id));

        if(userById.isEmpty()) {
            throw new NotFoundException("There is no user with this id!");
        }

        var user = userById.get();

        var photoUri = "";

        try {
            var fileName = user.getId() + "." + photo.getOriginalFilename().substring(photo.getOriginalFilename().lastIndexOf(".") + 1);
            photoUri = _fileUploadService.upload(photo, fileName);
        } catch (BadRequestException e) {
            throw new BadRequestException("Unable to upload image!");
        }

        user.setPictureUrl(photoUri);
        _userRepository.save(user);

    }

    public User getUser(String email) {
        return _userRepository.getUserByEmail(email).get();
    }

    public User findUserById(UUID id) {
        return _userRepository.findById(id).get();
    }

    public List<User> getAllUsers() {
        return _userRepository.findAll();
    }
}
