package com.example.model;

import java.io.Serializable;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/**
 * Created by jevon.averill on 14/10/2017.
 */
@JsonIgnoreProperties
public class PostingResponse implements Serializable {
  private static final long serialVersionUID = -7818696638456877478L;

  private int count;
  private List<Posting> results;
  private String status;
  private String message;

  public int getCount() {
    return count;
  }

  public void setCount(int count) {
    this.count = count;
  }

  public List<Posting> getResults() {
    return results;
  }

  public void setResults(List<Posting> results) {
    this.results = results;
  }

  public String getStatus() {
    return status;
  }

  public void setStatus(String status) {
    this.status = status;
  }

  public String getMessage() {
    return message;
  }

  public void setMessage(String message) {
    this.message = message;
  }

  @Override
  public String toString() {
    return "PostingResponse{" + "count=" + count + ", results=" + results + ", status='" + status
        + '\'' + ", message='" + message + '\'' + '}';
  }
}