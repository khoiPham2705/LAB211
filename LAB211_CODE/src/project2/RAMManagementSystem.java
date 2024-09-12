/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package project2;

import java.util.*;


/**
 *
 * @author DELL
 */
public class RAMManagementSystem {

    static boolean cont = true;

    public static void main(String[] agrs) {
        Scanner sc = new Scanner(System.in);

        int choice;
        String ch;
        RAMList RAMList = new RAMList();

        do {

            System.out.println("*** MENU ****");
            System.out.println("1. Add an Item.");
            System.out.println("2. Search SubMenu");
            System.out.println("3. Update Item Information");
            System.out.println("4. Delete Item");
            System.out.println("5. Show All Active Items");
            System.out.println("6.  Store Data to Files");
            System.out.println("7.Exit the program");
            System.out.println("**************");
            choice = sc.nextInt();
            sc.nextLine();// Consume the newline left by nextInt()

            switch (choice) {
                case 1:
                    RAMList.addRAMItem();
                    boolean cont2 = true;
                    do {
                        System.out.println("Do u want to continue adding more modules or return to the main menu(Yes or no)");
                        String choice2 = sc.nextLine().trim().toLowerCase();
                        if (choice2.equalsIgnoreCase("Yes")) {
                            RAMList.addRAMItem();
                            
                        } else {
                            
                            cont2 = false;
                        }
                        
                    }while(cont2 == true);
                    break;
                
                case 2:
                    String choicec2;
                    System.out.println("Search type,bus or brand?: ");
                    choicec2 = sc.nextLine();
                    if (choicec2.equalsIgnoreCase("type")) {
                        String type;
                        System.out.println("Enter type u want to find:");
                        type = sc.nextLine().trim().toUpperCase();
                        RAMList.searchType(type);
                    } else if (choicec2.equalsIgnoreCase("bus")) {
                        String bus;
                        System.out.println("Enter bus u want to find:");
                        bus = sc.nextLine().trim().toUpperCase();
                        RAMList.searchBus(bus);
                    } else if (choicec2.equalsIgnoreCase("brand")) {
                        String brand;
                        System.out.println("Enter brand u want to find:");
                        brand = sc.nextLine().trim().toUpperCase();
                        RAMList.searchBrand(brand);
                    }
                    
                    break;

                case 3:
                    RAMList.updateInformation();
                    
                    break;
                case 4:
                    RAMList.deleteInformation();
                    
                    break;
                case 5:
                    RAMList.displayActiveItems();
                    
                    break;
                case 6:
                case 7:
                    exitMenu();
                
                
            }
        } while (cont && choice >= 1 && choice <= 7);

    }

    public static void exitMenu() {
        Scanner sc = new Scanner(System.in);
        System.out.println("Do you want to go back to the main menu? (Yes or No):");
        String ch = sc.nextLine().trim();
        if (ch.equalsIgnoreCase("no")) {
            cont = false;
        }
    }
    

}
