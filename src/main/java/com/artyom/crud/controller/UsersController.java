package com.artyom.crud.controller;

import com.artyom.crud.dto.UserInfo;
import com.artyom.crud.dto.UserRequest;
import com.artyom.crud.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.support.RequestContextUtils;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;
import java.util.Objects;

@Controller
@RequestMapping("/users")
public class UsersController {
    private final UserService userService;

    public UsersController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping
    public String showUsers(ModelMap model, HttpServletRequest request) {
        Map<String, ?> flashMap = RequestContextUtils.getInputFlashMap(request);
        if (Objects.nonNull(flashMap)) {
            if (flashMap.containsKey("errorMessage")) {
                model.addAttribute("errorMessage", flashMap.get("errorMessage"));
            } else {
                model.addAttribute("info", flashMap.get("info"));
            }
            flashMap.forEach((k, v) -> System.out.println(k + ":" + v));
        }

        model.addAttribute("id", "");
        model.addAttribute("users", userService.findAll());
        return "users";
    }

    @GetMapping("/create")
    public String showCreateUser(ModelMap model) {
        model.addAttribute("userId", new StringBuilder());
        model.addAttribute("userRequest", new UserRequest());
        return "user-form";
    }

    @PostMapping("/create")
    public String saveUser(@ModelAttribute("userRequest") UserRequest userRequest) {
        userService.create(userRequest);
        return "redirect:/users";
    }

    @GetMapping("/edit")
    public String showEditUserById(@RequestParam("id") String id, ModelMap model) {
        UserInfo userInfo = userService.findById(Long.valueOf(id));
        model.addAttribute("user", userInfo);
        return "user-edit";
    }

    @PostMapping("/edit")
    public String updateUserById(@RequestParam("id") String id, @ModelAttribute("user") UserRequest request) {
        userService.updateById(Long.parseLong(id), request);
        return "redirect:/users";
    }

    @PostMapping("/find")
    public String getUserById(HttpServletRequest request, RedirectAttributes redirect) {
        String id = request.getParameter("userId");
        finderUserHandler(id, redirect);
        return "redirect:/users";
    }

    @PostMapping("/delete")
    public String deleteUserById(@RequestParam("id") String id) {
        userService.deleteById(Long.valueOf(id));
        return "redirect:/users";
    }

    private void finderUserHandler(String id, RedirectAttributes redirectAttributes) {
        if (id == null || id.isBlank() || id.isEmpty()) {
            redirectAttributes.addFlashAttribute("errorMessage", "Введите ID пользователя");
        }

        try {
            Long userId = Long.parseLong(id);
            UserInfo userInfo = userService.findById(userId);
            if (Objects.nonNull(userInfo)) {
                redirectAttributes.addFlashAttribute("info", userInfo);
            } else {
                redirectAttributes.addFlashAttribute("errorMessage", "Пользователь с ID " + id + " не найден.");
            }
        } catch (NumberFormatException e) {
            redirectAttributes.addFlashAttribute("errorMessage", "Введён некорректный ID");
        }
    }
}
