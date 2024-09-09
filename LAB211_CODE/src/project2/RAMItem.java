/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package project2;

/**
 *
 * @author DELL
 */
public class RAMItem {
    String code;
    String type;
    String bus;
    String brand;
    String quantity;
    String production_month_year;
    boolean active;

    public RAMItem() {
    }

    public RAMItem(String code, String type, String bus, String brand, String quantity, String production_month_year, boolean active) {
        this.code = code;
        this.type = type;
        this.bus = bus;
        this.brand = brand;
        this.quantity = quantity;
        this.production_month_year = production_month_year;
        this.active = active;
    }

    public String getCode() {
        return code;
    }

    public String getType() {
        return type;
    }

    public String getBus() {
        return bus;
    }

    public String getBrand() {
        return brand;
    }

    public String getQuantity() {
        return quantity;
    }

    public String getProduction_month_year() {
        return production_month_year;
    }

    public boolean isActive() {
        return active;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public void setType(String type) {
        this.type = type;
    }

    public void setBus(String bus) {
        this.bus = bus;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public void setQuantity(String quantity) {
        this.quantity = quantity;
    }

    public void setProduction_month_year(String production_month_year) {
        this.production_month_year = production_month_year;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    @Override
    public String toString() {
        return code  + "," + type + "," +  bus + "," + brand + "," + quantity + "," + production_month_year + "," + active ;
    }
    
    
    
    
    
}
