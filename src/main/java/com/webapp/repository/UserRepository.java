package com.webapp.repository;

import com.webapp.Model.User;
import org.springframework.data.repository.CrudRepository;

public interface UserRepository extends CrudRepository<User,Integer> {
    public Long countById(Integer id);
}
