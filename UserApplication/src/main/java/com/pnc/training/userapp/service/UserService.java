package com.pnc.training.userapp.service;

import com.pnc.training.userapp.model.User;
import com.pnc.training.userapp.model.dto.UserDto;

/**
 * Created by PL64640 on 8/11/2017.
 */
public interface UserService {

    UserDto getAllUsers();

    UserDto getUser(int userId);

    UserDto addUser(User user);

    UserDto updateUser(User user);

    UserDto deleteUser(int userId);

    UserDto getUserByRole(String role);

    UserDto getUserByLocation(String location);

    UserDto getUserByName(String userName);
}
