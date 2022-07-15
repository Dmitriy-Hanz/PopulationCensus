package populationcensus.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class AdminMainPageController {

    @GetMapping("/adminMain")
    public String load(){
        return "adminMainPage";
    }

    @PostMapping(value = "/navAdminMain")
    public String userBlankViewerPage(){
        return "redirect:adminMain";
    }

    @PostMapping("/logout")
    public String authorization(){
        return "redirect:main";
    }
}
