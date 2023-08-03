
package com.jencf.codefellowship.models;

import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
public class PostUser {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    @ManyToOne
    private ApplicationUser user;

    private String body;
    private LocalDateTime createdAt;

    public PostUser() {
        this.createdAt = LocalDateTime.now();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public ApplicationUser getUser() {
        return user;
    }

    public void setUser(ApplicationUser user) {
        this.user = user;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }
}


//package com.jencf.codefellowship.models;

//import jakarta.persistence.*;
//import java.time.LocalDateTime;
//
//@Entity
//public class PostUser {
//    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    Long id;
//
//    String body;
//
//    LocalDateTime createdAt;
//
//    @ManyToOne
//    ApplicationUser applicationUser;
//
//    public PostUser() {
//    }
//
//    public PostUser(String body, LocalDateTime createdAt, ApplicationUser applicationUser) {
//        this.body = body;
//        this.createdAt = createdAt;
//        this.applicationUser = applicationUser;
//    }
//
//    public Long getId() {
//        return id;
//    }
//
//    public void setId(Long id) {
//        this.id = id;
//    }
//
//    public String getBody() {
//        return body;
//    }
//
//    public void setBody(String body) {
//        this.body = body;
//    }
//
//    public LocalDateTime getCreatedAt() {
//        return createdAt;
//    }
//
//    public void setCreatedAt(LocalDateTime createdAt) {
//        this.createdAt = createdAt;
//    }
//
//    public ApplicationUser getApplicationUser() {
//        return applicationUser;
//    }
//
//    public void setApplicationUser(ApplicationUser applicationUser) {
//        this.applicationUser = applicationUser;
//    }
//}

