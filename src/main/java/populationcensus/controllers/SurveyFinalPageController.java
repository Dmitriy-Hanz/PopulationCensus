package populationcensus.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import populationcensus.Consts;

@Controller
public class SurveyFinalPageController {
    @GetMapping("*/surveyFinish")
    public String load(){
        return "questionsFinishPage";
    }

    @PostMapping(Consts.Url.$_FINISH)
    public String finish(){
        return "redirect:" + Consts.Url.$_MAIN;
    }
}
