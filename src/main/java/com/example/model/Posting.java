package com.example.model;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import net.karneim.pojobuilder.GeneratePojoBuilder;

/**
 * Created by jevon.averill on 31/08/2017.
 */
@GeneratePojoBuilder
@Document
public class Posting implements Serializable {

  private static final long serialVersionUID = 7316275754622016035L;

  @Id
  @Field(value = "threadId")
  private String threadId;

  @Field(value = "eventId")
  private String eventId;

  @Field(value = "createdDate")
  private Date createdDate;

  @Field(value = "message")
  private String message;

  @Field(value = "memberId")
  private String memberId;

  @Field(value = "comments")
  private List<String> comments;

  @Field(value = "likes")
  private int likes;

  @Field(value = "postingDate")
  private String postingDate;

  @Field(value = "markForDelete")
  private boolean markForDelete;

  public Posting() {
    this.setMarkForDelete(false);
    this.setCreatedDate(new Date());
  }

  public String getThreadId() {
    return threadId;
  }

  public void setThreadId(String threadId) {
    this.threadId = threadId;
  }

  public String getEventId() {
    return eventId;
  }

  public void setEventId(String eventId) {
    this.eventId = eventId;
  }

  public Date getCreatedDate() {
    return createdDate;
  }

  public void setCreatedDate(Date createdDate) {
    this.createdDate = createdDate;
  }

  public String getMessage() {
    return message;
  }

  public void setMessage(String message) {
    this.message = message;
  }

  public String getMemberId() {
    return memberId;
  }

  public void setMemberId(String memberId) {
    this.memberId = memberId;
  }

  public List<String> getComments() {
    return comments;
  }

  public void setComments(List<String> comments) {
    this.comments = comments;
  }

  public boolean isMarkForDelete() {
    return markForDelete;
  }

  public void setMarkForDelete(boolean markForDelete) {
    this.markForDelete = markForDelete;
  }

  public int getLikes() {
    return likes;
  }

  public void setLikes(int likes) {
    this.likes = likes;
  }

  public String getPostingDate() {
    return postingDate;
  }

  public void setPostingDate(String postingDate) {
    this.postingDate = postingDate;
  }

  @Override
  public String toString() {
    return "Posting{" + "threadId='" + threadId + '\'' + ", eventId='" + eventId + '\''
        + ", createdDate=" + createdDate + ", message='" + message + '\'' + ", memberId='"
        + memberId + '\'' + ", comments=" + comments + ", likes=" + likes + ", postingDate='"
        + postingDate + '\'' + ", markForDelete=" + markForDelete + '}';
  }
}
