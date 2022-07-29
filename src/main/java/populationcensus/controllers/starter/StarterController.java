package populationcensus.controllers.starter;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import populationcensus.Consts;

@Controller
@RequestMapping("/")
public class StarterController {

    @GetMapping
    public String startingPage(){
        return "redirect:" + Consts.Url.$_MAIN;
    }

}
