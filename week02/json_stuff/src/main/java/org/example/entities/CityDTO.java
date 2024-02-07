package org.example.entities;

public class CityDTO {
    String city_name;
    String zip_code;
    String state;
    String country;
    String full_name;
    String full_adress;
    String full_name_and_adress;
    public CityDTO(String city_name, String zip_code,String state,String country,String full_name,String full_adress, String full_name_and_adress){
        this.city_name =city_name;
        this.zip_code = zip_code;
        this.state = state;
        this.country = country;
        this.full_name = full_name;
        this.full_adress = full_adress;
        this.full_name_and_adress = full_name_and_adress;
    }
    public String getCity_name() {
        return city_name;
    }

    public void setCity_name(String city_name) {
        this.city_name = city_name;
    }

    public String getZip_code() {
        return zip_code;
    }

    public void setZip_code(String zip_code) {
        this.zip_code = zip_code;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getFull_name() {
        return full_name;
    }

    public void setFull_name(String full_name) {
        this.full_name = full_name;
    }

    public String getFull_adress() {
        return full_adress;
    }

    public void setFull_adress(String full_adress) {
        this.full_adress = full_adress;
    }

    public String getFull_name_and_adress() {
        return full_name_and_adress;
    }

    public void setFull_name_and_adress(String full_name_and_adress) {
        this.full_name_and_adress = full_name_and_adress;
    }

    @Override
    public String toString() {
        return "CityDTO{" +
                "city_name='" + city_name + '\'' +
                ", zip_code='" + zip_code + '\'' +
                ", state='" + state + '\'' +
                ", country='" + country + '\'' +
                ", full_name='" + full_name + '\'' +
                ", full_adress='" + full_adress + '\'' +
                ", full_name_and_adress='" + full_name_and_adress + '\'' +
                '}';
    }
}
