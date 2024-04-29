package com.artyom.crud.controller;

import com.artyom.crud.dto.UserInfo;
import com.artyom.crud.dto.UserRequest;
import com.artyom.crud.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/users")
public class UsersController {
    private final UserService userService;

    public UsersController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/create")
    public String showCreateUser(ModelMap model) {
        model.addAttribute("userRequest", new UserRequest());
        return "user-form";
    }

    @PostMapping("/create")
    public String createUser(@ModelAttribute("userRequest") UserRequest userRequest) {
        userService.create(userRequest);
        return "redirect:/users";
    }

    @GetMapping("/edit")
    public String showEditUser(@RequestParam("id") String id, ModelMap model) {
        UserInfo userInfo = userService.findById(Long.valueOf(id));
        model.addAttribute("user", userInfo);
        return "user-info";
    }

    @PostMapping("/edit")
    public String editUser(@RequestParam("id") String id, @ModelAttribute("user") UserRequest userRequest) {
        userService.updateById(Long.parseLong(id), userRequest);
        return "redirect:/edit";
    }

    @GetMapping
    public String showUsers(ModelMap model) {
        model.addAttribute("users", userService.findAll());
        return "users";
    }

}
