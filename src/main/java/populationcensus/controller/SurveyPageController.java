package populationcensus.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.SessionAttributes;
import populationcensus.service.dto.HouseholdDto;
import populationcensus.service.dto.PersonDto;

import java.util.LinkedList;
import java.util.List;

@Controller
@SessionAttributes(names = {"household","currentPerson","persons"})
public class SurveyPageController {

    @ModelAttribute(name = "household")
    public HouseholdDto householdMA() {
        return new HouseholdDto();
    }



    @GetMapping("*/surveyHousehold")
    public String loadHouseholdQuestionsPage(){
        return "householdQuestionsPage";
    }
    @GetMapping("/main/surveyPerson")
    public String loadPersonQuestionsPage(Model model){
        return "personQuestionsPage";
    }
    @GetMapping("/main/surveyForeignPerson")
    public String loadForeignPersonQuestionsPage(Model model){
        return "foreignPersonQuestionsPage";
    }



    @PostMapping("/interrupt")
    public String interruptSurvey(){
        return "redirect:/main";
    }

    @PostMapping(value = "/householdNext")
    public String personQuestionsPage(@ModelAttribute(name = "household") HouseholdDto obj, Model model) {
        model.addAttribute("currentPerson", new PersonDto());
        model.addAttribute("persons", new LinkedList<PersonDto>());
        return "redirect:/main/surveyPerson";
    }

    @PostMapping(value = "/personNext")
    public String personQuestionsPage(@ModelAttribute(name = "currentPerson") PersonDto obj, Model model) {
//        ((List<PersonDto>)model.getAttribute("persons")).add(obj);
        HouseholdDto tempHousehold = (HouseholdDto)model.getAttribute("household");
        List<PersonDto> tempPersons = (List<PersonDto>)model.getAttribute("persons");

        tempPersons.add(obj);

        if (tempPersons.size() == tempHousehold.getNumberOfMembers()){
            return "redirect:/main/surveyFinish";
        }
        return "redirect:/main/surveyPerson";
    }

    @PostMapping(value = "/foreignPerson")
    public String foreignPersonQuestionsPage() {
        return "redirect:/main/surveyForeignPerson";
    }
}
