/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package project3;

import java.util.ArrayList;

/**
 *
 * @author DELL
 */
public class Course {
    String code;
    String name;
    String type;
    String title;
    String beginDate;
    String endDate;
    String tuitionFee;
    String topic;
    public int maxLearners;
    public ArrayList<Learner> learners;

    public Course() {
    this.learners = new ArrayList<>(); // Khởi tạo danh sách học viên
}

    public Course(String code, String name, String type, String title, String beginDate, String endDate, String tuitionFee, String topic, int maxLearners) {
        this.code = code;
        this.name = name;
        this.type = type;
        this.title = title;
        this.beginDate = beginDate;
        this.endDate = endDate;
        this.tuitionFee = tuitionFee;
        this.topic = topic;
        this.maxLearners = maxLearners;
        this.learners = new ArrayList<>(); // Khởi tạo danh sách học viên
    }
    public  int getMaxLearners() {
        return maxLearners;
    }

    public ArrayList<Learner> getLearners() {
        return learners;
    }
    public boolean addLearnerToCourse(Learner learner) {
        if (learners.size() < maxLearners) {
            learners.add(learner);
            return true; // Learner added successfully
        } else {
            System.out.println("Cannot add learner. Maximum number of learners reached.");
            return false; // Learner not added
        }
    }

    public String getCode() {
        return code;
    }

    public String getName() {
        return name;
    }

    public String getType() {
        return type;
    }

    public String getTitle() {
        return title;
    }

    public String getBeginDate() {
        return beginDate;
    }

    public String getEndDate() {
        return endDate;
    }

    public String getTuitionFee() {
        return tuitionFee;
    }

    public String getTopic() {
        return topic;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setType(String type) {
        this.type = type;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setBeginDate(String beginDate) {
        this.beginDate = beginDate;
    }

    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }

    public void setTuitionFee(String tuitionFee) {
        this.tuitionFee = tuitionFee;
    }

    public void setTopic(String topic) {
        this.topic = topic;
    }

    public void setMaxLearners(int maxLearners) {
        this.maxLearners = maxLearners;
    }

    
    

    @Override
    public String toString() {
        return  code + "," + name + "," + type + "," + title + "," + beginDate + "," + endDate + "," + tuitionFee + "," + topic +  "," +maxLearners ;
    }
    
    
}
