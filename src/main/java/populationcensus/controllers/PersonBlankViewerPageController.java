package populationcensus.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.support.SessionStatus;
import populationcensus.Consts;
import populationcensus.dto.mapper.HouseholdMapper;
import populationcensus.dto.mapper.PersonMapper;
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

    @GetMapping(Consts.Url.MAIN_$_MY_BLANK)
    public String load(@ModelAttribute(name = "passportID") String passportID, Model model){
        Person person = personService.findPersonByPassportID(passportID);
        if (person == null){
            return "redirect:" + Consts.Url.$_MAIN_$_MY_BLANK_FAIL;
        }
        model.addAttribute("personForView", personMapper.toPersonDto(person));
        model.addAttribute("householdForView",householdMapper.toHouseholdDto(person.getHouseholdField()));
        return "personBlankViewerPage";
    }
    @GetMapping(Consts.Url.MAIN_$_MY_BLANK_FAIL)
    public String loadFail(){
        return "personBlankViewerFailPage";
    }
    @GetMapping("/adminMain/personsList/blank/{id}")
    public String loadFromPersonsList(Model model, @PathVariable String id){
        Person person = personService.findPerson(Long.parseLong(id));
        model.addAttribute("personForView", personMapper.toPersonDto(person));
        model.addAttribute("householdForView",householdMapper.toHouseholdDto(person.getHouseholdField()));
        return "personBlankViewerPage";
    }

    @GetMapping(Consts.Url.$_MAIN_$_MY_BLANK_$_HOUSEHOLD)
    public String loadFromMain(){
        return "householdBlankViewerPage";
    }
    @GetMapping("/adminMain/personsList/blank/*/household")
    public String loadFromPersonsList(){
        return "householdBlankViewerPage";
    }

    @PostMapping(Consts.Url.$_SHOW_HOUSEHOLD)
    public String showHousehold(HttpServletRequest req){
        return "redirect:" + req.getHeader("referer") + "/household";
    }
    @PostMapping(Consts.Url.$_SHOW_PERSON)
    public String showPerson(HttpServletRequest req){
        return "redirect:" + req.getHeader("referer").replaceAll("/household","");
    }



    @PostMapping(Consts.Url.$_BACK_TO_PAGE)
    public String back(HttpSession httpSession, SessionStatus status, HttpServletRequest req){
        status.setComplete();
        httpSession.removeAttribute("passportID");
        httpSession.removeAttribute("personForView");
        httpSession.removeAttribute("householdForView");
        return "redirect:" + previousPage(req);
    }

    private String previousPage(HttpServletRequest req) {
        return req.getHeader("referer")
                .replaceAll("/myBlankFail", "")
                .replaceAll("/myBlank/household", "")
                .replaceAll("/blank/\\d/household", "")
                .replaceAll("[/]\\w*[/]\\d", "")
                .replaceAll("/myBlank", "");
    }
}
