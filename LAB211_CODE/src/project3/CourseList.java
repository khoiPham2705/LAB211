/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package project3;

/**
 *
 * @author DELL
 *
 */
import java.util.ArrayList;
import java.util.*;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class CourseList extends ArrayList<Course> {

    Scanner sc = new Scanner(System.in);
    ArrayList<Course> tCourse;
    TopicList tList;

    public CourseList() {
    }

    public CourseList(TopicList tList) {
        tCourse = new ArrayList();
        this.tList = tList;
    }

    public void addCourse() {
        String code;
        do {
            sc.nextLine();

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
            System.out.print("Enter type(online or offline)");
            type = sc.nextLine().trim().toLowerCase();
        } while (!type.equals("online") && !type.equals("offline"));

        String title;
        System.out.print("Enter title: ");
        title = sc.nextLine();

        String beginDate;
        do {

            System.out.print("Enter begin date: ");
            beginDate = sc.nextLine();
            if (validateDay(beginDate) == false) {
                System.out.println("invalid date");
            }
        } while (validateDay(beginDate) == false);

        String endDate;
        do {
            System.out.print("Enter end date: ");
            endDate = sc.nextLine();
            if (validateEndDate(beginDate, endDate) == false) {
                System.out.println("invalid date");
            }
        } while ((validateEndDate(beginDate, endDate) == false));

        String tuitionFee;
        do {
            System.out.print("Enter tuition fee: ");
            tuitionFee = sc.nextLine();
            if (isNumeric(tuitionFee) == false) {
                System.out.println("Invalid tuition fee");
            }
        } while (isNumeric(tuitionFee) == false);

        System.out.println("Choose a topic from the following list:");
        tList.listTopic();  // Hiển thị danh sách topic
        String topicCode;
        Topic selectedTopic = null;
        do {
            System.out.print("Enter topic code: ");
            topicCode = sc.nextLine().trim().toUpperCase();
            int pos = tList.searchCode(topicCode);
            if (pos != -1) {
                selectedTopic = tList.get(pos);
            } else {
                System.out.println("Invalid topic code. Please try again.");
            }
        } while (selectedTopic == null);

        int learner;
        System.out.print("Enter max learner: ");
        learner = sc.nextInt();

        this.add(new Course(code, name, type, title, beginDate, endDate, tuitionFee, topicCode, learner));
    }

    public void updateCourse() {
        System.out.print("Enter code to update: ");
        String fCode = sc.nextLine().trim().toUpperCase();
        int pos = searchCode(fCode);
        if (pos == -1) {
            System.out.println("The topic does not exist.");
            return;
        }
        String code;
        do {
            System.out.print("Enter new code (press Enter to skip): ");
            code = sc.nextLine().trim().toUpperCase();
            if (!code.isEmpty() && !code.equals(fCode) && searchCode(code) != -1) {
                // Kiểm tra nếu mã mới không phải là chính mã đang update và đã tồn tại
                System.out.println("Code already exists. Please enter a different code.");
            }
        } while (!code.isEmpty() && !code.equals(fCode) && searchCode(code) != -1);

        String name;
        System.out.print("Enter name: ");
        name = sc.nextLine();

        String type;
        do {
            System.out.print("Enter type(online or offline)");
            type = sc.nextLine().trim().toLowerCase();
        } while (!type.equals("online") && !type.equals("offline") && !type.isEmpty());

        String title;
        System.out.print("Enter title: ");
        title = sc.nextLine();

        String beginDate;
        do {

            System.out.print("Enter begin date(day/month/year): ");
            beginDate = sc.nextLine();
            if (validateDay(beginDate) == false) {
                System.out.println("invalid date");
            }
        } while (validateDay(beginDate) == false && !beginDate.isEmpty());

        String endDate;
        do {
            System.out.print("Enter end date(day/month/year): ");
            endDate = sc.nextLine();
            if (!validateEndDate(beginDate, endDate)) {
                System.out.println("invalid date");
            }
        } while ((!validateEndDate(beginDate, endDate)) && !endDate.isEmpty());

        String tuitionFee;
        do {
            System.out.print("Enter tuition fee: ");
            tuitionFee = sc.nextLine();
            if (isNumeric(tuitionFee) == false) {
                System.out.println("Invalid tuition fee");
            }
        } while (isNumeric(tuitionFee) == false);

        System.out.println("Choose a topic from the following list:");
        tList.listTopic();  // Hiển thị danh sách topic
        String topicCode;
        Topic selectedTopic = null;
        do {
            System.out.print("Enter topic code: ");
            topicCode = sc.nextLine().trim().toUpperCase();
            int pos1 = tList.searchCode(topicCode);
            if (pos1 != -1) {
                selectedTopic = tList.get(pos);
            } else {
                System.out.println("Invalid topic code. Please try again.");
            }
        } while (selectedTopic == null);

        int learner;
        System.out.print("Enter max learner: ");
        learner = sc.nextInt();

        Course course = this.get(pos);
        if (!isBlank(code)) {
            course.setCode(code);
        }
        if (!isBlank(name)) {
            course.setName(name);
        }
        if (!isBlank(type)) {
            course.setType(type);
        }
        if (!isBlank(title)) {
            course.setTitle(title);
        }
        if (!isBlank(beginDate)) {
            course.setBeginDate(beginDate);
        }
        if (!isBlank(endDate)) {
            course.setEndDate(endDate);
        }
        if (!isBlank(tuitionFee)) {
            course.setTuitionFee(tuitionFee);
        }
        if (!isBlank(endDate)) {
            course.setEndDate(endDate);
        }
        if (topicCode != null) {
            course.setTopic(topicCode);
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

    // Hàm validate ngày tháng
    public static boolean validateDay(String date) {
        // Tách chuỗi theo dấu "/"
        String[] parts = date.split("/");

        // Kiểm tra xem có đúng 3 phần (ngày, tháng, năm)
        if (parts.length != 3) {
            return false;
        }

        try {
            int day = Integer.parseInt(parts[0]);
            int month = Integer.parseInt(parts[1]);
            int year = Integer.parseInt(parts[2]);

            // Kiểm tra tháng có hợp lệ (từ 1 đến 12)
            if (month < 1 || month > 12) {
                return false;
            }

            // Kiểm tra ngày có hợp lệ theo từng tháng và năm nhuận
            if (day < 1) {
                return false;
            }

            switch (month) {
                case 2: // Tháng 2
                    if (isLeapYear(year)) { // Năm nhuận
                        return day <= 29;
                    } else {
                        return day <= 28;
                    }
                case 4:
                case 6:
                case 9:
                case 11: // Các tháng có 30 ngày
                    return day <= 30;
                default: // Các tháng có 31 ngày
                    return day <= 31;
            }

        } catch (NumberFormatException e) {
            return false; // Nếu không phải là số hợp lệ
        }
    }

    // Hàm kiểm tra năm nhuận
    public static boolean isLeapYear(int year) {
        // Năm nhuận là năm chia hết cho 4 nhưng không chia hết cho 100, hoặc chia hết cho 400
        return (year % 4 == 0 && year % 100 != 0) || (year % 400 == 0);
    }

    public static boolean isDateAfter(String beginDate, String endDate) {
        String[] beginParts = beginDate.split("/");
        String[] endParts = endDate.split("/");

        int beginDay = Integer.parseInt(beginParts[0]);
        int beginMonth = Integer.parseInt(beginParts[1]);
        int beginYear = Integer.parseInt(beginParts[2]); // Thêm năm vào đây

        int endDay = Integer.parseInt(endParts[0]);
        int endMonth = Integer.parseInt(endParts[1]);
        int endYear = Integer.parseInt(endParts[2]); // Thêm năm vào đây

        if (endYear > beginYear) {
            return true; // Nếu năm kết thúc lớn hơn năm bắt đầu
        } else if (endYear == beginYear) {
            if (endMonth > beginMonth) {
                return true; // Nếu tháng kết thúc lớn hơn tháng bắt đầu
            } else if (endMonth == beginMonth) {
                return endDay > beginDay; // Nếu tháng bằng nhau, so sánh ngày
            }
        }
        return false; // Trả về false nếu không thỏa mãn điều kiện
    }

// Hàm validate ngày kết thúc phải sau ngày bắt đầu
    public static boolean validateEndDate(String beginDate, String endDate) {
        if (!validateDay(beginDate) || !validateDay(endDate)) {
            return false;
        }
        return isDateAfter(beginDate, endDate);
    }

    public boolean isNumeric(String str) {
        try {
            Integer.parseInt(str);
            return true; // Nếu chuyển đổi thành công, đó là số
        } catch (NumberFormatException e) {
            return false; // Nếu xảy ra ngoại lệ, không phải số
        }
    }

    public static boolean isBlank(String str) {
        return str == null || str.trim().isEmpty();
    }

    public void deleteCourse() {
        String code;
        System.out.print("Enter code: ");
        code = sc.nextLine();
        int pos = this.searchCode(code);
        if (pos == -1) {
            System.out.println("The course does not exist");
            return;
        } else {
            System.out.println("Are you sure you want to delete the course with Code " + code + "? (Yes or No):");
            String confirm = sc.nextLine().trim();
            if (confirm.equalsIgnoreCase("yes")) {
                this.remove(pos);
                System.out.println("Deletion successfully.");
            } else {
                System.out.println("Deletion canceled.");
            }
        }
    }

    public void listCourse() {
        // Sắp xếp danh sách theo tên trước khi in ra  
        for (Course course : this) {
            System.out.println(course);
        }
    }

    public void displayCourses() {
        // Sắp xếp danh sách khóa học theo ngày bắt đầu
        this.sort(Comparator.comparing(Course::getBeginDate));

        // Duyệt qua danh sách khóa học
        for (Course course : this) {
            System.out.println("=== Course Information ===");
            System.out.println("Course Code: " + course.getCode());
            System.out.println("Course Name: " + course.getName());
            System.out.println("Begin Date: " + course.getBeginDate());
            System.out.println("End Date: " + course.getEndDate());
            System.out.println("Course Type: " + course.getType());

            // Tính số lượng học viên đậu và rớt
            int passCount = 0, failCount = 0;
            for (Learner learner : course.getLearners()) {
                if (Integer.parseInt(learner.getScore()) >= 5) {
                    passCount++;
                } else {
                    failCount++;
                }
            }

            System.out.println("Number of learners passed: " + passCount);
            System.out.println("Number of learners failed: " + failCount);

            // Tính tổng thu nhập
            double income = course.getLearners().size() * Double.parseDouble(course.getTuitionFee());
            System.out.println("Total income: $" + income);
            System.out.println("-------------------------------");
        }
    }

    public void searchByTopic(String topic) {
        List<Course> matchingTopiCourse = new ArrayList<>();
        for (int i = 0; i < this.size(); i++) {
            if (this.get(i).getTopic().equalsIgnoreCase(topic)) {
                matchingTopiCourse.add(this.get(i));
            }
        }
        if (matchingTopiCourse.isEmpty()) {
            System.out.println("No Topic found with the specified type.");
        } else {
            for (Course course : matchingTopiCourse) {
                System.out.println("=== Course Information ===");
                System.out.println("Course Code: " + course.getCode());
                System.out.println("Course Name: " + course.getName());
                System.out.println("Begin Date: " + course.getBeginDate());
                System.out.println("End Date: " + course.getEndDate());
                System.out.println("Course Type: " + course.getType());

                // Tính số lượng học viên đậu và rớt
                int passCount = 0, failCount = 0;
                for (Learner learner : course.getLearners()) {
                    if (Integer.parseInt(learner.getScore()) >= 5) {
                        passCount++;
                    } else {
                        failCount++;
                    }
                }

                System.out.println("Number of learners passed: " + passCount);
                System.out.println("Number of learners failed: " + failCount);

                // Tính tổng thu nhập
                double income = course.getLearners().size() * Double.parseDouble(course.getTuitionFee());
                System.out.println("Total income: $" + income);
                System.out.println("-------------------------------");
            }
        }
    }

    public void searchByName(String name) {
        List<Course> matchingNameCourse = new ArrayList<>();
        for (int i = 0; i < this.size(); i++) {
            if (this.get(i).getName().contains(name)) {
                matchingNameCourse.add(this.get(i));
            }
        }
        if (matchingNameCourse.isEmpty()) {
            System.out.println("No Topic found with the specified type.");
        } else {
            for (Course course : matchingNameCourse) {
                System.out.println("=== Course Information ===");
                System.out.println("Course Code: " + course.getCode());
                System.out.println("Course Name: " + course.getName());
                System.out.println("Begin Date: " + course.getBeginDate());
                System.out.println("End Date: " + course.getEndDate());
                System.out.println("Course Type: " + course.getType());

                // Tính số lượng học viên đậu và rớt
                int passCount = 0, failCount = 0;
                for (Learner learner : course.getLearners()) {
                    if (Integer.parseInt(learner.getScore()) >= 5) {
                        passCount++;
                    } else {
                        failCount++;
                    }
                }

                System.out.println("Number of learners passed: " + passCount);
                System.out.println("Number of learners failed: " + failCount);

                // Tính tổng thu nhập
                double income = course.getLearners().size() * Double.parseDouble(course.getTuitionFee());
                System.out.println("Total income: $" + income);
                System.out.println("-------------------------------");
            }
        }
    }
    

    public boolean saveToFile(String filename) {
        if (this.isEmpty()) {
            System.out.println("Empty courseList");
            return false;
        }

        try (BufferedWriter bw = new BufferedWriter(new FileWriter(filename))) {

            for (Course x : this) {
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
                Course prd = new Course();

                prd.setCode(data[0]);
                prd.setName(data[1]);
                prd.setType(data[2]);
                prd.setTitle(data[3]);
                prd.setBeginDate(data[4]);
                prd.setEndDate(data[5]);
                prd.setTuitionFee(data[6]);
                prd.setTopic(data[7]);
                prd.setMaxLearners((int) Double.parseDouble(data[8]));

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
