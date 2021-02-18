package com.capgemini.molveno.controller;

import com.capgemini.molveno.model.formmodels.BookRoomsFilter;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;
import java.time.LocalDate;

@Controller
public class HomeController {

    @GetMapping({ "/", "", "/api"})
    public String homeInit(Model model) {
        if (model.containsAttribute("filter"))
            return "index";
        BookRoomsFilter form = new BookRoomsFilter();
        form.setArrivalDate(LocalDate.now());
        form.setDepartureDate(LocalDate.now().plusDays(1));
        form.setGuests(2);
        model.addAttribute("filter", form);
        return "index";
    }

    @PostMapping("/filter")
    private String filterRooms(@Valid @ModelAttribute BookRoomsFilter filter, BindingResult bindingResult, RedirectAttributes redirectAttributes) {
        redirectAttributes.addFlashAttribute("filter", filter);
        if (bindingResult.hasErrors()) {
            return "redirect:";
        }
        return "redirect:/api/rooms/list";
    }
}
