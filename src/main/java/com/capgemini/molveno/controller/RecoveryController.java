package com.capgemini.molveno.controller;

import com.capgemini.molveno.model.PasswordToken;
import com.capgemini.molveno.model.formmodels.NewPasswordForm;
import com.capgemini.molveno.model.user.User;
import com.capgemini.molveno.service.IPasswordTokenService;
import com.capgemini.molveno.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.validation.Valid;

@Controller
public class RecoveryController {

    private IUserService userService;
    private IPasswordTokenService passwordTokenService;
    private User user;

    @Autowired
    public RecoveryController(IUserService userService, IPasswordTokenService passwordTokenService){
        this.userService = userService;
        this.passwordTokenService = passwordTokenService;
    }

    @GetMapping("/api/users/changepassword")
    public String changePassword(@RequestParam("token") String token, Model model){
        model.addAttribute("newPasswordForm", new NewPasswordForm());
        PasswordToken pToken = passwordTokenService.getPToken(token);
        //check if token valid
        if (pToken == null){
            System.out.println("invalid token");
            return "index";
        }
        //add user to instance
        System.out.println("valid token");
        User user = userService.getUserFromId(pToken.getUser().getId());
        System.out.println(user.getEmail());
        this.user = user;
        return "/api/users/changepassword";
    }

    @PostMapping("/api/users/savenewpassword")
    public String saveChanges(@Valid @ModelAttribute("newPasswordForm") NewPasswordForm form,
                              BindingResult bindingResult,
                              Model model){
        System.out.println(form.getNewPassword() /*+ user.getId()*/);
        String encoded = userService.encodePassword(form.getNewPassword());
        user.setPassword(encoded);
        userService.save(user);
        return "index";
    }
}
