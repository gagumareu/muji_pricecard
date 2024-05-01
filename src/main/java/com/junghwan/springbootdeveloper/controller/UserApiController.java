package com.junghwan.springbootdeveloper.controller;

import com.junghwan.springbootdeveloper.dto.AddUserRequest;
import com.junghwan.springbootdeveloper.service.UserService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@RequiredArgsConstructor
@Controller
@Log4j2
public class UserApiController {

    private final UserService userService;

    @PostMapping("/user")
    public String signup(AddUserRequest request, RedirectAttributes redirectAttributes){

        log.info("signup.....");
        log.info(request);

        try {
            userService.signup(request);
        } catch (UserService.UseridExistException e) {
            redirectAttributes.addFlashAttribute("error", "userId");
            return "redirect:/signup";
        }

        redirectAttributes.addFlashAttribute("result", "success");

        return "redirect:/login";
    }

    @GetMapping("/logout")
    public String logout(HttpServletRequest request, HttpServletResponse response){

        new SecurityContextLogoutHandler().logout(request, response, SecurityContextHolder.getContext().getAuthentication());

        return "redirect:/login";
    }

}