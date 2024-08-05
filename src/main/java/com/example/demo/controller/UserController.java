package com.example.demo.controller;

import com.example.demo.dto.request.UserRequest;
import com.example.demo.model.User;
import com.example.demo.repo.UserRepo;
import com.example.demo.service.UserService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    public User userDisplay = null;

    private final UserRepo userRepo;

    @GetMapping("/login")
    public ModelAndView showL() {
        ModelAndView modelAndView = new ModelAndView("Login");
        return modelAndView;
    }


    @GetMapping("/register")
    public ModelAndView showR() {
        ModelAndView modelAndView = new ModelAndView("Register");
        return modelAndView;
    }

    @PostMapping("/login")
    public ModelAndView login(@ModelAttribute UserRequest userRequest, Model model) {
        User user = userService.login(userRequest);
        if (user != null) {
            model.addAttribute("user", user);
            userDisplay = user;
            ModelAndView modelAndView = new ModelAndView("Login-Success");
            return modelAndView;
        } else {
            model.addAttribute("message", "Username or password is incorrect");
            ModelAndView modelAndView = new ModelAndView("Login");
            return modelAndView;
        }
    }

    @GetMapping("/success")
    public ModelAndView success(HttpServletRequest request, HttpServletResponse response, Model model) {
        response.setContentType("text/utf-8");
        String code = request.getParameter("code");
        ModelAndView modelAndView = null;
        if (code == null) {
            String error = request.getParameter("error");
            if (error != null) {
                model.addAttribute("message", "Login failed");
                modelAndView = new ModelAndView("Login");
            } else {
                model.addAttribute("user", userDisplay);
                modelAndView = new ModelAndView("Login-Success");
            }


        } else {
            String token = userService.getTokenGoogle(code);
            User user = userService.GoogleAccountGetUserInfo(token);
            model.addAttribute("user", user);
            model.addAttribute("display", "none");
            userDisplay = user;
            modelAndView = new ModelAndView("Login-Success");
        }
        return modelAndView;
    }

    @GetMapping("/forgot-password")
    public ModelAndView showP() {
        ModelAndView modelAndView = new ModelAndView("Forgot-Password");
        return modelAndView;
    }

    @PostMapping("/forgot-password")
    public ModelAndView forgotPassword(@ModelAttribute UserRequest userRequest, Model model) {
        boolean user = userService.forgotPassword(userRequest.getEmail());
        if (user) {
            model.addAttribute("user", user);
            model.addAttribute("message", "Please check your email to reset password");
            model.addAttribute("display", "block");
            model.addAttribute("color", "green");
            ModelAndView modelAndView = new ModelAndView("Forgot-Password");
            return modelAndView;
        } else {
            model.addAttribute("message", "Email is incorrect");
            model.addAttribute("display", "block");
            model.addAttribute("color", "red");
            ModelAndView modelAndView = new ModelAndView("Forgot-Password");
            return modelAndView;
        }
    }

    @PostMapping("/change-password")
    public ModelAndView changePassword(@ModelAttribute UserRequest userRequest, Model model) {

        if (userRequest.getCurrentPassword().equals(userDisplay.getPassword())) {
            if (!(userRequest.getNewPassword().equals(userRequest.getConfirmNewPassword()))) {
                model.addAttribute("message", "New password and confirm password are not the same");
                model.addAttribute("display", "block");
                model.addAttribute("color", "red");
                ModelAndView modelAndView = new ModelAndView("Change-Password");
                return modelAndView;
            } else {
                User user = userService.changePassword(userDisplay.getUsersId(), userRequest);
                model.addAttribute("message", "Change password successfully");
                model.addAttribute("display", "block");
                model.addAttribute("color", "green");
                ModelAndView modelAndView = new ModelAndView("Change-Password");
                return modelAndView;
            }
        } else {
            model.addAttribute("message", "Password is incorrect");
            model.addAttribute("display", "block");
            model.addAttribute("color", "red");
            ModelAndView modelAndView = new ModelAndView("Change-Password");
            return modelAndView;
        }
    }

    @GetMapping("/change-password")
    public ModelAndView changePassWord(@ModelAttribute UserRequest userRequest, Model model) {
        ModelAndView modelAndView = new ModelAndView("Change-Password");
        return modelAndView;
    }

    @GetMapping("/edit-profile")
    public ModelAndView edit(Model model) {
        model.addAttribute("user", userRepo.findByUsersId(userDisplay.getUsersId()));
        ModelAndView modelAndView = new ModelAndView("Edit-Profile");
        return modelAndView;
    }

    @GetMapping("/edit-address")
    public ModelAndView showE(Model model){
        model.addAttribute("user", userRepo.findByUsersId(userDisplay.getUsersId()));
        model.addAttribute("addresses", userService.getAddress(userDisplay.getUsersId()));
        ModelAndView modelAndView = new ModelAndView("Edit-Address");
        return modelAndView;
    }
}
