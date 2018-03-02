package com.pnc.training.userapp.service;

import com.pnc.training.userapp.constants.Constants;
import com.pnc.training.userapp.model.User;
import com.pnc.training.userapp.model.dto.ErrorDto;
import com.pnc.training.userapp.model.dto.UserDto;
import com.pnc.training.userapp.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.Optional;

/**
 * Created by PL64640 on 8/11/2017.
 */
@Service("userService")
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public UserDto getAllUsers() {
        UserDto<Iterable<User>> userDto = new UserDto<>();
        ErrorDto<String> errorDto = new ErrorDto<>();

        Iterable<User> userEntities = userRepository.findAll();
        if (((Collection<?>) userEntities).size() > 0) {
            userDto.setStatus(UserDto.status.SUCCESS.toString());
            userDto.setData(userEntities);
        } else {
            errorDto.setErrorCode(Constants.NOT_FOUND);
            errorDto.setErrorMessage("No Users was found in our Database.");
            userDto.setStatus(UserDto.status.FAIL.toString());
            userDto.setErrorData(errorDto);
        }

        return userDto;
    }

    @Override
    public UserDto getUser(int userId) {
        UserDto<User> userDto = new UserDto<>();
        ErrorDto<String> errorDto = new ErrorDto<>();

        Optional<User> user = Optional.ofNullable(userRepository.findOne(userId));
        if (user.isPresent()) {
            userDto.setData(user.get());
            userDto.setStatus(UserDto.status.SUCCESS.toString());
        } else {
            errorDto.setErrorCode(Constants.NOT_FOUND);
            errorDto.setErrorMessage("Users was found in our Database.");
            userDto.setStatus(UserDto.status.FAIL.toString());
            userDto.setErrorData(errorDto);
        }

        return userDto;
    }

    @Override
    public UserDto addUser(User user) {
        UserDto<User> userDto = new UserDto<>();

        User newUser = userRepository.save(user);
        userDto.setData(newUser);
        userDto.setStatus(UserDto.status.SUCCESS.toString());

        return userDto;
    }

    @Override
    public UserDto updateUser(User user) {
        UserDto<User> userDto = new UserDto<>();
        ErrorDto<String> errorDto = new ErrorDto<>();

        if (userRepository.exists(user.getUserId())) {
            User newUser = userRepository.save(user);
            userDto.setData(newUser);
            userDto.setStatus(UserDto.status.SUCCESS.toString());
        } else {
            errorDto.setErrorCode(Constants.NOT_FOUND);
            errorDto.setErrorMessage("User is not available in our database.");
            userDto.setStatus(UserDto.status.FAIL.toString());
            userDto.setErrorData(errorDto);
        }

        return userDto;
    }

    @Override
    public UserDto deleteUser(int userId) {
        UserDto<User> userDto = new UserDto<>();
        ErrorDto<String> errorDto = new ErrorDto<>();

        Optional<User> user = Optional.ofNullable(userRepository.findOne(userId));
        if (user.isPresent()) {
            userRepository.delete(userId);
            userDto.setData(user.get());
            userDto.setStatus(UserDto.status.SUCCESS.toString());
        } else {
            errorDto.setErrorCode(Constants.NOT_FOUND);
            errorDto.setErrorMessage("User is not available in our database.");
            userDto.setStatus(UserDto.status.FAIL.toString());
            userDto.setErrorData(errorDto);
        }

        return userDto;
    }

    @Override
    public UserDto getUserByRole(String role) {
        UserDto<Iterable<User>> userDto = new UserDto<>();
        ErrorDto<String> errorDto = new ErrorDto<>();

        Iterable<User> userEntities = userRepository.findByRole(role);
        if (((Collection<?>) userEntities).size() > 0) {
            userDto.setStatus(UserDto.status.SUCCESS.toString());
            userDto.setData(userEntities);
        } else {
            errorDto.setErrorCode(Constants.NOT_FOUND);
            errorDto.setErrorMessage("No Users was found in our Database for the given role.");
            userDto.setStatus(UserDto.status.FAIL.toString());
            userDto.setErrorData(errorDto);
        }

        return userDto;
    }

    @Override
    public UserDto getUserByLocation(String location) {
        UserDto<Iterable<User>> userDto = new UserDto<>();
        ErrorDto<String> errorDto = new ErrorDto<>();

        Iterable<User> userEntities = userRepository.findByLocation(location);
        if (((Collection<?>) userEntities).size() > 0) {
            userDto.setStatus(UserDto.status.SUCCESS.toString());
            userDto.setData(userEntities);
        } else {
            errorDto.setErrorCode(Constants.NOT_FOUND);
            errorDto.setErrorMessage("No Users was found in our Database for the given location.");
            userDto.setStatus(UserDto.status.FAIL.toString());
            userDto.setErrorData(errorDto);
        }

        return userDto;
    }

    @Override
    public UserDto getUserByName(String userName) {
        UserDto<Iterable<User>> userDto = new UserDto<>();
        ErrorDto<String> errorDto = new ErrorDto<>();

        Iterable<User> userEntities = userRepository.findByUserName(userName);
        if (((Collection<?>) userEntities).size() > 0) {
            userDto.setStatus(UserDto.status.SUCCESS.toString());
            userDto.setData(userEntities);
        } else {
            errorDto.setErrorCode(Constants.NOT_FOUND);
            errorDto.setErrorMessage("No Users was found in our Database for the given username.");
            userDto.setStatus(UserDto.status.FAIL.toString());
            userDto.setErrorData(errorDto);
        }

        return userDto;
    }
}