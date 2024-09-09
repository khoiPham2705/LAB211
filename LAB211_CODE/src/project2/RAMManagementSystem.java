/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package project2;
import java.util.*;
import java.io.*;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.time.Year;
import project1.ProductList;

/**
 *
 * @author DELL
 */
public class RAMManagementSystem {
    static boolean cont = true;
    public static void main(String[] agrs){
        Scanner sc = new Scanner(System.in);
        
        
        int choice;
        String ch ; 
        RAMList RAMList = new RAMList();
        
        
        
        do {
            
            System.out.println("*** MENU ****");
            System.out.println("1. Add a product.");
            System.out.println("Others - Quit.  ");
            System.out.println("**************");
            choice = sc.nextInt();
            sc.nextLine();// Consume the newline left by nextInt()
            
            switch (choice) {
                case 1:
                    RAMList.addRAMItem();
                    goBackMenu();
                    break;
                case 2:
                    RAMList.listRAMItem();
                    goBackMenu();      
            }       
    }while (cont == true && choice >= 1 && choice <= 6);
        
        
    }
    
    public static void goBackMenu() {
        Scanner sc = new Scanner(System.in);
        System.out.println("Do you want to go back to the main menu? (Yes or No):");
        String ch = sc.nextLine().trim();
        if (ch.equalsIgnoreCase("no")) {
            cont = false; 
        }
    }

}
