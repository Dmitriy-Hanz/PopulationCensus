package populationcensus.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class UserBlankViewerPageController {

    @GetMapping("*/myBlank")
    public String load(){
        return "userBlankViewerPage";
    }

    @PostMapping("/main")
    public String back(){
        return "redirect:/main";
    }


}
