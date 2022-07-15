package populationcensus.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
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
    public String userBlankViewerPage(){
        return "redirect:main/myBlank";
    }

    @PostMapping("/authorization")
    public String authorization(){
        return "redirect:adminMain";
    }
}
