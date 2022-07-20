package populationcensus.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;

@Controller
@SessionAttributes("passportID")
public class MainPageController {

    @GetMapping("/main")
    public String load(){
        return "mainPage";
    }

    @PostMapping(value = "/surveyHousehold")
    public String householdQuestionsPage(){
        return "redirect:main/surveyHousehold";
    }

    @PostMapping(value = "/myBlank")
    public String userBlankViewerPage(@RequestParam(name = "passportID") String passportID, Model model){
        model.addAttribute("passportID", passportID);
        return "redirect:main/myBlank";
    }

    @PostMapping("/authorization")
    public String authorization(){
        return "redirect:adminMain";
    }
}
