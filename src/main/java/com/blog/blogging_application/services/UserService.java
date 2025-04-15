package com.blog.blogging_application.services;

import java.util.List;


import com.blog.blogging_application.payloads.UserDto;

public interface UserService {

    UserDto createUser(UserDto user);

    UserDto updateUser(UserDto user, Integer userId);

    UserDto getUserById(Integer userId);

    List<UserDto> getAllUsers();

    void deleteUser(Integer UserId);

}

