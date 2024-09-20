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
public class Learner {
    String code;
    String name;
    String dateOfBirth;
    String score;
    String course;

    public Learner() {
    }

    public Learner(String code, String name, String dateOfBirth, String score, String course) {
        this.code = code;
        this.name = name;
        this.dateOfBirth = dateOfBirth;
        this.score = score;
        this.course = course;
    }

    public String getCode() {
        return code;
    }

    public String getName() {
        return name;
    }

    public String getDateOfBirth() {
        return dateOfBirth;
    }

    public String getScore() {
        return score;
    }

    public String getCourse() {
        return course;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setDateOfBirth(String dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public void setScore(String score) {
        this.score = score;
    }

    public void setCourse(String course) {
        this.course = course;
    }
    
    
}
