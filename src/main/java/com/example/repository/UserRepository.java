package com.example.repository;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.example.model.User;

/**
 * Created by jevon.averill on 12/10/2017.
 */
@Repository
public interface UserRepository extends MongoRepository<User, String> {
  User findByUserIdAndMarkForDeleteIsFalse(String userId);

  List<User> findAllByMarkForDeleteIsFalse();
}
