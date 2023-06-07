package com.singidunum.UserService.controller;


import com.singidunum.UserService.DTO.LoginParams;
import com.singidunum.UserService.DTO.UserDTO;
import com.singidunum.UserService.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;


@RestController
@RequestMapping("user")
@CrossOrigin("*")

public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }


    @PostMapping("get")
    public ResponseEntity<?> getUser (@RequestBody @Valid LoginParams loginParams, BindingResult result ){
        if(result.hasErrors()){
            return new ResponseEntity<String>(result.getAllErrors().toString(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
        UserDTO userDTO= userService.findUser(loginParams);
        if (userDTO==null){
            return new ResponseEntity<String>("User not found.", HttpStatus.INTERNAL_SERVER_ERROR);

        }
        return  new ResponseEntity<UserDTO>(userService.findUser(loginParams), HttpStatus.OK);
    }

    @GetMapping("{id}")
    public UserDTO getById (@PathVariable("id") Integer id){
        return  userService.getUser(id);
    }

    @PostMapping("create")
    public ResponseEntity<?> createUser (@RequestBody @Valid UserDTO user, BindingResult result ){
        if(result.hasErrors()){
            return new ResponseEntity<String>(result.getAllErrors().toString(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return  new ResponseEntity<UserDTO>(userService.createUser(user), HttpStatus.OK);
    }

    @PostMapping("edit")
    public ResponseEntity<?> editUser (@Valid @RequestBody UserDTO user, BindingResult result ){
        if(result.hasErrors()){
            return new ResponseEntity<String>(result.getAllErrors().toString(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return  new ResponseEntity<UserDTO>(userService.createUser(user), HttpStatus.OK);
    }

    @DeleteMapping("{id}")
    public void deleteUser(@PathVariable("id") Integer id){
        userService.deleteUser(id);
    }


}
