/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package project1;

import java.util.List;
import java.util.Scanner;

/**
 *
 * @author DELL
 */

public class main {
    static boolean cont = true;
    public static void main(String[] agrs){
        Scanner scanner = new Scanner(System.in);
        
        
        int choice;
        String ch ; 
        ProductList productList = new ProductList();
        

        do {
            
            System.out.println("*** MENU ****");
            System.out.println("1. Add a product.");
            System.out.println("2. Search product by product name, return a list of all products that same name.");
            System.out.println("3. Update product.");
            System.out.println("4. Delete product. ");
            System.out.println("5. Save products to file. ");
            System.out.println("6. Print list products from the file. ");
            System.out.println("Others - Quit.  ");
            System.out.println("**************");
            choice = scanner.nextInt();
            scanner.nextLine();// Consume the newline left by nextInt()
            
            switch (choice) {
                case 1:
                    productList.addProduct();
                    goBackMenu();
                    break;
                case 2:
                    System.out.println("Enter product name:");
                    String name = scanner.nextLine().toUpperCase();
                    List<Product> products = productList.searchName(name);
    
                   if (products.isEmpty()) {                      
                       System.err.println("Have no any Product");
                 } else {                    
                       for (Product product : products) {                      
                           System.out.println(product.toString());                         
        }                     
    }
                   goBackMenu();
                   break;  
                case 3:
                    productList.updateInformation();
                    goBackMenu();
                    break;
                case 4:
                    productList.deleteInformation();
                    goBackMenu();
                    break;
                case 5:
                    productList.saveToFile("src/project1/Product.txt");
                    goBackMenu();
                    break;
                case 6:
                    productList.loadFromFile("src/project1/Product.txt");
                    
                    productList.sortByPriceName();
                    productList.listProduct();
                    goBackMenu();
                    break;
                
            }       
    }while (cont == true && choice >= 1 && choice <= 6);
        
        
    }
    public static int menu(Object... options){
        Scanner sc = new Scanner(System.in);
        int L = options.length;
        for (int i = 0 ; i < L ; i++){
            System.out.println((i+1) + "-" + options[i].toString());
        }
        System.out.println("Choose (1.." + L + "): ");
        return Integer.parseInt(sc.nextLine());
    }
    public static Object chooseOne (Object... options){
        int pos = menu(options);
        if (pos < 0 || pos >= options.length) return null;
        return options[pos-1];
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
