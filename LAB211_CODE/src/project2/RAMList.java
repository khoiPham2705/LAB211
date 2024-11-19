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

    ArrayList<RAMItem> rList;

    public RAMList() {
        rList = new ArrayList<>();
    }
    private Map<String, List<String>> ramData = new HashMap<>();

    // Đọc dữ liệu từ file ramitem.txt
    public void loadRAMData(String filePath) {
        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] parts = line.split("-");
                String type = parts[0];
                String speed = parts[1];

                // Lưu trữ speed theo từng type
                ramData.computeIfAbsent(type, k -> new ArrayList<>()).add(speed);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // Hiển thị danh sách các loại RAM để người dùng chọn
    public void displayRAMTypes() {
        System.out.println("Available RAM types:");
        for (String type : ramData.keySet()) {
            System.out.println(type);
        }
    }

    // Hiển thị các speed hợp lệ cho một loại RAM đã chọn
    public void displaySpeedsForType(String type) {
        List<String> speeds = ramData.get(type);
        if (speeds != null) {
            System.out.println("Available speeds for " + type + ": " + speeds);
        } else {
            System.out.println("No speeds available for the selected type.");
        }
    }

    // Kiểm tra xem speed có hợp lệ với type đã chọn hay không
    public boolean validateSpeedForType(String type, String speed) {
        List<String> speeds = ramData.get(type);
        if (speeds != null && speeds.contains(speed)) {
            return true;
        }
        return false;
    }

    public void addRAMItem() {
        Scanner sc = new Scanner(System.in);
        String type;
        String code;
        do {
            do {
                displayRAMTypes();
                System.out.print("Enter type: ");
                type = sc.nextLine().trim().toUpperCase();
                if (validateType(type, "src/project2/RAMItem.txt") == false) {
                    System.out.println("invalid type");
                }
            } while (validateType(type, "src/project2/RAMItem.txt") == false);

            String type_number;
            do {
                System.out.print("Enter type number: ");
                type_number = sc.nextLine().trim();
                if (isNumeric(type_number) == false) {
                    System.out.println("this is not a numerical!!!");
                }
            } while (isNumeric(type_number) == false);

            String RAM = "RAM";
            code = RAM + type + "-" + type_number;
        } while (this.searchCode(code) != -1);

        String speed;
        String MHz = "MHz";

        do {
            displaySpeedsForType(type);
            System.out.print("Enter bus speed: ");
            speed = sc.nextLine();
            if (isNumeric(speed) == false || validateSpeedForType(type, speed) == false) {
                System.out.println("Invalid speed");
            }
        } while (isNumeric(speed) == false || validateSpeedForType(type, speed) == false);
        String bus = speed + MHz;

        String brand;
        System.out.print("Enter brand: ");
        brand = sc.nextLine().trim();

        int quantity;
        do {
            System.out.print("Enter quantity: ");
            quantity = sc.nextInt();
            if (quantity < 0) {
                System.out.println("Quantity must higher than 0");
            }
        } while (quantity < 0);
        sc.nextLine();

        String production_month_year;
        do {
            System.out.print("Enter production date(Month_Year): ");
            production_month_year = sc.nextLine();
            if (validateMonthYear(production_month_year) == false) {
                System.out.println("invalid production date!!!");
            }
        } while (validateMonthYear(production_month_year) == false);

        boolean active = true;

        this.add(new RAMItem(code, type, bus, brand, quantity, production_month_year, active));

    }

    public boolean validateFormatCode(String code) {
        String[] tmp = code.split("-");
        if (isNumeric(tmp[1])) {
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

    public static boolean validateMonthYear(String input) {
        // Tách chuỗi dựa trên ký tự '_'
        String[] parts = input.split("_");

        // Kiểm tra xem chuỗi có đúng 2 phần không (MM và YYYY)
        if (parts.length != 2) {
            return false;
        }

        try {
            // Chuyển phần tháng và năm sang số nguyên
            int month = Integer.parseInt(parts[0]);
            int year = Integer.parseInt(parts[1]);

            // Kiểm tra giá trị hợp lệ của tháng và năm
            if (month >= 1 && month <= 12 && year >= 1000 && year <= 9999) {
                return true;
            }
        } catch (NumberFormatException e) {
            // Nếu phần tháng hoặc năm không phải là số nguyên
            return false;
        }

        return false;
    }

    public boolean validateType(String type, String filePath) {
        return validateId(type, filePath);
    }

    public void listRAMItem() {
        for (RAMItem RAMItem : this) {
            System.out.println(RAMItem);
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

    private static boolean validateSpeed(String speed, String filePath) {
        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {

            String line;
            while ((line = br.readLine()) != null) {
                String[] parts = line.split("-");
                if (parts[1].trim().equals(speed)) {
                    return true;
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return false;
    }

    public void searchType(String name) {
        List<RAMItem> matchingRAMItem = new ArrayList<>();
        for (int i = 0; i < this.size(); i++) {
            if (this.get(i).getType().equalsIgnoreCase(name)) {
                matchingRAMItem.add(this.get(i));
            }
        }
        if (matchingRAMItem.isEmpty()) {
            System.out.println("No RAM items found with the specified type.");
        } else {
            for (RAMItem x : matchingRAMItem) {
                System.out.println(x.getCode() + "," + x.getType() + "," + x.getProduction_month_year() + "," + x.getQuantity());
            }
        }
    }

    public void searchBus(String name) {
        List<RAMItem> matchingRAMItem = new ArrayList<>();
        String searchBus = name.endsWith("MHz") ? name : name + "MHz"; // Thêm "MHz" nếu cần

        for (RAMItem item : this) {
            if (item.getBus().equalsIgnoreCase(searchBus)) {
                matchingRAMItem.add(item);
            }
        }

        // Kiểm tra xem có tìm được sản phẩm nào không
        if (matchingRAMItem.isEmpty()) {
            System.out.println("No RAM items found with the specified bus speed.");
        } else {
            for (RAMItem x : matchingRAMItem) {
                System.out.println(x.getCode() + "," + x.getBus() + "," + x.getProduction_month_year() + "," + x.getQuantity());
            }
        }
    }

    public void searchBrand(String name) {
        List<RAMItem> matchingRAMItem = new ArrayList<>();
        for (int i = 0; i < this.size(); i++) {
            if (this.get(i).getBrand().equalsIgnoreCase(name)) {
                matchingRAMItem.add(this.get(i));
            }
        }
        if (matchingRAMItem.isEmpty()) {
            System.out.println("No RAM items found with the specified type.");
        } else {
            for (RAMItem x : matchingRAMItem) {
                System.out.println(x.getCode() + "," + x.getBrand() + "," + x.getProduction_month_year() + "," + x.getQuantity());
            }
        }

    }

    public static boolean isBlank(String str) {
        return str == null || str.trim().isEmpty();
    }

    public void updateInformation() {
        Scanner sc = new Scanner(System.in);
        String code;
        String type;
        String ramtype;
        ;
        do {

            System.out.println("Enter code:");
            code = sc.nextLine().trim().toUpperCase();
            String[] tmp = code.split("-");
            ramtype = tmp[0];
            type = ramtype.substring(3);
            if (validateType(type, "src/project2/RAMItem.txt") == false) {
                System.out.println("Invalid type hehe");
            }
        } while (validateType(type, "src/project2/RAMItem.txt") == false);

        int pos = this.searchCode(code);
        if (pos == -1) {
            System.err.println("RAMItem does not exist.");
            return;
        } else {
            do {
                do {
                    displayRAMTypes();

                    System.out.print("Enter type: ");
                    type = sc.nextLine().trim().toUpperCase();
                    if (validateType(type, "src/project2/RAMItem.txt") == false) {
                        System.out.println("invalid type");
                    }
                } while (validateType(type, "src/project2/RAMItem.txt") == false);

                String type_number;
                do {
                    System.out.print("Enter type number: ");
                    type_number = sc.nextLine().trim();
                    if (isNumeric(type_number) == false) {
                        System.out.println("this is not a numerical!!!");
                    }
                } while (isNumeric(type_number) == false);

                String RAM = "RAM";
                code = RAM + type + "-" + type_number;
            } while (this.searchCode(code) != -1);

            String speed;
            String MHz = "MHz";

            do {
                displaySpeedsForType(type);
                System.out.print("Enter bus speed: ");
                speed = sc.nextLine();
                if (isNumeric(speed) == false || validateSpeedForType(type, speed) == false) {
                    System.out.println("Invalid speed");
                }
            } while (isNumeric(speed) == false || validateSpeedForType(type, speed) == false);
            String bus = speed + MHz;

            String brand;
            System.out.print("Enter brand: ");
            brand = sc.nextLine().trim();

            int quantity;
            System.out.print("Enter quantity: ");
            quantity = sc.nextInt();
            sc.nextLine();

            String production_month_year;
            do {
                System.out.print("Enter production date(Month_Year): ");
                production_month_year = sc.nextLine();
                if (validateMonthYear(production_month_year) == false) {
                    System.out.println("invalid production date!!!");
                }
            } while (validateMonthYear(production_month_year) == false);

            boolean active = true;

            if (isBlank(code) == false) {
                this.get(pos).setCode(code);
            }
            if (isBlank(type) == false) {
                this.get(pos).setType(type);
            }
            if (isBlank(bus) == false) {
                this.get(pos).setBus(bus);
            }
            if (isBlank(brand) == false) {
                this.get(pos).setBrand(brand);
            }
            updateQuantity(quantity, pos);
            if (isBlank(production_month_year) == false) {
                this.get(pos).setProduction_month_year(production_month_year);
            }

            System.out.println("Update Success");
        }
    }

    public void updateQuantity(Integer newQuantity, int pos) {
        if (newQuantity != null) {
            this.get(pos).setQuantity(newQuantity);
        }

    }

    public void deleteInformation() {
        Scanner sc = new Scanner(System.in);
        String code;
        String type;
        String ramtype;
        do {
            System.out.println("Enter code:");
            code = sc.nextLine().trim().toUpperCase();
            String[] tmp = code.split("-");
            ramtype = tmp[0];
            type = ramtype.substring(3);
            if (validateType(type, "src/project2/RAMItem.txt") == false) {
                System.out.println("Invalid type hehe");
            }
        } while (validateType(type, "src/project2/RAMItem.txt") == false);

        int pos = this.searchCode(code);
        if (pos == -1) {
            System.err.println("RAMItem does not exist.");
            return;
        } else {
            System.out.println("Are you sure you want to delete the product with Code " + code + "? (Yes or No):");
            String confirm = sc.nextLine().trim();
            if (confirm.equalsIgnoreCase("yes")) {
                this.get(pos).setActive(false);
                System.out.println("Deletion successfully.");
            } else {
                System.out.println("Deletion canceled.");
            }
        }
    }

    public void displayActiveItems() {
        for (RAMItem item : this) {
            if (item.isActive()) {
                System.out.println(item);
            }
        }
    }

    // Lưu dữ liệu vào file nhị phân
    public void saveActiveItemsToFile(String fileName) {
        List<RAMItem> activeItems = new ArrayList<>();
        for (RAMItem item : this) {
            if (item.isActive()) { // Assuming isActive() returns boolean
                activeItems.add(item);
            }
        }

        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(fileName))) {
            oos.writeObject(activeItems); // Write only active items
            System.out.println("Active RAM data saved successfully.");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // Tải dữ liệu từ file nhị phân
    public void loadActiveItemsFromFile(String fileName) {
        File file = new File(fileName);
        if (file.length() == 0) {
            System.out.println("The file is empty. No data to load.");
            return; // Kết thúc phương thức nếu file rỗng
        }
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(fileName))) {
            List<RAMItem> loadedItems = (List<RAMItem>) ois.readObject();
            this.clear();
            this.addAll(loadedItems);
            System.out.println("Active RAM data loaded successfully.");
        } catch (FileNotFoundException e) {
            System.out.println("File not found, please ensure the file exists.");
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}
