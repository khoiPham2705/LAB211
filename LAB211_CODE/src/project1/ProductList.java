/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package project1;

/**
 *
 * @author DELL
 */

import java.util.*;
import java.io.*;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.time.Year;

public class ProductList extends ArrayList<Product> {

    ArrayList<Product> products;

    public ProductList() {
        products = new ArrayList<>();
    }
    public void addProduct() {
        Scanner sc = new Scanner(System.in);

        System.out.print("Enter ID: ");
        String tID = sc.nextLine().trim().toUpperCase();
        if (this.searchID(tID) != -1) {
            System.err.println("ID already exist!!!");
            return;
        }

        String tName;
        do {
            System.out.print("Enter Name: ");
            tName = sc.nextLine().trim();
        } while (tName.length() < 1);

        String tBrand;
        do {
            System.out.print("Enter brand ID: ");
            tBrand = sc.nextLine().trim();
        } while (validateBrandId(tBrand, "src/project1/01_Brand.txt") == true);

        String tCategory;
        do {
            System.out.print("Enter category ID: ");
            tCategory = sc.nextLine().trim();;
        } while (validateCategoryId(tCategory, "src/project1/01_Category.txt") == true);
        
        String tYear;
        do{
            System.out.print("Enter model year:");
            tYear = sc.nextLine().trim();;
        } while ( isValidModelYear (tYear) == false);
        
        int tPrice;
        do{
            System.out.print("Enter list price: ");
            tPrice = sc.nextInt();
        } while (tPrice < 0);

        this.add(new Product(tID, tName, tBrand, tCategory, tYear , tPrice));

    }
    public int searchID(String ID) {
        for (int i = 0; i < this.size(); i++) {
            if (this.get(i).getId().equals(ID)) {
                return i;
            }
        }
        return -1;
    }

     public static boolean validateBrandId(String brandId, String brandFilePath) {
        return validateId(brandId, brandFilePath);
    }

    
    public static boolean validateCategoryId(String categoryId, String categoryFilePath) {
        return validateId(categoryId, categoryFilePath);
    }


    private static boolean validateId(String id, String filePath) {
        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = br.readLine()) != null) {
                
                String[] parts = line.split(",");
                if (parts[0].trim().equals(id)) {
                    return true;
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return false;
    }
    public static boolean isValidModelYear(String modelYear) {
        try {
            int year = Integer.parseInt(modelYear);
            int currentYear = Year.now().getValue();
            return year >= 1900 && year <= currentYear;
        } catch (NumberFormatException e) {
            return false; 
        }
    }
    
    public List<Product> searchName(String name) {
        List<Product> matchingProducts = new ArrayList<>();
        for (int i = 0; i < this.size(); i++) {
            if (this.get(i).getName().equalsIgnoreCase(name)) {
                matchingProducts.add(this.get(i));          
        }
    }
        Collections.sort(matchingProducts);
        return matchingProducts;
    }
    public void updateInformation(){
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter ID:");
        String x = sc.nextLine().trim().toUpperCase();
        
        int pos = this.searchID(x);
        if (pos == -1) {
            System.err.println("Product does not exist.");
            return;
        }
    
        else{
            String tID;
            System.out.print("Enter ID: ");
            tID = sc.nextLine().trim();
            
            String tName;
            System.out.print("Enter name: ");
            tName = sc.nextLine().trim();
            
            String tBrand;
            System.out.print("Enter brand ID: ");
            tBrand = sc.nextLine().trim();
            
            String tCategory;
            System.out.print("Enter Category ID: ");
            tCategory = sc.nextLine().trim();
            
            String tYear;
            System.out.print("Enter model year: ");
            tYear = sc.nextLine().trim();
            
            int tPrice;
            System.out.print("Enter price: ");
            tPrice = sc.nextInt();
            
            if(tID != null){
                this.get(pos).setId(tID);
            }
            if(tName != null){
                this.get(pos).setName(tName);
            }
            if(tBrand != null){
                this.get(pos).setBrandId(tBrand);
            }
            if(tCategory != null){
                this.get(pos).setCategoryId(tCategory);
            }
            if(tYear != null){
                this.get(pos).setModelYear(tYear);
            }
            if(tPrice >= 0){
                this.get(pos).setListPrice(tPrice);
            }
            
            System.out.println("Update Success");
            
               
            
            
        }
        
}
    public  void deleteInformation(){
            Scanner sc = new Scanner(System.in);
            System.out.println("Enter ID:");
            String x = sc.nextLine().trim().toUpperCase();
        
            int pos = this.searchID(x);
            if (pos == -1) {              
                System.err.println("Product does not exist.");
                return;
        }
            else{
                System.out.println("Are you sure you want to delete the product with ID " + x + "? (Yes or No):");
                String confirm = sc.nextLine().trim();
                if (confirm.equalsIgnoreCase("yes")) {
                    this.remove(pos);
                    System.out.println("Deletion successfully.");
                }
                else {
                    System.out.println("deletion canceled.");
                }
            }
        }
    public boolean saveToFile(String filename) {
        if (this.isEmpty()) {
            System.out.println("Empty productList");
            return false;
        }

        try (BufferedWriter bw = new BufferedWriter(new FileWriter(filename))) {

            for (Product x : this) {
                bw.write(x.toString());
                bw.newLine();
            }

            System.out.println("Done!!!");
            return true;
        } catch (IOException e) {
            System.err.println("Error writing file: " + filename);
        } catch (Exception e) {
            System.err.println("ErrBrandWrite: " + e);
        }
        return false;
    }
    public boolean loadFromFile(String filename) {

        try (BufferedReader br = new BufferedReader(new FileReader(filename))) {

            String line;
            while ((line = br.readLine()) != null) {
                if (line.length() < 10) {
                    continue;
                }

                String[] data = line.split(",");
                Product prd = new Product();
                
                prd.setId(data[0]);
                prd.setName(data[1]);
                prd.setBrandId(data[2]);
                prd.setCategoryId(data[3]);
                prd.setModelYear(data[4]);
                prd.setListPrice((int) Double.parseDouble(data[5]));

                

                this.add(prd);               
            }
            return true;
        } catch (IOException e) {
            System.err.println("Error reading file: " + filename);
        } catch (Exception e) {
            System.err.println("ErrBrandLoad: " + e);
        }
        return false;
    }
    public void listProduct(){
        for (Product product: this){
            System.out.println(product);
        }
    }
    public static Comparator<Product> sortByPrice() {
        return Comparator.comparingInt(Product::getListPrice);
    }
    public static Comparator<Product> sortByName() {
        return Comparator.comparing(Product::getName);
    }
    public void sortByPriceName() {
    Collections.sort(this, sortByPrice().thenComparing(sortByName()));
}
}

     
    



    

}