package populationcensus.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class SurveyFinalPageController {
    @GetMapping("*/surveyFinish")
    public String load(){
        return "questionsFinishPage";
    }

    @PostMapping("/finish")
    public String finish(){
        return "redirect:/main";
    }
}
