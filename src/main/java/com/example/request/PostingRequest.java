package com.example.request;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/**
 * Created by jevon.averill on 10/13/17.
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class PostingRequest implements Serializable {

  private static final long serialVersionUID = 1717375068553074499L;
  private String message;
  private String memberId;
  private String postingDate;
  private String postingHour;

  public PostingRequest() {}

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

  public String getPostingDate() {
    return postingDate;
  }

  public void setPostingDate(String postingDate) {
    this.postingDate = postingDate;
  }

  public String getPostingHour() {
    return postingHour;
  }

  public void setPostingHour(String postingHour) {
    this.postingHour = postingHour;
  }

  @Override
  public String toString() {
    return "PostingRequest{" + "message='" + message + '\'' + ", memberId='" + memberId + '\''
        + ", postingDate='" + postingDate + '\'' + ", postingHour='" + postingHour + '\'' + '}';
  }
}
