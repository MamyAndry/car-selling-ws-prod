package carselling.selling.entity.advancedResearch;

public class CarDetails {
    String description;
    Double min_price;
    Double max_price;
    String idLocation;
    String idTransmission;
    String idFuelType;
    String color;
    String idMotor;
    Double min_kilometrage;
    Double max_kilometrage;
    String idCarStatus;
    String idGearBox;
    String idModel;
    String idBrand;
    String idCategory;
    String idOrigin;

    public CarDetails(String description, Double min_price, Double max_price, String idLocation, String idTransmission, String idFuelType, String color, String idMotor, Double min_kilometrage, Double max_kilometrage, String idCarStatus, String idGearBox, String idModel, String idBrand, String idCategory, String idOrigin) {
        this.description = description;
        this.min_price = min_price;
        this.max_price = max_price;
        this.idLocation = idLocation;
        this.idTransmission = idTransmission;
        this.idFuelType = idFuelType;
        this.color = color;
        this.idMotor = idMotor;
        this.min_kilometrage = min_kilometrage;
        this.max_kilometrage = max_kilometrage;
        this.idCarStatus = idCarStatus;
        this.idGearBox = idGearBox;
        this.idModel = idModel;
        this.idBrand = idBrand;
        this.idCategory = idCategory;
        this.idOrigin = idOrigin;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Double getMin_price() {
        return min_price;
    }

    public void setMin_price(Double min_price) {
        this.min_price = min_price;
    }

    public Double getMax_price() {
        return max_price;
    }

    public void setMax_price(Double max_price) {
        this.max_price = max_price;
    }

    public String getIdLocation() {
        return idLocation;
    }

    public void setIdLocation(String idLocation) {
        this.idLocation = idLocation;
    }

    public String getIdTransmission() {
        return idTransmission;
    }

    public void setIdTransmission(String idTransmission) {
        this.idTransmission = idTransmission;
    }

    public String getIdFuelType() {
        return idFuelType;
    }

    public void setIdFuelType(String idFuelType) {
        this.idFuelType = idFuelType;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public String getIdMotor() {
        return idMotor;
    }

    public void setIdMotor(String idMotor) {
        this.idMotor = idMotor;
    }

    public Double getMin_kilometrage() {
        return min_kilometrage;
    }

    public void setMin_kilometrage(Double min_kilometrage) {
        this.min_kilometrage = min_kilometrage;
    }

    public Double getMax_kilometrage() {
        return max_kilometrage;
    }

    public void setMax_kilometrage(Double max_kilometrage) {
        this.max_kilometrage = max_kilometrage;
    }

    public String getIdCarStatus() {
        return idCarStatus;
    }

    public void setIdCarStatus(String idCarStatus) {
        this.idCarStatus = idCarStatus;
    }

    public String getIdGearBox() {
        return idGearBox;
    }

    public void setIdGearBox(String idGearBox) {
        this.idGearBox = idGearBox;
    }

    public String getIdModel() {
        return idModel;
    }

    public void setIdModel(String idModel) {
        this.idModel = idModel;
    }

    public String getIdBrand() {
        return idBrand;
    }

    public void setIdBrand(String idBrand) {
        this.idBrand = idBrand;
    }

    public String getIdCategory() {
        return idCategory;
    }

    public void setIdCategory(String idCategory) {
        this.idCategory = idCategory;
    }

    public void setIdOrigin(String idOrigin) {
        this.idOrigin = idOrigin;
    }

    public String getIdOrigin(){
        return idOrigin;
    }
}

