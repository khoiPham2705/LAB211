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

        this.add(new Course(code, name, type, title, beginDate, endDate, tuitionFee, topicCode,learner));
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

            System.out.print("Enter begin date: ");
            beginDate = sc.nextLine();
            if (validateDay(beginDate) == false) {
                System.out.println("invalid date");
            }
        } while (validateDay(beginDate) == false && !beginDate.isEmpty());

        String endDate;
        do {
            System.out.print("Enter end date: ");
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
    public static boolean validateDay(String dayMonth) {
        String[] parts = dayMonth.split("/");

        // Kiểm tra có đúng 2 phần và mỗi phần đều là số nguyên
        if (parts.length != 2) {
            return false;
        }

        try {
            int day = Integer.parseInt(parts[0]);
            int month = Integer.parseInt(parts[1]);

            // Kiểm tra tháng hợp lệ (1 đến 12)
            if (month < 1 || month > 12) {
                return false;
            }

            // Kiểm tra ngày hợp lệ theo từng tháng
            switch (month) {
                case 2: // Tháng 2
                    return day >= 1 && day <= 29;  // Không kiểm tra năm nhuận trong hàm này
                case 4: case 6: case 9: case 11: // Các tháng 30 ngày
                    return day >= 1 && day <= 30;
                default: // Các tháng 31 ngày
                    return day >= 1 && day <= 31;
            }

        } catch (NumberFormatException e) {
            return false; // Nếu không phải là số hợp lệ
        }
    }

    public static boolean isDateAfter(String beginDate, String endDate) {
        String[] beginParts = beginDate.split("/");
        String[] endParts = endDate.split("/");

        int beginDay = Integer.parseInt(beginParts[0]);
        int beginMonth = Integer.parseInt(beginParts[1]);
        int endDay = Integer.parseInt(endParts[0]);
        int endMonth = Integer.parseInt(endParts[1]);

        if (endMonth > beginMonth) {
            return true;
        } else if (endMonth == beginMonth) {
            return endDay > beginDay;
        } else {
            return false;
        }
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
    public void deleteCourse(){
        String code;
        System.out.print("Enter code: ");
        code = sc.nextLine();
        int pos = this.searchCode(code);
        if(pos == -1){
            System.out.println("The course does not exist");
            return;
        }
        else{
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
}
