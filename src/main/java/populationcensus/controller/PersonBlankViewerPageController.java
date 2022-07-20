package populationcensus.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;
import populationcensus.dto.mapper.HouseholdMapper;
import populationcensus.dto.mapper.PersonMapper;
import populationcensus.repository.entity.Household;
import populationcensus.repository.entity.Person;
import populationcensus.service.interfaces.PersonService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@Controller
@RequiredArgsConstructor
@SessionAttributes({"passportID","personForView","householdForView"})
public class PersonBlankViewerPageController {

    private final PersonService personService;
    private final PersonMapper personMapper;
    private final HouseholdMapper householdMapper;

    @GetMapping("main/myBlank")
    public String load(@ModelAttribute(name = "passportID") String passportID, Model model){
        Person person = personService.findPersonByPassportID(passportID);
        if (person == null){
            return "redirect:/main/myBlankFail";
        }
        model.addAttribute("personForView", personMapper.toPersonDto(person));
        model.addAttribute("householdForView",householdMapper.toHouseholdDto(person.getHouseholdField()));
        return "personBlankViewerPage";
    }
    @GetMapping("main/myBlankFail")
    public String loadFail(){
        return "personBlankViewerFailPage";
    }

    @GetMapping("*/myBlank/household")
    public String load(){
        return "householdBlankViewerPage";
    }

    @PostMapping("/backToPage")
    public String back(HttpSession httpSession, SessionStatus status, HttpServletRequest req){
        status.setComplete();
        httpSession.removeAttribute("passportID");
        httpSession.removeAttribute("personForView");
        httpSession.removeAttribute("householdForView");
        return "redirect:" + previousPage(req);
    }
    @PostMapping("/showHousehold")
    public String showHousehold(){
        return "redirect:/main/myBlank/household";
    }
    @PostMapping("/showPerson")
    public String showPerson(){
        return "redirect:/main/myBlank";
    }

    private String previousPage(HttpServletRequest req) {
        return req.getHeader("referer")
                .replaceAll("/myBlankFail", "")
                .replaceAll("/myBlank/household", "")
                .replaceAll("/myBlank", "");
    }
}
