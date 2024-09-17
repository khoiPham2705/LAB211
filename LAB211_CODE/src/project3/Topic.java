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
public class Topic {
    String code;
    String name;
    String type;
    String title;
    String duration;

    public Topic() {
    }

    public Topic(String code, String name, String type, String title, String duration) {
        this.code = code;
        this.name = name;
        this.type = type;
        this.title = title;
        this.duration = duration;
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

    public String getDuration() {
        return duration;
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

    public void setDuration(String duration) {
        this.duration = duration;
    }

    @Override
    public String toString() {
        return code + "," + name + "," + type + "," + title + "," + duration + '}';
    }
    
    
            
}
