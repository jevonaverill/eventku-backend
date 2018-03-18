package com.example.repository;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.example.model.Comment;

/**
 * Created by jevon.averill on 12/10/2017.
 */
@Repository
public interface CommentRepository extends MongoRepository<Comment, String> {
  Comment findByCommentIdAndMarkForDeleteIsFalse(String commentId);

  List<Comment> findAllByCommentIdAndMarkForDeleteIsFalse(String commentId);

  List<Comment> findAllByThreadIdAndMarkForDeleteIsFalse(String threadId);
}
