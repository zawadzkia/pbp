package com.zawadzkia.pbp.controller;

import com.zawadzkia.pbp.exceptions.UserOperationException;
import com.zawadzkia.pbp.model.mt.UserMT;
import com.zawadzkia.pbp.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@RequiredArgsConstructor
@Controller
public class LoginController {

    private final UserService userService;

    @RequestMapping(value = {"/login"}, method = RequestMethod.GET)
    public String login() {
        return "login";
    }

    @RequestMapping(value = {"/", "/registration"}, method = RequestMethod.GET)
    public String registrationForm(Model model) {
        UserMT userMT = new UserMT();
        model.addAttribute("userMT", userMT);
        return "registration";
    }

    @RequestMapping(value = {"/registration"}, method = RequestMethod.POST)
    public String processRegistrationForm(@ModelAttribute UserMT userMT, Model model) {
        try {
            userService.registerUser(userMT);
        } catch (Exception e) {
            model.addAttribute("error", e.getLocalizedMessage());
            model.addAttribute("userMT", userMT);
            return "registration";
        }
        return "login";
    }

}
