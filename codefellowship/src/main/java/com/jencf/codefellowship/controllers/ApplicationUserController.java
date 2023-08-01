package com.jencf.codefellowship.controllers;


import com.jencf.codefellowship.models.ApplicationUser;
import com.jencf.codefellowship.repositories.ApplicationUserRepo;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
//import org.springframework.security.core.Authentication;
//import org.springframework.security.core.context.SecurityContextHolder;
//import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.view.RedirectView;

import java.security.Principal;
import java.time.LocalDate;

@Controller
public class ApplicationUserController {

    @Autowired
    ApplicationUserRepo applicationUserRepo;

    @Autowired
    PasswordEncoder passwordEncoder;

    @Autowired
    HttpServletRequest request;

    @GetMapping("/login")
    public String getLoginPage() {
        return "login";
    }

    @GetMapping("/signup")
    public String getSignupPage() {
        return "signup";
    }

//    @GetMapping("/logout")
//    public RedirectView logout(HttpServletRequest request, HttpServletResponse response) throws ServletException {
//        request.logout();
//        return new RedirectView("/");
//    }


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

    @GetMapping("/")
    public String getIndexPage(Model model, Principal principal) {
        if (principal != null) {
            String username = principal.getName();
            ApplicationUser user = applicationUserRepo.findByUsername(username);
            model.addAttribute("username", username);
            model.addAttribute("firstName", user.getFirstName());
            model.addAttribute("lastName", user.getLastName());
            model.addAttribute("dateOfBirth", user.getDateOfBirth());
            model.addAttribute("bio", user.getBio());
        }
        return "index.html";
    }
}






