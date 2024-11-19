/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package project1;
import java.util.*;
/**
 *
 * @author DELL
 */
public class Product implements Comparable<Product>{
    String id;
    String name;
    String brandId;
    String categoryId;
    String modelYear;
    double listPrice;

    public Product() {
    }
    
    

    public Product(String id, String name, String brandId, String categoryId, String modelYear, double listPrice) {
        this.id = id;
        this.name = name;
        this.brandId = brandId;
        this.categoryId = categoryId;
        this.modelYear = modelYear;
        this.listPrice = listPrice;
    }

    

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getBrandId() {
        return brandId;
    }

    public String getCategoryId() {
        return categoryId;
    }

    public String getModelYear() {
        return modelYear;
    }

    public double getListPrice() {
        return listPrice;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setBrandId(String brandId) {
        this.brandId = brandId;
    }

    public void setCategoryId(String categoryId) {
        this.categoryId = categoryId;
    }

    public void setModelYear(String modelYear) {
        this.modelYear = modelYear;
    }

    public void setListPrice(double listPrice) {
        this.listPrice = listPrice;
    }
    public String toString() {
    return id +"," + name + "," + brandId +"," + categoryId +"," + modelYear +"," + listPrice;
}
    @Override
    public int compareTo(Product other) {       
        return Integer.compare(Integer.parseInt(this.modelYear), Integer.parseInt(other.modelYear));
    }
    
    
}
