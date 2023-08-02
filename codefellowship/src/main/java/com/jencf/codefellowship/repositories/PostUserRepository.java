package com.jencf.codefellowship.repositories;

import com.jencf.codefellowship.models.ApplicationUser;
import com.jencf.codefellowship.models.PostUser;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.*;

public interface PostUserRepository extends JpaRepository<PostUser, Long> {

}

