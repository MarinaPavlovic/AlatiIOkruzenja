package com.singidunum.UserService.service;


import com.singidunum.UserService.DTO.LoginParams;
import com.singidunum.UserService.DTO.UserDTO;
import com.singidunum.UserService.entity.User;
import com.singidunum.UserService.repository.IUserRepository;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService implements IUserService{

    private final IUserRepository userRepository;
    private final ModelMapper mapper;

    public UserService(IUserRepository userRepository, ModelMapper mapper) {
        this.userRepository = userRepository;
        this.mapper = mapper;
    }


    @Override
    public UserDTO createUser(UserDTO userDTO) {
        User user = mapper.map(userDTO, User.class);

        userRepository.save(user);
        return mapper.map(user, UserDTO.class);
    }

    @Override
    public UserDTO getUser(Integer id) {
        Optional<User> user= userRepository.findById(id);
        return mapper.map(user , UserDTO.class);
    }



    @Override
    public UserDTO findUser(LoginParams loginParams) {

        User user=userRepository.findUser(loginParams.getUsername(),loginParams.getPassword());

        return mapper.map(user, UserDTO.class);
    }

    @Override
    public void deleteUser(Integer id) {
        userRepository.deleteById(id);
    }
}
