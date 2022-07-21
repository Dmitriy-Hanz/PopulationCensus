package populationcensus.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import populationcensus.repository.entity.Person;
import populationcensus.service.interfaces.PersonService;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Controller
@RequiredArgsConstructor
public class CensusResultsPageController {

    private final PersonService personService;

    @GetMapping("/adminMain/censusResults")
    public String load(Model model) {
        List<Person> allDatabase = personService.findAll();

        boolean hasPersons = !allDatabase.isEmpty();
        model.addAttribute("hasPersons", hasPersons);
        if (hasPersons) {
            model.addAttribute("populationCountAndDensity", populationCountAndDensity(allDatabase));
            model.addAttribute("populationCountByAgeGroups", populationCountByAgeGroups(allDatabase));
        }
        return "censusResultsPage";
    }

    private Number[] populationCountAndDensity(List<Person> persons) {
        Number[] results = new Number[5];
        results[0] = persons.size();
        results[1] = (int) persons.stream()
                .map(Person::getHouseholdField)
                .filter(household -> !household.getFullAddressInfo().getCity().equals(""))
                .count();
        results[2] = (int) persons.stream()
                .map(Person::getHouseholdField)
                .filter(household -> !household.getFullAddressInfo().getVillageName().equals(""))
                .count();
        results[3] = (100 * (double) (int) results[1]) / (double) (int) results[0];
        results[4] = (100 * (double) (int) results[2]) / (double) (int) results[0];
        return results;
    }

    private Number[][] populationCountByAgeGroups(List<Person> persons) {
        Number[][] results = new Number[3][5];

        List<Person> cityPersons = persons.stream()
                .filter(person -> !person.getHouseholdField().getFullAddressInfo().getCity().equals(""))
                .toList();
        List<Person> villagePersons = persons.stream()
                .filter(person -> !person.getHouseholdField().getFullAddressInfo().getVillageName().equals(""))
                .toList();

        results[0][0] = persons.size();
        results[0][1] = (int) persons.stream()
                .filter(p -> p.getAge() < 16)
                .count();
        results[0][2] = (int) persons.stream()
                .filter(p -> (p.getGender() == 1 && p.getAge() < 63) || (p.getGender() == 2 && p.getAge() < 58))
                .count();
        results[0][3] = (int) persons.stream()
                .filter(p -> (p.getGender() == 1 && p.getAge() > 62) || (p.getGender() == 2 && p.getAge() > 57))
                .count();
        results[0][4] = (double) persons.stream()
                .map(Person::getAge)
                .reduce(Integer::sum)
                .orElse(0) / (persons.size() == 0 ? 1 : persons.size());

        results[1][0] = cityPersons.size();
        results[1][1] = (int) cityPersons.stream()
                .filter(p -> p.getAge() < 16)
                .count();
        results[1][2] = (int) cityPersons.stream()
                .filter(p -> (p.getGender() == 1 && p.getAge() < 63) || (p.getGender() == 2 && p.getAge() < 58))
                .count();
        results[1][3] = (int) cityPersons.stream()
                .filter(p -> (p.getGender() == 1 && p.getAge() > 62) || (p.getGender() == 2 && p.getAge() > 57))
                .count();
        results[1][4] = (double) cityPersons.stream()
                .map(Person::getAge)
                .reduce(Integer::sum)
                .orElse(0) / (cityPersons.size() == 0 ? 1 : cityPersons.size());

        results[2][0] = villagePersons.size();
        results[2][1] = (int) villagePersons.stream()
                .filter(person -> person.getAge() < 16)
                .count();
        results[2][2] = (int) villagePersons.stream()
                .filter(p -> (p.getGender() == 1 && p.getAge() < 63) || (p.getGender() == 2 && p.getAge() < 58))
                .count();
        results[2][3] = (int) villagePersons.stream()
                .filter(p -> (p.getGender() == 1 && p.getAge() > 62) || (p.getGender() == 2 && p.getAge() > 57))
                .count();
        results[2][4] = (double) villagePersons.stream()
                .map(Person::getAge)
                .reduce(Integer::sum)
                .orElse(0) / (villagePersons.size() == 0 ? 1 : villagePersons.size());

        return results;
    }


}
