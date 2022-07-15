package populationcensus.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.SessionAttributes;
import populationcensus.repository.entity.Household;
import populationcensus.repository.entity.Person;
import populationcensus.service.dto.HouseholdDto;
import populationcensus.service.dto.PersonDto;
import populationcensus.service.dto.mapper.HouseholdMapper;
import populationcensus.service.dto.mapper.PersonMapper;
import populationcensus.service.interfaces.HouseholdService;

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
        HouseholdDto householdFromModel = (HouseholdDto)model.getAttribute("household");
        List<PersonDto> personsFromModel = (List<PersonDto>)model.getAttribute("persons");

        personsFromModel.add(obj);
        model.addAttribute("currentPerson", new PersonDto());

        if (personsFromModel.size() == householdFromModel.getNumberOfMembers()){
            Household householdResult = householdMapper.toHousehold(householdFromModel);
            List<Person> personsResult = personMapper.toPersonList(personsFromModel);
            householdResult.setPersons(personsResult);

            householdService.saveHousehold(householdResult);
            return "redirect:/main/surveyFinish";
        }
        return "redirect:/main/surveyPerson";
    }

    @PostMapping(value = "/foreignPerson")
    public String foreignPersonQuestionsPage(Model model) {
        ((PersonDto)model.getAttribute("currentPerson")).setIsForeign(true);
        return "redirect:/main/surveyForeignPerson";
    }
}
