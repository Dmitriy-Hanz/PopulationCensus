package populationcensus.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("*/surveyFinish")
public class SurveyFinalPageController {

    @GetMapping
    public String load(){
        return "questionsFinishPage";
    }

}
