package com.megamaker.admin.controller;

import com.megamaker.admin.domain.Role;
import com.megamaker.admin.dto.user.ResponseListUser;
import com.megamaker.admin.dto.user.ResponseUser;
import com.megamaker.admin.dto.user.UserSearchCond;
import com.megamaker.admin.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

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
//
//    @PutMapping
//    public String update(@ModelAttribute RequestProblemUpdate requestProblemUpdate, Model model,
//                         HttpServletRequest request) {
//        ResponseProblem updatedProblem = problemService.update(requestProblemUpdate);
//        model.addAttribute("problem", updatedProblem);
//        return "redirect:problem/" + updatedProblem.getId() + "?" + request.getQueryString();
//    }
//
//    @DeleteMapping
//    public String remove(@RequestParam Long id, HttpServletRequest request) {
//        problemService.remove(id);
//        return "redirect:problem/list?" + request.getQueryString();
//    }
}
