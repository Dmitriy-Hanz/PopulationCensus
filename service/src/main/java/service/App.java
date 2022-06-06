package service;

import repository.dao.DaoFactory;
import repository.dao.idaos.*;
import repository.entity.*;

public class App
{
    public static void main( String[] args )
    {
        HouseholdDaoImpl householdDao = DaoFactory.getInstance().createHouseholdDao();
        AccommodationsInfoDaoImpl accommodationsInfoDao = DaoFactory.getInstance().createAccommodationsInfoDao();
        FullAddressInfoDaoImpl fullAddressInfoDao = DaoFactory.getInstance().createFullAddressInfoDao();

        PersonDaoImpl personDao = DaoFactory.getInstance().createPersonDao();
        LivingCountryInfoDaoImpl livingCountryInfoDao = DaoFactory.getInstance().createLivingCountryInfoDao();
        LivingPlaceInfoDaoImpl livingPlaceInfoDao = DaoFactory.getInstance().createLivingPlaceInfoDao();
        EducationInfoDaoImpl educationInfoDao = DaoFactory.getInstance().createEducationInfoDao();
        WorkInfoDaoImpl workInfoDao = DaoFactory.getInstance().createWorkInfoDao();
        ChildrenInfoDaoImpl childrenInfoDao = DaoFactory.getInstance().createChildrenInfoDao();


        AccommodationsInfo accommodationsInfo = AccommodationsInfo.builder()
                .areaOfFlat(228)
                .build();
        FullAddressInfo fullAddressInfo = FullAddressInfo.builder()
                .city("Купянск")
                .build();
        Household household = Household.builder()
                .numberOfMembers(1)
                .fullAddressInfo(fullAddressInfo)
                .accommodationsInfo(accommodationsInfo)
                .build();

        LivingCountryInfo livingCountryInfo = LivingCountryInfo.builder()
                .didYouLiveInOtherCountry(false)
                .build();
        LivingPlaceInfo livingPlaceInfo = LivingPlaceInfo.builder()
                .nameOfPreviousCountry("abc")
                .build();
        EducationInfo educationInfo = EducationInfo.builder()
                .lvlOfEducation(6)
                .build();
        WorkInfo workInfo = WorkInfo.builder()
                .typeOfWorkplace(3)
                .build();
        ChildrenInfo childrenInfo = ChildrenInfo.builder()
                .noChildren(true)
                .build();

        Person person = Person.builder()
                .name("Коля")
                .livingCountryInfo(livingCountryInfo)
                .livingPlaceInfo(livingPlaceInfo)
                .educationInfo(educationInfo)
                .workInfo(workInfo)
                .childrenInfo(childrenInfo)
                .build();
    }
}
