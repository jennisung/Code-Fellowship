package com.jencf.codefellowship.models;

import jakarta.persistence.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import java.time.LocalDate;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
public class ApplicationUser implements UserDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    long id;

    private String username;
    private String password;
    private String firstName;
    private String lastName;
    private LocalDate dateOfBirth;
    private String bio;

    public ApplicationUser() {

    }

    public ApplicationUser(String username, String password, String firstName, String lastName,
                           LocalDate dateOfBirth, String bio) {
        this.username = username;
        this.password = password;
        this.firstName = firstName;
        this.lastName = lastName;
        this.dateOfBirth = dateOfBirth;
        this.bio = bio;
    }


    @OneToMany(mappedBy = "user")
    private List<PostUser> posts;


    //lab 18
    @ManyToMany
    @JoinTable(
            name = "followers_to_followees",
            joinColumns = {@JoinColumn(name = "userWhoIsFollowing")},
            inverseJoinColumns = {@JoinColumn(name = "FollowedUser")}
    )
    private Set<ApplicationUser> usersIFollow = new HashSet<>();

    @ManyToMany(mappedBy = "usersIFollow")
    private Set<ApplicationUser> usersWhoFollowMe = new HashSet<>();

    //end of lab 18

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public LocalDate getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(LocalDate dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public String getBio() {
        return bio;
    }

    public void setBio(String bio) {
        this.bio = bio;
    }


    //lab18 additions
    public List<PostUser> getPosts() {
        return posts;
    }

    public void setPosts(List<PostUser> posts) {
        this.posts = posts;
    }

    public Set<ApplicationUser> getUsersIFollow() {
        return usersIFollow;
    }

    public void setUsersIFollow(Set<ApplicationUser> usersIFollow) {
        this.usersIFollow = usersIFollow;
    }

    public Set<ApplicationUser> getUsersWhoFollowMe() {
        return usersWhoFollowMe;
    }

    public void setUsersWhoFollowMe(Set<ApplicationUser> usersWhoFollowMe) {
        this.usersWhoFollowMe = usersWhoFollowMe;
    }

    //end of lab 18 in model

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return null;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
