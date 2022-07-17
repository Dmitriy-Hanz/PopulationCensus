function getValueByName(name){
    return document.getElementsByName(name)[0].value;
}
function isChecked(name){
    return document.getElementsByName(name)[0].checked;
}
function validationFail(message){
    alert(message);
    return false;
}
function isEmptyString(name){
    return getValueByName(name) === "";
}
function isNotIntegerNumber(name) {
    return Number.isInteger(+getValueByName(name)) === false;
}



function validateHousehold() {

    if (isEmptyString("fullAddressInfo.region")) { return validationFail("Не указана городская область."); }
    if (isEmptyString("fullAddressInfo.regionDistrict")) { return validationFail("Не указана район области.") }

    if (!isEmptyString("fullAddressInfo.city") && (!isEmptyString("fullAddressInfo.villageName") || !isEmptyString("fullAddressInfo.villageCouncil")))
    {
        return validationFail("Город/район города и сельсовет/сельский НП не могут быть указаны одновременно.")
    }
    else
    {
        if (!isEmptyString("fullAddressInfo.cityDistrict") && (!isEmptyString("fullAddressInfo.villageName") || !isEmptyString("fullAddressInfo.villageCouncil")))
        {
            return validationFail("город/район города и сельсовет/сельский НП не могут быть указаны одновременно.");
        }
    }

    if (isEmptyString("fullAddressInfo.city") && isEmptyString("fullAddressInfo.villageName") && isEmptyString("fullAddressInfo.villageCouncil"))
    {
        return validationFail("Не указано место проживания (город/сельсовет/сельский НП).");
    }
    else
    {
        if (!isEmptyString("fullAddressInfo.villageCouncil") && isEmptyString("fullAddressInfo.villageName"))
            return validationFail("Не указан сельский населенный пункт.");
    }

    if (isEmptyString("fullAddressInfo.streetAvenueOther")) { return validationFail("Не указана улица, проспект, ... ."); }

    if (isEmptyString("fullAddressInfo.houseNumber") && isEmptyString("fullAddressInfo.ownerFIO")) { return validationFail("Не указан номер дома или собственник."); }
    if (!isEmptyString("fullAddressInfo.houseNumber") && !isEmptyString("fullAddressInfo.ownerFIO")) { return validationFail("Номер дома и собственник не могут быть указаны одновременно."); }

    if (isEmptyString("fullAddressInfo.flatOrRoomNumber")) { return validationFail("Не указан номер квартиры/комнаты"); }



    if (isEmptyString("numberOfMembers")) { return validationFail("Не указано количество членов домохозяйства."); }
    else
    { if (isNotIntegerNumber("numberOfMembers")) { return validationFail("Количество членов домохозяйства должно быть целой цифрой(целым числом)."); } }

    if (isEmptyString("roomsCount") && isEmptyString("partOfRoom")) { return validationFail("Не указано кол-во комнат или часть комнаты, занимаемых домохозяйством."); }
    else
    { if (!isEmptyString("roomsCount") && isNotIntegerNumber("roomsCount")) return validationFail("количество комнат должно быть цифрой(числом)."); }

    if (getValueByName("accommodationsInfo.ownerOfApartment") == 0) { return validationFail("Не указана информация о собственнике помещения для проживания."); }
    if (getValueByName("accommodationsInfo.apartmentType") == 0) { return validationFail("Не указан тип помещения, используемого для проживания."); }

    if (getValueByName("accommodationsInfo.apartmentType") < 3)
    {
        if (isEmptyString("accommodationsInfo.areaOfFlat")) { return validationFail("Не указана площадь дома/квартиры."); }
        else 
        { if (isNotIntegerNumber("accommodationsInfo.areaOfFlat")) return validationFail("Площадь дома/квартиры должна быть целым числом(целой цифрой)."); }

        if (getValueByName("accommodationsInfo.waterPipes") == 0) { return validationFail("Не указана информация о водопроводе."); }
        if (getValueByName("accommodationsInfo.canalization") == 0) { return validationFail("Не указана информация о канализации."); }
        if (getValueByName("accommodationsInfo.hotWaterSupply") == 0) { return validationFail("Не указана информация о горячем водоснабжении."); }
        if (getValueByName("accommodationsInfo.heating") == 0) { return validationFail("Не указана информация об отоплении."); }
        if (getValueByName("accommodationsInfo.cookingEquipment") == 0) { return validationFail("Не указана информация о кухонном оборудовании."); }
    }
    else{
        if (!isEmptyString("accommodationsInfo.areaOfFlat")) { return validationFail("Поле «Площадь дома/квартиры» должно быть пустым."); }
        if (getValueByName("accommodationsInfo.waterPipes") != 0) { return validationFail("Поле «Водопровод» должно быть пустым."); }
        if (getValueByName("accommodationsInfo.canalization") != 0) { return validationFail("Поле «Канализация» должно быть пустым."); }
        if (isChecked("accommodationsInfo.hasBathOrShower") === true) { return validationFail("Поле «Ванна и (или) душ» должно быть пустым."); }

        if (getValueByName("accommodationsInfo.hotWaterSupply") != 0) { return validationFail("Поле «Горячее водоснабжение» должно быть пустым."); }
        if (getValueByName("accommodationsInfo.heating") != 0) { return validationFail("Поле «Отопление» должно быть пустым."); }
        if (getValueByName("accommodationsInfo.cookingEquipment") != 0) { return validationFail("Поле «Кухонное оборудование» должно быть пустым."); }
    }

}

function validatePerson() {
    // alert(isChecked("accommodationsInfo.hasBathOrShower"));



    // alert("SWALLOW MY CUM");
    // return false;
}