/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package project2;

import java.util.ArrayList;
import java.util.*;
import java.io.*;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.time.Year;

/**
 *
 * @author DELL
 */
public class RAMList extends ArrayList<RAMItem> {

    Scanner sc = new Scanner(System.in);
    ArrayList<RAMItem> rList;

    public RAMList() {
        rList = new ArrayList<>();
    }

    public void addRAMItem() {
        

        
        String type;
        String code;
        do{
            do{
            System.out.print("Enter type: ");
            type = sc.nextLine().trim().toUpperCase();
            if(validateType(type,"src/project2/RAMItem.txt") == false){
                System.out.println("invalid type");
            }
        }while (validateType(type,"src/project2/RAMItem.txt") == false);

        String type_number;
        do{
            System.out.print("Enter type number: ");
            type_number= sc.nextLine().trim();
            if(isNumeric(type_number) == false){
                System.out.println("this is not a numerical!!!");
            }
        }while(isNumeric(type_number) == false);
        
        String RAM = "RAM";
        code = RAM + type + "-" + type_number;
        }while(this.searchCode(code) != -1);
        
        
        String speed;
        String MHz= "MHz";
        System.out.println("Enter bus speed: ");
        speed = sc.nextLine();
        String bus = speed + MHz;

        String brand;
        System.out.println("Enter brand: ");
        brand = sc.nextLine().trim();

        String quantity;
        System.out.println("Enter quantity: ");
        quantity = sc.nextLine().trim();

        
        
        String production_month_year;
        System.out.println("Enter production date(Month_Year): ");
        production_month_year = sc.nextLine();

        boolean active = true;
        
        this.add(new RAMItem(code,type,bus,brand,quantity,production_month_year,active));  

    }

    public boolean validateFormatCode(String code) {
        String[] tmp = code.split("-");
        if(isNumeric(tmp[1])){
            return true;
        }
        return false;
    }

    public boolean isNumeric(String str) {
        try {
            Integer.parseInt(str);
            return true; // Nếu chuyển đổi thành công, đó là số
        } catch (NumberFormatException e) {
            return false; // Nếu xảy ra ngoại lệ, không phải số
        }
    }
    
    public int searchCode(String code) {
        for (int i = 0; i < this.size(); i++) {
            if (this.get(i).getCode().equals(code)) {
                return i;
            }
        }
        return -1;
    }
    
    
    
    public boolean validateType(String type, String filePath){
        return validateId(type,filePath);
    }

    public void listRAMItem(){
        for (RAMItem RAMItem : this){
            System.out.print(RAMItem);
        }
    }
    
    
  
    private static boolean validateId(String id, String filePath) {
        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            
            
            String line;
            while ((line = br.readLine()) != null) {
                String[] parts = line.split("-");
                if (parts[0].trim().equals(id)) {
                    return true;
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return false;
    }
}
