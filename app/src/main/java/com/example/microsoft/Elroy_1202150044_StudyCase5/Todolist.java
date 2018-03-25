package com.example.microsoft.Elroy_1202150044_StudyCase5;

/**
 * Created by 3D on 3/25/2018.
 */

public class Todolist {

    //Deklarasi variable
    String id;
    private String name, desc, priority;

    //Konstruktor dari class Todolist
    public Todolist() {
        this.id = id;
        this.name = name;
        this.desc = desc;
        this.priority = priority;
    }
    //Setter dan getter
    public String getId() {

        return id;
    }

    public void setId(String id) {

        this.id = id;
    }

    public String getName() {

        return name;
    }

    public void setName(String name) {

        this.name = name;
    }

    public String getDesc() {

        return desc;
    }

    public void setDesc(String desc) {

        this.desc = desc;
    }

    public String getPriority() {

        return priority;
    }

    public void setPriority(
            String priority) {
        this.priority = priority;
    }
}

