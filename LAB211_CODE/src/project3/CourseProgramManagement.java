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
        
        topicList.loadFromFile("src/project3/topicData.txt");
        courseList.loadFromFile("src/project3/courseData.txt");
        learnerList.loadFromFile("src/project3/learnerData.txt");
        
        
        for(Course course : courseList){
            for(Learner learner : learnerList){
                if(learner.getCourse().equals(course.getCode())){
                    course.addLearnerToCourse(learner);
                }
            }
        }
        
        
        
       
        

        do {
            System.out.println("*** MENU ****");
            System.out.println("1. Manage Topic");
            System.out.println("2. Manage Course");
            System.out.println("3. Manage Learner");
            System.out.println("4. Search Information ");
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
                    sc.nextLine(); 

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
                    sc.nextLine();

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
                            courseList.displayCourses();
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
                                sc.nextLine();
                                learnerList.addLearnerToCourse(courseList);
                                System.out.print("Do u want to continue to add Learner? ( Yes or No )");
                                String answer1 = sc.nextLine();
                                
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
                    System.out.println("1. Search topic");
                    System.out.println("2. Search course");
                    System.out.print("Enter your option: ");
                    int choice4 = sc.nextInt();
                    sc.nextLine();
                    switch(choice4){
                        case 1:
                            
                            System.out.print("Enter name: ");
                            String name4 = sc.nextLine();
                            
                            topicList.searchByName(name4);
                            break;
                        case 2:
                            System.out.println("Search By Topic or Name");
                            System.out.print("Enter your option: ");
                            String choice5 = sc.nextLine().trim();
                            if(choice5.equalsIgnoreCase("topic")){
                                System.out.print("Enter topic u want to find: ");
                                String foundTopic = sc.nextLine().trim();
                                courseList.searchByTopic(foundTopic);
                            }
                            else if(choice5.equalsIgnoreCase("name")){
                                System.out.print("Enter name u want to find: ");
                                String foundName = sc.nextLine().trim();
                                courseList.searchByName(foundName);
                            }
                            break;
                            
                    }
                    break;

                case 5:
                    int choice5 ;
                    System.out.println("1.Save Topic");
                    System.out.println("2.Save Course");
                    System.out.println("3.Save Learners");
                    System.out.println("4.Save all");
                    do{
                        System.out.print("Enter your option: ");
                        choice5 = sc.nextInt();
                    }while(choice5 > 4 || choice5 < 1);
                    switch(choice5){
                        case 1:
                            topicList.saveToFile("src/project3/topicData.txt");
                            break;
                        case 2:
                            courseList.saveToFile("src/project3/courseData.txt");
                            break;
                        case 3:
                            learnerList.saveToFile("src/project3/learnerData.txt");
                            break;
                        case 4:
                            topicList.saveToFile("src/project3/topicData.txt");
                            courseList.saveToFile("src/project3/courseData.txt");
                            learnerList.saveToFile("src/project3/learnerData.txt");
                            
                    }
                    System.out.println("Save Successfully");
                    
                    
                    
                    
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
