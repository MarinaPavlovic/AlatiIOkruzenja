package com.singidunum.UserService.service;


import com.singidunum.UserService.DTO.LoginParams;
import com.singidunum.UserService.DTO.UserDTO;

public interface IUserService {

    UserDTO createUser(UserDTO userDTO);
    UserDTO getUser(Integer id);


    UserDTO findUser(LoginParams loginParams);

    void deleteUser(Integer id);

}
