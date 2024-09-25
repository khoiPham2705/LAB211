/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package project3;

/**
 *
 * @author DELL
 */
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.*;

public class TopicList extends ArrayList<Topic> {

    Scanner sc = new Scanner(System.in);
    ArrayList<Topic> tList;

    public TopicList() {
        tList = new ArrayList<>();
    }

    public void addTopic() {
        String code;
        do {
            System.out.print("Enter code: ");
            code = sc.nextLine().trim().toUpperCase();
            if (searchCode(code) != -1) { // Nếu tìm thấy code, thông báo code đã tồn tại
                System.out.println("Code already exists. Please enter a different code.");
            }
        } while (searchCode(code) != -1);

        String name;
        System.out.print("Enter name: ");
        name = sc.nextLine();

        String type;
        do {
            System.out.print("Enter type(long or short)");
            type = sc.nextLine().trim().toLowerCase();
        } while (!type.equals("long") && !type.equals("short"));

        String title;
        System.out.print("Enter title: ");
        title = sc.nextLine();

        String duration;
        do {
            System.out.print("Enter duration(slot): ");
            duration = sc.nextLine();
            if (isNumeric(duration) == false || Double.parseDouble(duration) < 0) {
                System.out.println("invalid duration");
            }
        } while (isNumeric(duration) == false || Double.parseDouble(duration) < 0);

        this.add(new Topic(code, name, type, title, duration));

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
            if (this.get(i).getCode().equalsIgnoreCase(code)) {
                return i;
            }
        }
        return -1;
    }

    public void updateTopic() {
        System.out.print("Enter code to update: ");
        String fCode = sc.nextLine().trim().toUpperCase();
        int pos = searchCode(fCode);
        if (pos == -1) {
            System.out.println("The topic does not exist.");
            return;
        }

        System.out.print("Enter new code: ");
        String code = sc.nextLine().trim().toUpperCase();

        System.out.print("Enter new name: ");
        String name = sc.nextLine();

        String type;
        do {
            System.out.print("Enter new type (long or short): ");
            type = sc.nextLine().trim().toLowerCase();
            if (type.isEmpty()) {
                break;
            }
        } while (!type.equals("long") && !type.equals("short"));

        System.out.print("Enter new title: ");
        String title = sc.nextLine();

        System.out.print("Enter new duration: ");
        String duration = sc.nextLine();

        Topic topic = this.get(pos);
        if (!isBlank(code)) {
            topic.setCode(code);
        }
        if (!isBlank(name)) {
            topic.setName(name);
        }
        if (!isBlank(type)) {
            topic.setType(type);
        }
        if (!isBlank(title)) {
            topic.setTitle(title);
        }
        if (!isBlank(duration)) {
            topic.setDuration(duration);
        }

        System.out.println("Update successful.");
    }

    public static boolean isBlank(String str) {
        return str == null || str.trim().isEmpty();
    }

    public void deleteTopic() {
        String code;
        System.out.print("Enter code: ");
        code = sc.nextLine();
        int pos = this.searchCode(code);
        if (pos == -1) {
            System.out.println("The topic does not exist");
            return;
        } else {
            System.out.println("Are you sure you want to delete the topic with Code " + code + "? (Yes or No):");
            String confirm = sc.nextLine().trim();
            if (confirm.equalsIgnoreCase("yes")) {
                this.remove(pos);
                System.out.println("Deletion successfully.");
            } else {
                System.out.println("Deletion canceled.");
            }
        }
    }

    public void listTopic() {
        // Sắp xếp danh sách theo tên trước khi in ra
        Collections.sort(this, Comparator.comparing(Topic::getName));
        for (Topic topic : this) {
            System.out.println(topic);
        }
    }

    public void searchByName(String name) {
        List<Topic> matchingNameTopic = new ArrayList<>();
        for (int i = 0; i < this.size(); i++) {
            if (this.get(i).getName().contains(name)) {
                matchingNameTopic.add(this.get(i));
            }
        }
        if (matchingNameTopic.isEmpty()) {
            System.out.println("No Topic found with the specified type.");
        } else {
            for (Topic x : matchingNameTopic) {
                System.out.println(x.toString());
            }
        }
    }

    public boolean saveToFile(String filename) {
        if (this.isEmpty()) {
            System.out.println("Empty topicList");
            return false;
        }

        try (BufferedWriter bw = new BufferedWriter(new FileWriter(filename))) {

            for (Topic x : this) {
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
                Topic prd = new Topic();

                prd.setCode(data[0]);
                prd.setName(data[1]);
                prd.setType(data[2]);
                prd.setTitle(data[3]);
                prd.setDuration(data[4]);

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

}
