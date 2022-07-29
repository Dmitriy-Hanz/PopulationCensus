package populationcensus.controllers;

import lombok.RequiredArgsConstructor;
import org.apache.tomcat.util.bcel.Const;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;
import populationcensus.Consts;
import populationcensus.repository.entity.Household;
import populationcensus.repository.entity.Person;
import populationcensus.dto.HouseholdDto;
import populationcensus.dto.PersonDto;
import populationcensus.dto.mapper.HouseholdMapper;
import populationcensus.dto.mapper.PersonMapper;
import populationcensus.service.interfaces.HouseholdService;

import javax.servlet.http.HttpSession;
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
    @GetMapping(Consts.Url.$_MAIN_$_SURVEY_PERSON)
    public String loadPersonQuestionsPage(){
        return "personQuestionsPage";
    }
    @GetMapping(Consts.Url.$_MAIN_$_SURVEY_FOREIGN_PERSON)
    public String loadForeignPersonQuestionsPage(){
        return "foreignPersonQuestionsPage";
    }



    @PostMapping(Consts.Url.$_INTERRUPT)
    public String interruptSurvey(HttpSession httpSession, SessionStatus status){
        status.setComplete();
        httpSession.removeAttribute("household");
        httpSession.removeAttribute("currentPerson");
        httpSession.removeAttribute("persons");
        return "redirect:" + Consts.Url.$_MAIN;
    }

    @PostMapping(Consts.Url.$_HOUSEHOLD_NEXT)
    public String personQuestionsPage(@ModelAttribute(name = "household") HouseholdDto obj, Model model) {
        model.addAttribute("currentPerson", new PersonDto(false));
        model.addAttribute("persons", new LinkedList<PersonDto>());
        return "redirect:" + Consts.Url.$_MAIN_$_SURVEY_PERSON;
    }

    @PostMapping(Consts.Url.$_PERSON_NEXT)
    public String personQuestionsPage(@ModelAttribute(name = "currentPerson") PersonDto obj, Model model, HttpSession httpSession, SessionStatus status) {
        HouseholdDto householdFromModel = (HouseholdDto)model.getAttribute("household");
        List<PersonDto> personsFromModel = (List<PersonDto>)model.getAttribute("persons");

        if (obj.getIsForeign()){
            clearForeignPersonUselessValues(obj);
        } else{
            clearPersonUselessValues(obj);
        }
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
            return "redirect:" + Consts.Url.$_MAIN_$_SURVEY_FINISH;
        }

        model.addAttribute("currentPerson", new PersonDto(false));
        return "redirect:" + Consts.Url.$_MAIN_$_SURVEY_PERSON;
    }

    @PostMapping(Consts.Url.$_FOREIGN_PERSON)
    public String foreignPersonQuestionsPage(Model model) {
        ((PersonDto)model.getAttribute("currentPerson")).setIsForeign(true);
        return "redirect:" + Consts.Url.$_MAIN_$_SURVEY_FOREIGN_PERSON;
    }

    public void clearForeignPersonUselessValues(PersonDto person){
        if (person.getIsForeign()) {
            if (person.getBirthCountry() == 1) {
                person.setNameOfBirthCountry("");
            }
            if (person.getCitizenship() != 2) {
                person.setNameOfCitizenshipCountry("");
            }
        }
    }

    public void clearPersonUselessValues(PersonDto person) {
        if (person.getAge() < 15)
        {
            person.setMaritalStatus(null);
        }
        if (person.getBirthCountry() == 1)
        {
            person.setNameOfBirthCountry(null);
        }
        if (person.getCitizenship() != 2) { person.setNameOfCitizenshipCountry(null); }
        if (person.getNationality() != 6) { person.setNameOfNationality(null); }
        if (person.getNativeLanguage() != 5) { person.setNameOfNativeLanguage(null); }
        if (person.getSpeakingLanguage() != 5) { person.setNameOfSpeakingLanguage(null); }


        //LivingPlace and LivingCountry

        if (person.getLivingPlaceInfo().getDoYouLiveHereFromBirth())
        {
            person.getLivingPlaceInfo().setArrivalPeriod(null);
            person.getLivingPlaceInfo().setPreviousLivingPlace(null);
            person.getLivingPlaceInfo().setReasonForArrivalAtPlace(null);
            person.getLivingPlaceInfo().setRegionOrDistrictName(null);
            person.getLivingPlaceInfo().setCityOrPGTName(null);
            person.getLivingPlaceInfo().setIsItVillage(null);
            person.getLivingCountryInfo().setDidYouLiveInOtherCountry(null);
            person.getLivingCountryInfo().setNameOfCountryYouCameFrom(null);
            person.getLivingCountryInfo().setArrivalPeriod(null);
            person.getLivingCountryInfo().setReasonForArrivalAtRB(null);
        }
        else
        {
            switch (person.getLivingPlaceInfo().getPreviousLivingPlace()) {
                case 1 -> {
                    person.getLivingPlaceInfo().setNameOfPreviousCountry(null);
                    if (!person.getLivingCountryInfo().getDidYouLiveInOtherCountry()) {
                        person.getLivingCountryInfo().setNameOfCountryYouCameFrom(null);
                        person.getLivingCountryInfo().setArrivalPeriod(null);
                        person.getLivingCountryInfo().setReasonForArrivalAtRB(null);
                    }
                }
                case 2 -> {
                    person.getLivingPlaceInfo().setRegionOrDistrictName(null);
                    person.getLivingPlaceInfo().setCityOrPGTName(null);
                    person.getLivingPlaceInfo().setIsItVillage(null);
                    person.getLivingCountryInfo().setDidYouLiveInOtherCountry(null);
                    person.getLivingCountryInfo().setNameOfCountryYouCameFrom(null);
                    person.getLivingCountryInfo().setArrivalPeriod(null);
                    person.getLivingCountryInfo().setReasonForArrivalAtRB(null);
                }
            }
        }

        if (person.getAge() >= 15 & person.getAge() < 74)
        {
            person.getLivingCountryInfo().setReasonForLeavingBelarus(null);
        }


        //EducationInfo

        if (person.getAge() < 10)
        {
            person.getEducationInfo().setLvlOfEducation(null);
            person.getEducationInfo().setAcademicDegree(null);
            person.getEducationInfo().setCanYouReadAndWrite(null);
        }
        else
        {
            if (person.getEducationInfo().getLvlOfEducation() == 8) {
                person.getEducationInfo().setAcademicDegree(null);
            } else {
                person.getEducationInfo().setCanYouReadAndWrite(null);
            }
        }

        if (person.getAge() < 6) { person.getEducationInfo().setBasicEducationInfo(null); }

        if (!(person.getAge() >= 15 && person.getAge() <= 65)) { person.getEducationInfo().setAdditionalEducationInfo(null); }

        if (person.getAge() > 7) { person.getEducationInfo().setDoesChildAttendPreschool(null); }


        //WorkInfo

        if (person.getAge() < 15 || person.getAge() > 74)
        {
            person.setWorkInfo(null);
        }
        else {
            if (person.getWorkInfo().getDoYouHaveWork()) {
                person.getWorkInfo().setWhyDidntYouWorkTillNow(null);
                person.getWorkInfo().setDidYouLookedForAJob(null);
                person.getWorkInfo().setCanYouStarWorkingInTwoWeeks(null);
            } else {
                if (!person.getWorkInfo().getWhyDidntYouWorkTillNow()) {
                    person.getWorkInfo().setLocationOfWork(null);
                    person.getWorkInfo().setRegionOrDistrictName(null);
                    person.getWorkInfo().setCityOrPGTName(null);
                    person.getWorkInfo().setIsItVillage(null);
                    person.getWorkInfo().setNameOfCountry(null);
                    person.getWorkInfo().setDepartureFrequencyToWork(null);
                    person.getWorkInfo().setUnemploymentReason(null);
                    person.getWorkInfo().setTypeOfWorkplace(null);
                    person.getWorkInfo().setTypeOfWorkPosition(null);
                }
                person.getWorkInfo().setDidYouLookedForAJob(null);
                person.getWorkInfo().setCanYouStarWorkingInTwoWeeks(null);
            }


            switch (person.getWorkInfo().getLocationOfWork()) {
                case 1 -> {
                    person.getWorkInfo().setRegionOrDistrictName(null);
                    person.getWorkInfo().setCityOrPGTName(null);
                    person.getWorkInfo().setIsItVillage(null);
                    person.getWorkInfo().setNameOfCountry(null);
                    person.getWorkInfo().setDepartureFrequencyToWork(null);
                    person.getWorkInfo().setUnemploymentReason(null);
                }
                case 2 -> {
                    person.getWorkInfo().setNameOfCountry(null);
                    person.getWorkInfo().setDepartureFrequencyToWork(null);
                }
                case 3 -> {
                    person.getWorkInfo().setRegionOrDistrictName(null);
                    person.getWorkInfo().setCityOrPGTName(null);
                    person.getWorkInfo().setIsItVillage(null);
                }
            }
            if (person.getWorkInfo().getDidYouLookedForAJob() == person.getWorkInfo().getCanYouStarWorkingInTwoWeeks()) {
                person.getWorkInfo().setWhyYouCantWorkOrStoppedSearch(null);
            }
        }


        //ChildrenInfo

        if (person.getGender() == 1)
        {
            person.getChildrenInfo().setHowManyChildrenDoYouHave(null);
            person.getChildrenInfo().setNoChildren(null);
            person.getChildrenInfo().setChildrenPlans(null);
            person.getChildrenInfo().setHowManyChildrenDoYouWant(null);
            person.getChildrenInfo().setDontKnowHowMany(null);
        }
        else
        {
            if (person.getAge() < 15)
            {
                person.getChildrenInfo().setHowManyChildrenDoYouHave(null);
                person.getChildrenInfo().setNoChildren(null);
            }
            else
            {
                if (person.getChildrenInfo().getNoChildren()) { person.getChildrenInfo().setHowManyChildrenDoYouWant(null); }
            }
            if (!(person.getAge() >= 18 && person.getAge() <= 49))
            {
                person.getChildrenInfo().setChildrenPlans(null);
                person.getChildrenInfo().setHowManyChildrenDoYouWant(null);
                person.getChildrenInfo().setDontKnowHowMany(null);
            }
            else
            {
                if (person.getChildrenInfo().getChildrenPlans() != 1)
                {
                    person.getChildrenInfo().setHowManyChildrenDoYouWant(null);
                    person.getChildrenInfo().setDontKnowHowMany(null);
                }
                else
                {
                    if(person.getChildrenInfo().getDontKnowHowMany()) { person.getChildrenInfo().setHowManyChildrenDoYouWant(null); }
                }
            }
        }

    }
}
