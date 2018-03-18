package com.example.model;

import java.io.Serializable;

import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import net.karneim.pojobuilder.GeneratePojoBuilder;

/**
 * Created by jevon.averill on 12/10/2017.
 */
@GeneratePojoBuilder
@JsonIgnoreProperties(ignoreUnknown = true)
public class UserRequest implements Serializable {
  private static final long serialVersionUID = 3969920444036686167L;

  @NotNull
  private String userId;

  private String fullName;

  private String address;

  @NotNull
  private String email;

  private String phoneNumber;

  public String getUserId() {
    return userId;
  }

  public void setUserId(String userId) {
    this.userId = userId;
  }

  public String getFullName() {
    return fullName;
  }

  public void setFullName(String fullName) {
    this.fullName = fullName;
  }

  public String getAddress() {
    return address;
  }

  public void setAddress(String address) {
    this.address = address;
  }

  public String getEmail() {
    return email;
  }

  public void setEmail(String email) {
    this.email = email;
  }

  public String getPhoneNumber() {
    return phoneNumber;
  }

  public void setPhoneNumber(String phoneNumber) {
    this.phoneNumber = phoneNumber;
  }

  @Override
  public String toString() {
    return "UserRequest{" + "userId='" + userId + '\'' + ", fullName='" + fullName + '\''
        + ", address = '" + address + '\'' + ", email='" + email + '\'' + ", phoneNumber='"
        + phoneNumber + '\'' + '}';
  }
}
