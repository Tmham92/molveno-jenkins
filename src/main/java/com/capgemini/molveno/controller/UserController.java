package com.capgemini.molveno.controller;

import com.capgemini.molveno.model.details.EnterpriseDetails;
import com.capgemini.molveno.model.formmodels.AddGuestForm;
import com.capgemini.molveno.service.IBookingService;
import com.capgemini.molveno.service.IUserService;
import com.capgemini.molveno.utility.ExportToFile;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;


@Controller
@RequestMapping("/api/users")
public class UserController {

    private final IUserService userService;
    private final IBookingService bookingService;
    private EnterpriseDetails storedEnterpriseDetails = new EnterpriseDetails();
    private AddGuestForm addGuestForm = new AddGuestForm();

    @Autowired
    public UserController(IUserService userService, IBookingService bookingService) {
        this.userService = userService;
        this.bookingService = bookingService;
    }

    @GetMapping("/employee")
    public String getAllUsers(Model model) {
        model.addAttribute("users", userService.findAll());
        return "/api/users/list";
    }

    @PostMapping("/employee/register")
    private String goToAddNewGuest() {
        return "redirect:/api/users/register";
    }

    @GetMapping("/register")
    private String getAddGuest(Model model) {
        model.addAttribute("enterpriseDetails", storedEnterpriseDetails);
        model = userService.checkIfEnterpriseDetailsAreFilledIn(model, (EnterpriseDetails) model.getAttribute("enterpriseDetails"));
        model.addAttribute("addGuestForm", new AddGuestForm());
        return "/api/users/register";
    }

    @RequestMapping(value = "/register", method = RequestMethod.POST)
    private String postAddNewGuest(@Valid @ModelAttribute("addGuestForm") AddGuestForm form, BindingResult bindingResult,
                                   Model model, RedirectAttributes redirectAttributes) {
        if (bindingResult.hasErrors()) { return "/api/users/register";
        } else {
            userService.addNewUser(form, storedEnterpriseDetails);
            model.addAttribute("enterpriseDetails" , new EnterpriseDetails());
            redirectAttributes.addFlashAttribute("emailExists", form.getEmail());
            return "redirect:/login";
        }
    }

    @RequestMapping(value = "/register/enterprise", method = RequestMethod.POST)
    public String createEnterpriseDetails(@Valid @ModelAttribute("enterpriseDetails") EnterpriseDetails enterpriseDetails,
                    BindingResult bindingResult, Model model ) {
        if (bindingResult.hasErrors()) {
            return "/api/users/register";
        }
        storedEnterpriseDetails = enterpriseDetails;
        model.addAttribute("enterpriseDetails", userService.setNewEnterpriseDetails(enterpriseDetails));
        model.addAttribute("addGuestForm", this.addGuestForm);

        return "/api/users/register";
    }

    @GetMapping("register/enterprise")
    public String redirectEnterpriseMapping(Model model) {
        model.addAttribute("addGuestForm", this.addGuestForm);
        return "/api/users/register";
    }

    @PostMapping("/employee/export")
    public String exportActiveGuests(RedirectAttributes redirectAttributes) {
        ExportToFile ETF = new ExportToFile();
        ETF.writeToFile(bookingService.findAll());
        redirectAttributes.addFlashAttribute("exportDone", true);
        return "redirect:/api/users/employee";
    }
}

