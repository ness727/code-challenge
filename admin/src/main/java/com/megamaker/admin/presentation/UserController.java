package com.megamaker.admin.presentation;

import com.megamaker.admin.domain.user.vo.Role;
import com.megamaker.admin.domain.user.dto.RequestUserUpdate;
import com.megamaker.admin.domain.user.dto.ResponseListUser;
import com.megamaker.admin.domain.user.dto.ResponseUser;
import com.megamaker.admin.domain.user.dto.UserSearchCond;
import com.megamaker.admin.application.UserService;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import static org.springframework.data.domain.Sort.Direction.ASC;

@RequiredArgsConstructor
@RequestMapping("/user")
@Controller
public class UserController {
    private final UserService userService;

    @GetMapping("/list")
    public String list(@ModelAttribute UserSearchCond userSearchCond,
                       @PageableDefault(sort = "id", direction = ASC) Pageable pageable,
                       Model model) {
        Page<ResponseListUser> foundUserList = userService.findAll(userSearchCond, pageable);
        model.addAttribute("userPage", foundUserList);
        return "user/list";
    }

    @GetMapping("/{id}")
    public String editForm(@PathVariable Long id, Model model) {
        ResponseUser foundUser = userService.findById(id);
        model.addAttribute("user", foundUser);
        model.addAttribute("roles", Role.values());
        return "user/edit-form";
    }

    @PutMapping
    public String update(@ModelAttribute RequestUserUpdate requestUserUpdate, Model model,
                         HttpServletRequest request) {
        ResponseUser updatedUser = userService.update(requestUserUpdate);
        model.addAttribute("user", updatedUser);
        return "redirect:user/" + updatedUser.getId() + "?" + request.getQueryString();
    }

    @DeleteMapping
    public String remove(@RequestParam Long id, HttpServletRequest request) {
        userService.remove(id);
        return "redirect:user/list?" + request.getQueryString();
    }
}
