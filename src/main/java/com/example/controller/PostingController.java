package com.example.controller;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.concurrent.TimeUnit;

import javax.validation.Valid;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.example.model.Comment;
import com.example.model.Posting;
import com.example.model.PostingResponse;
import com.example.repository.CommentRepository;
import com.example.repository.PostingRepository;
import com.example.request.PostingRequest;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

/**
 * Created by jevon.averill on 30/08/2017.
 */
@RestController
@RequestMapping(value = "/api/Thread")
@Api(value = "Test", description = "Test")
public class PostingController {

  @Autowired
  private PostingRepository postingRepository;

  @Autowired
  private CommentRepository commentRepository;

  /**
   * <a href=
   * "https://cloud.google.com/appengine/docs/flexible/java/how-instances-are-managed#health_checking">
   * App Engine health checking</a> requires responding with 200 to {@code /_ah/health}.
   */
  @GetMapping
  @RequestMapping(value = "/_ah/health", method = RequestMethod.GET)
  @ApiOperation(value = "hc", notes = "healthcheck")
  public String healthy() {
    // Message body required though ignored
    return "Still surviving.";
  }

  @PostMapping
  @RequestMapping(value = "/saveThread", method = RequestMethod.POST)
  @ApiOperation(value = "Save posting", notes = "save posting info to DB")
  public void saveThread(@RequestBody @Valid PostingRequest posting, @RequestParam String eventId)
      throws Exception {
    Posting newPosting = new Posting();
    BeanUtils.copyProperties(posting, newPosting);
    newPosting.setEventId(eventId);
    String[] eventDates = posting.getPostingDate().split("-");
    String[] eventDateTime = posting.getPostingHour().split(":");
    String neew = posting.getPostingDate() + ":" + posting.getPostingHour();
    Date eventDate = new SimpleDateFormat("yyyy-MM-dd:HH:mm").parse(neew);
    Date date = new Date();
    if (TimeUnit.MILLISECONDS.toDays(date.getTime() - eventDate.getTime()) == 1) {
      newPosting.setPostingDate("Yesterday");
    } else {
      if (TimeUnit.MILLISECONDS.toSeconds(date.getTime() - eventDate.getTime()) < 60) {
        newPosting.setPostingDate(
            Long.toString(TimeUnit.MILLISECONDS.toSeconds(date.getTime() - eventDate.getTime()))
                + " seconds ago");
      } else if (TimeUnit.MILLISECONDS.toMinutes(date.getTime() - eventDate.getTime()) < 60) {
        newPosting.setPostingDate(
            Long.toString(TimeUnit.MILLISECONDS.toMinutes(date.getTime() - eventDate.getTime()))
                + " minutes ago");
      } else if (TimeUnit.MILLISECONDS.toHours(date.getTime() - eventDate.getTime()) < 24) {
        newPosting.setPostingDate(
            Long.toString(TimeUnit.MILLISECONDS.toHours(date.getTime() - eventDate.getTime()))
                + " hours ago");
      } else {
        newPosting.setPostingDate(
            Long.toString(TimeUnit.MILLISECONDS.toDays(date.getTime() - eventDate.getTime()))
                + " days ago");
      }
    }
    this.postingRepository.save(newPosting);
  }

  @GetMapping
  @RequestMapping(value = "/id/{eventId}", method = RequestMethod.GET)
  @ApiOperation(value = "Find All Posting By Event Id",
      notes = "find all Posting By EventID from DB")
  public PostingResponse findThreadByEventId(@PathVariable String eventId) throws Exception {
    PostingResponse PostingResponse = new PostingResponse();
    try {
      List<Posting> result =
          this.postingRepository.findAllByEventIdAndMarkForDeleteIsFalse(eventId);
      PostingResponse.setResults(result);
      PostingResponse.setCount(result.size());
    } catch (Exception e) {
      PostingResponse.setStatus("failed");
      PostingResponse.setMessage(e.getMessage());
      return PostingResponse;
    }
    PostingResponse.setStatus("success");
    PostingResponse.setMessage("success");
    return PostingResponse;
  }

  @PostMapping
  @RequestMapping(value = "/deleteThreadByThreadId", method = RequestMethod.POST)
  @ApiOperation(value = "Delete Posting By ThreadId", notes = "Delete Posting By ThreadId")
  public void deleteThreadByThreadId(@RequestParam String threadId) throws Exception {
    Posting posting = this.postingRepository.findByEventIdAndMarkForDeleteIsFalse(threadId);
    posting.setMarkForDelete(true);
    List<Comment> commentList =
        this.commentRepository.findAllByThreadIdAndMarkForDeleteIsFalse(threadId);
    commentList.forEach(comment -> {
      comment.setMarkForDelete(true);
      this.commentRepository.save(comment);
    });
    this.postingRepository.save(posting);
  }

  @PostMapping
  @RequestMapping(value = "/updateThreadByThreadId", method = RequestMethod.POST)
  @ApiOperation(value = "Update Posting By ThreadId", notes = "UpdateThreadByThreadId")
  public Boolean updateThreadByThreadId(@RequestBody PostingRequest threadReq,
      @RequestParam String threadId) throws Exception {
    Posting threadUpdate = this.postingRepository.findByEventIdAndMarkForDeleteIsFalse(threadId);
    if (threadUpdate == null)
      throw new IllegalStateException("Posting does not exist. Failed to Update");
    threadUpdate.setMemberId(threadReq.getMemberId());
    threadUpdate.setMessage(threadReq.getMessage());
    this.postingRepository.save(threadUpdate);
    return true;
  }

  @PostMapping
  @RequestMapping(value = "/like/id/{threadId}", method = RequestMethod.POST)
  @ApiOperation(value = "Like a post", notes = "Like a post")
  public Boolean likePost(@PathVariable String threadId) {
    Posting posting = this.postingRepository.findByThreadIdAndMarkForDeleteIsFalse(threadId);
    posting.setLikes(posting.getLikes() + 1);
    this.postingRepository.save(posting);
    return true;
  }

  @PostMapping
  @RequestMapping(value = "/unlike/id/{threadId}", method = RequestMethod.POST)
  @ApiOperation(value = "unLike a post", notes = "unLike a post")
  public Boolean unlikePost(@PathVariable String threadId) {
    Posting posting = this.postingRepository.findByThreadIdAndMarkForDeleteIsFalse(threadId);
    posting.setLikes(posting.getLikes() - 1);
    this.postingRepository.save(posting);
    return true;
  }
}
