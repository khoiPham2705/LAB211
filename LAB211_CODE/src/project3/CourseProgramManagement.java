/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package project3;

import java.util.Scanner;

/**
 *
 * @author DELL
 */
public class CourseProgramManagement {

    static boolean cont = true;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int choice;
        TopicList topicList = new TopicList();  // Khởi tạo đối tượng TopicList
        CourseList courseList = new CourseList(topicList);
        LearnerList learnerList = new LearnerList();

        do {
            System.out.println("*** MENU ****");
            System.out.println("1. Manage Topic");
            System.out.println("2. Manage Course");
            System.out.println("3. Manage Learner");
            System.out.println("4. Search Topic");
            System.out.println("5. Save Topics, Courses and Learner to file.");
            System.out.println("6. Exit");
            System.out.println("**************");
            System.out.print("Please select an option: ");
            choice = sc.nextInt();
            sc.nextLine(); // Consume the newline left by nextInt()

            switch (choice) {
                case 1:

                    int topicChoice;
                    System.out.println("1. Add Topic to Catalog");
                    System.out.println("2. Update Topic");
                    System.out.println("3. Delete Topic");
                    System.out.println("4. Display All Topics");
                    System.out.print("Please select a topic management option: ");
                    topicChoice = sc.nextInt();
                    sc.nextLine(); // Consume newline

                    switch (topicChoice) {
                        case 1:
                            boolean addOrNot;
                            do {
                                topicList.addTopic();
                                System.out.println("Do u want to continue to add Course? ( Yes or No )");
                                String answer1 = sc.nextLine();
                                addOrNot = answer1.equals("yes");
                            } while (addOrNot);
                            break;
                        case 2:
                            topicList.updateTopic();
                            break;
                        case 3:
                            topicList.deleteTopic();
                            break;
                        case 4:
                            topicList.listTopic();
                            break;
                        default:
                            System.out.println("Invalid choice.");
                            break;
                    }

                    break;

                case 2:
                    boolean addOrNot;

                    int courseChoice;
                    System.out.println("1. Add Course");
                    System.out.println("2. Update Course");
                    System.out.println("3. Delete Course");
                    System.out.println("4. Display Course Information");
                    System.out.print("Please select an option: ");
                    courseChoice = sc.nextInt();
                    sc.nextLine(); // Consume newline

                    switch (courseChoice) {
                        case 1:
                            boolean addOrNot2;
                            do {

                                courseList.addCourse();
                                System.out.println("Do u want to continue to add Course? ( Yes or No )");
                                String answer1 = sc.nextLine();
                                addOrNot2 = answer1.equals("yes");
                            } while (addOrNot2);
                            break;
                        case 2:
                            courseList.updateCourse();
                            break;
                        case 3:
                            courseList.deleteCourse();
                            break;
                        case 4:
                            courseList.listCourse();
                            break;
                        default:
                            System.out.println("Invalid choice.");
                            break;
                    }
                    break;

                case 3:
                    System.out.println("1. Add learner to course");
                    System.out.println("2. Enter score for learner");
                    System.out.println("3. Display Learner information");
                    System.out.print("");
                    int choice3 = sc.nextInt();
                    switch (choice3) {
                        case 1:
                            boolean addOrNot2;
                            do {
                                learnerList.addLearnerToCourse(courseList);
                                System.out.println("Do u want to continue to add Learner? ( Yes or No )");
                                String answer1 = sc.nextLine();
                                sc.nextLine();
                                addOrNot2 = answer1.equals("yes");
                            } while (addOrNot2);
                            break;
                        case 2:
                            learnerList.updateLearnerScore();
                            break;
                        case 3:
                            learnerList.displayAllLearnersInfo();
                    }
                    break;

                case 4:
                    System.out.println("Search Topic is under development.");
                    break;

                case 5:
                    System.out.println("Save Topics, Courses and Learner to file is under development.");
                    break;

                case 6:
                    exitMenu();
                    break;

                default:
                    System.out.println("Invalid choice.");
                    break;
            }

        } while (cont && choice >= 1 && choice
                <= 6); // Vòng lặp sẽ chạy cho đến khi người dùng chọn thoát (6)

    }

    public static void exitMenu() {
        cont = false;
        System.out.println("Good bye");
    }
}
