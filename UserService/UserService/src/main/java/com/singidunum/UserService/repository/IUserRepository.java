package com.singidunum.UserService.repository;

import com.singidunum.UserService.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface IUserRepository extends JpaRepository<User,Integer> {

    @Query(nativeQuery = true, value = "SELECT * FROM users WHERE username= :username AND password= :password")
    User findUser(@Param("username") String username , @Param("password") String password);

}
