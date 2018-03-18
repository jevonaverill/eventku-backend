package com.example.repository;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.example.model.Event;

/**
 * Created by jevon.averill on 12/10/2017.
 */
@Repository
public interface EventRepository extends MongoRepository<Event, String> {
  Event findByEventIdAndMarkForDeleteIsFalse(String eventId);

  List<Event> findAllByMarkForDeleteIsFalse();
}
