package service.services;

import service.services.impl.*;
import service.services.interfaces.*;

public class ServiceProvider {
    private final AccommodationsInfoService accommodationsInfoService = new AccommodationsInfoServiceImpl();
    private final ChildrenInfoService childrenInfoService = new ChildrenInfoServiceImpl();
    private final EducationInfoService educationInfoService = new EducationInfoServiceImpl();
    private final FullAddressInfoService fullAddressInfoService = new FullAddressInfoServiceImpl();
    private final HouseholdService householdService = new HouseholdServiceImpl();
    private final LivingCountryInfoService livingCountryInfoService = new LivingCountryInfoServiceImpl();
    private final LivingPlaceInfoService livingPlaceInfoService = new LivingPlaceInfoServiceImpl();
    private final PersonService personService = new PersonServiceImpl();
    private final WorkInfoService workInfoService = new WorkInfoServiceImpl();


    private static class SingletonHolder {
        private final static ServiceProvider INSTANCE = new ServiceProvider();
    }

    public static ServiceProvider getInstance() {
        return SingletonHolder.INSTANCE;
    }

    public AccommodationsInfoService getAccommodationsInfoService() {
        return accommodationsInfoService;
    }

    public ChildrenInfoService getChildrenInfoService() {
        return childrenInfoService;
    }

    public EducationInfoService getEducationInfoService() {
        return educationInfoService;
    }

    public FullAddressInfoService getFullAddressInfoService() {
        return fullAddressInfoService;
    }

    public HouseholdService getHouseholdService() {
        return householdService;
    }

    public LivingCountryInfoService getLivingCountryInfoService() {
        return livingCountryInfoService;
    }

    public LivingPlaceInfoService getLivingPlaceInfoService() {
        return livingPlaceInfoService;
    }

    public PersonService getPersonService() {
        return personService;
    }

    public WorkInfoService getWorkInfoService() {
        return workInfoService;
    }
}
