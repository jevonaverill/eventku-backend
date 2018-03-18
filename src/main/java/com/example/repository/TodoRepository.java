package com.example.repository;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.example.model.Todo;

/**
 * Created by jevon.averill on 31/08/2017.
 */
@Repository
public interface TodoRepository extends MongoRepository<Todo, String> {
  Todo findByTodoIdAndMarkForDeleteIsFalse(String todoId);

  List<Todo> findAllByMarkForDeleteIsFalse();
}
