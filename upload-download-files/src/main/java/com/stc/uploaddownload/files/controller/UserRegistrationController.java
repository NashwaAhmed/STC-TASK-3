package com.stc.uploaddownload.files.controller;

import com.stc.uploaddownload.files.model.UserRegisterDto;
import com.stc.uploaddownload.files.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequestMapping("/registration")
public class UserRegistrationController {

    @Autowired
    private UserService userService;

    @ModelAttribute("user")
    public UserRegisterDto userRegistrationDto(){
        return new UserRegisterDto();
    }

    @PostMapping
    public String registerUserAccount(@ModelAttribute("user") UserRegisterDto registrationDto) {
        log.info("new customer registration {}", registrationDto);
        userService.save(registrationDto);
        return "registration succeed";
    }

}
