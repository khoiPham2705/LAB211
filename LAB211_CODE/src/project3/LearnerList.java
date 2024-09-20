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
import java.util.ArrayList;
import java.util.*;

public class LearnerList extends ArrayList<Learner> {

    Scanner sc = new Scanner(System.in);
    ArrayList<Learner> learners;

    public LearnerList() {
        learners = new ArrayList<>();
    }

    public void addLearnerToCourse(CourseList courseList) {
    // Hiển thị danh sách các khóa học
    System.out.println("Available courses:");
    courseList.listCourse();

    // Nhập mã khóa học
    System.out.print("Enter course code to add learner: ");
    String courseCode = sc.nextLine().trim().toUpperCase();
    int courseIndex = courseList.searchCode(courseCode);

    // Kiểm tra mã khóa học có tồn tại không
    if (courseIndex == -1) {
        System.out.println("Invalid course code.");
        return;
    }

    Course selectedCourse = courseList.get(courseIndex);

    // Kiểm tra giới hạn học viên
    if (selectedCourse.getLearners().size() >= selectedCourse.getMaxLearners()) {
        System.out.println("The course has reached its maximum capacity.");
        return;
    }

    // Nhập thông tin học viên
    System.out.print("Enter learner code: ");
    String code = sc.nextLine().trim();
    System.out.print("Enter learner name: ");
    String name = sc.nextLine().trim();
    String dateOfBirth;
    do {
        System.out.print("Enter learner date of birth: ");
        dateOfBirth = sc.nextLine();
    } while (!validateDay(dateOfBirth));

    String score;
    int number;
    do {
        System.out.print("Enter score (0-10): ");
        score = sc.nextLine();
    } while (!isNumeric(score) || (number = Integer.parseInt(score)) < 0 || number > 10);

    // Thêm học viên vào danh sách
    selectedCourse.learners.add(new Learner(code, name, dateOfBirth, score, courseCode));
    System.out.println("Learner added to the course.");
}

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

    public boolean isNumeric(String str) {
        try {
            Integer.parseInt(str);
            return true; // Nếu chuyển đổi thành công, đó là số
        } catch (NumberFormatException e) {
            return false; // Nếu xảy ra ngoại lệ, không phải số
        }
    }

    public void updateLearnerScore() {
        // Hiển thị submenu
        String option;

        System.out.println("=== Update Learner Score ===");
        System.out.println("1. Enter learner code and update score");

        System.out.print("Choose an option: ");
        option = sc.nextLine();

        // Nhập mã học viên
        System.out.print("Enter learner code: ");
        String code = sc.nextLine().trim();

        // Tìm học viên trong danh sách
        Learner learner = findLearnerByCode(code);

        if (learner == null) {
            System.out.println("The learner does not exist.");
        } else {
            // Nhập và kiểm tra điểm
            String score;
            int number;
            do {
                System.out.print("Enter score (0-10): ");
                score = sc.nextLine();
            } while (!isNumeric(score) || (number = Integer.parseInt(score)) < 0 || number > 10);

            // Cập nhật điểm cho học viên
            learner.setScore(score); // Đảm bảo rằng bạn có phương thức setScore trong lớp Learner
            System.out.println("Score updated successfully for learner with code: " + code);
            System.out.println("New score: " + score);
        }

    }

    // Phương thức tìm học viên theo mã
    private Learner findLearnerByCode(String code) {
        for (Learner learner : this) {
            if (learner.getCode().equalsIgnoreCase(code)) {
                return learner;
            }
        }
        return null;
    }

    public void displayAllLearnersInfo() {
    // Kiểm tra nếu danh sách học viên trống
    if (this.isEmpty()) {
        System.out.println("There are no learners in the list.");
    } else {
        // Duyệt qua danh sách học viên và hiển thị thông tin của từng học viên
        for (Learner learner : this) {
            System.out.println("=== Learner Information ===");
            System.out.println("Code: " + learner.getCode());
            System.out.println("Name: " + learner.getName());
            System.out.println("Date of Birth: " + learner.getDateOfBirth());
            System.out.println("Score: " + learner.getScore());

            // Tính trạng thái và GPA
            String status = calculateStatus(learner.getScore());
            double gpa = calculateGPA(learner.getScore());

            System.out.println("Status: " + status);
            System.out.println("GPA: " + gpa);
            System.out.println("-------------------------------");
        }
    }
}

    // Phương thức tính trạng thái học viên
    private String calculateStatus(String score) {
        int scoreValue = Integer.parseInt(score);
        return scoreValue >= 5 ? "Pass" : "Fail"; // Đậu nếu điểm >= 5, rớt nếu dưới 5
    }

    // Phương thức tính GPA từ điểm (ví dụ, GPA từ điểm số)
    private double calculateGPA(String score) {
        int scoreValue = Integer.parseInt(score);
        return (double) scoreValue; // Điểm trực tiếp là GPA (0-10)
    }

}
