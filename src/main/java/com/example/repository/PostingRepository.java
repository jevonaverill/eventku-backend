package com.example.repository;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.example.model.Posting;

/**
 * Created by jevon.averill on 12/10/2017.
 */
@Repository
public interface PostingRepository extends MongoRepository<Posting, String> {
  Posting findByEventIdAndMarkForDeleteIsFalse(String eventId);

  Posting findByThreadIdAndMarkForDeleteIsFalse(String threadId);

  List<Posting> findAllByEventIdAndMarkForDeleteIsFalse(String eventId);
}
