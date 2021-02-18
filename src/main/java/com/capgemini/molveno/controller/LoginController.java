package com.capgemini.molveno.controller;

import com.capgemini.molveno.model.PasswordToken;
import com.capgemini.molveno.model.formmodels.AddGuestForm;
import com.capgemini.molveno.model.formmodels.RecoverPasswordForm;
import com.capgemini.molveno.model.user.User;
import com.capgemini.molveno.service.LoginService;
import com.capgemini.molveno.service.PasswordTokenService;
import com.capgemini.molveno.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Controller
public class LoginController {


    private final LoginService loginService;
    private final UserService userService;
    private final PasswordTokenService pTokenService;

    @Autowired
    public LoginController(LoginService loginService, UserService userService, PasswordTokenService pTokenService) {
        this.loginService = loginService;
        this.userService = userService;
        this.pTokenService = pTokenService;
    }


    @GetMapping("/login")
    public String login(@ModelAttribute ("emailExists") String email, Model model) {
        model.addAttribute("emailExists", email);
        model.addAttribute("recoverPasswordForm", new RecoverPasswordForm());
        return "login";
    }

    @PostMapping("/login/register")
    public String register(@ModelAttribute ("emailExists") String email, Model model) {
        model.addAttribute("emailExists", email);
        return "api/users/register";
    }

    @PostMapping("/sendMail")
    public String sendMail(@Valid @ModelAttribute("recoverPasswordForm") RecoverPasswordForm form,
                         BindingResult bindingResult,
                         Model model){
        if (bindingResult.hasErrors()) {
            System.out.println("ERROR!" + form.getEmail());
        } else if (userService.checkForEmailDuplicate(form.getEmail())){
            User user = userService.getUserFromEmail(form.getEmail());
            PasswordToken pToken = new PasswordToken(user);
            pTokenService.save(pToken);
            loginService.sendEmail(form.getEmail(), pToken);
        } else {
            System.out.println("email bestaat niet");
        }
        System.out.println("Mail gestuurd naar " +  form.getEmail());
        return "redirect:login";
    }
}
