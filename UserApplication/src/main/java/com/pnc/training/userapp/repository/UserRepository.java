package com.pnc.training.userapp.repository;

import com.pnc.training.userapp.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by PL64640 on 8/11/2017.
 */
public interface UserRepository extends JpaRepository<User, Integer> {

    Iterable<User> findByUserName(String userName);

    Iterable<User> findByRole(String role);

    Iterable<User> findByLocation(String location);
}
