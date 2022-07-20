package populationcensus.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import populationcensus.repository.entity.security.User;
import populationcensus.service.interfaces.security.UserService;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class UsersListPageController {

    private final UserService userService;

    @GetMapping("/adminMain/usersList")
    public String load(Model model){
        List<User> users = userService.findOnlyCustom();
        model.addAttribute("users", users);
        return "usersListPage";
    }

    @PostMapping("/createUser")
    public String createUser(){
        return "redirect:/adminMain/usersList/createUser";
    }
}
