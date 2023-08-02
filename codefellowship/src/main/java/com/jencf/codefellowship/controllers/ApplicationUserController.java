package com.jencf.codefellowship.controllers;


import com.jencf.codefellowship.models.ApplicationUser;
import com.jencf.codefellowship.models.PostUser;
import com.jencf.codefellowship.repositories.ApplicationUserRepo;
import com.jencf.codefellowship.repositories.PostUserRepository;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
//import org.springframework.security.core.Authentication;
//import org.springframework.security.core.context.SecurityContextHolder;
//import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.view.RedirectView;

import java.security.Principal;
import java.time.LocalDate;
import java.util.Optional;

@Controller
public class ApplicationUserController {

    @Autowired
    ApplicationUserRepo applicationUserRepo;

    @Autowired
    PasswordEncoder passwordEncoder;

    @Autowired
    HttpServletRequest request;

    @Autowired
    PostUserRepository postUserRepository;

    @GetMapping("/login")
    public String getLoginPage() {
        return "login";
    }

    @GetMapping("/signup")
    public String getSignupPage() {
        return "signup";
    }

    @PostMapping("/signup")
    public RedirectView postSignup(String username, String password, String firstName, String lastName,
                                   String dateOfBirth, String bio) {
        ApplicationUser user = new ApplicationUser();
        user.setUsername(username);
        user.setPassword(passwordEncoder.encode(password));
        user.setFirstName(firstName);
        user.setLastName(lastName);
        user.setDateOfBirth(LocalDate.parse(dateOfBirth));
        user.setBio(bio);
        applicationUserRepo.save(user);
        authWithHttpServletRequest(username, password);
        return new RedirectView("/");
    }

    public void authWithHttpServletRequest(String username, String password) {
        try {
            request.login(username, password);
        } catch (ServletException e) {
            System.out.println("Cannot login");
            e.printStackTrace();
        }
    }

//    @GetMapping("/")
//    public String getIndexPage(Model model, Principal principal) {
//        if (principal != null) {
//            String username = principal.getName();
//            ApplicationUser user = applicationUserRepo.findByUsername(username);
//            model.addAttribute("username", username);
//            model.addAttribute("firstName", user.getFirstName());
//            model.addAttribute("lastName", user.getLastName());
//            model.addAttribute("dateOfBirth", user.getDateOfBirth());
//            model.addAttribute("bio", user.getBio());
//        }
//        return "index";
//    }
    @GetMapping("/")
    public String getIndexPage(Principal principal) {
    if (principal != null) {
        return "index";
    }
    return "redirect:/login";
    }



    //    lab 17

//    @GetMapping("/myprofile")
//    public String getUserProfile() {
//        return "myprofile";
//    }


    @GetMapping("/myprofile")
    public String getUserProfile(Model model, Principal principal) {
        if (principal != null) {
            String username = principal.getName();
            ApplicationUser user = applicationUserRepo.findByUsername(username);
            model.addAttribute("user", user);
            System.out.println("getUserProfile");
            return "myprofile";
        }
        return "redirect:/login";
    }




    @PostMapping("/myprofile")
    public RedirectView createPost(String body, Principal principal) {
        if (principal != null) {
            String username = principal.getName();
            ApplicationUser user = applicationUserRepo.findByUsername(username);
            PostUser post = new PostUser();
            post.setBody(body);
            post.setUser(user);
            postUserRepository.save(post);
        }
        return new RedirectView("/myprofile");
    }

//    @GetMapping("/users/{id}")
//    public String getUserInfo(Model model, Principal principal, @PathVariable Long id) {
//        if (principal != null) {
//            String username = principal.getName();
//            model.addAttribute("username", username);
//        }
//
//        Optional<ApplicationUser> foundUserOptional = applicationUserRepo.findById(id);
//        if (foundUserOptional.isPresent()) {
//            ApplicationUser foundUser = foundUserOptional.get();
//            model.addAttribute("foundUser", foundUser);
//            return "users";
//
//        } else {
//            return "error";
//        }
//    }





}










