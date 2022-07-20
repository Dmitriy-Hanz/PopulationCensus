package populationcensus.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;
import populationcensus.repository.entity.Household;
import populationcensus.repository.entity.Person;
import populationcensus.dto.HouseholdDto;
import populationcensus.dto.PersonDto;
import populationcensus.dto.mapper.HouseholdMapper;
import populationcensus.dto.mapper.PersonMapper;
import populationcensus.service.interfaces.HouseholdService;

import javax.servlet.http.HttpSession;
import java.time.LocalDate;
import java.util.LinkedList;
import java.util.List;

@Controller
@SessionAttributes(names = {"household","currentPerson","persons"})
@RequiredArgsConstructor
public class SurveyPageController {

    private final HouseholdMapper householdMapper;
    private final PersonMapper personMapper;
    private final HouseholdService householdService;

    @ModelAttribute(name = "household")
    public HouseholdDto householdMA() {
        return new HouseholdDto();
    }



    @GetMapping("*/surveyHousehold")
    public String loadHouseholdQuestionsPage(Model model){
        return "householdQuestionsPage";
    }
    @GetMapping("/main/surveyPerson")
    public String loadPersonQuestionsPage(){
        return "personQuestionsPage";
    }
    @GetMapping("/main/surveyForeignPerson")
    public String loadForeignPersonQuestionsPage(){
        return "foreignPersonQuestionsPage";
    }



    @PostMapping("/interrupt")
    public String interruptSurvey(HttpSession httpSession, SessionStatus status){
        status.setComplete();
        httpSession.removeAttribute("household");
        httpSession.removeAttribute("currentPerson");
        httpSession.removeAttribute("persons");
        return "redirect:/main";
    }

    @PostMapping("/householdNext")
    public String personQuestionsPage(@ModelAttribute(name = "household") HouseholdDto obj, Model model) {
        model.addAttribute("currentPerson", new PersonDto(false));
        model.addAttribute("persons", new LinkedList<PersonDto>());
        return "redirect:/main/surveyPerson";
    }

    @PostMapping("/personNext")
    public String personQuestionsPage(@ModelAttribute(name = "currentPerson") PersonDto obj, Model model, HttpSession httpSession, SessionStatus status) {
        HouseholdDto householdFromModel = (HouseholdDto)model.getAttribute("household");
        List<PersonDto> personsFromModel = (List<PersonDto>)model.getAttribute("persons");

        personsFromModel.add(obj);
        if (personsFromModel.size() == householdFromModel.getNumberOfMembers()){
            Household householdResult = householdMapper.toHousehold(householdFromModel);
            List<Person> personsResult = personMapper.toPersonList(personsFromModel);
            householdResult.setPersons(personsResult);

            householdService.saveHousehold(householdResult);
            status.setComplete();
            httpSession.removeAttribute("household");
            httpSession.removeAttribute("currentPerson");
            httpSession.removeAttribute("persons");
            return "redirect:/main/surveyFinish";
        }

        model.addAttribute("currentPerson", new PersonDto());
        return "redirect:/main/surveyPerson";
    }

    @PostMapping("/foreignPerson")
    public String foreignPersonQuestionsPage(Model model) {
        ((PersonDto)model.getAttribute("currentPerson")).setIsForeign(true);
        return "redirect:/main/surveyForeignPerson";
    }
}
