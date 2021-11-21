package com.example.notes.model;

public class Module {

    private String name , title , post , id  , date  ;


    public Module(String name, String title, String post, String id ,String date) {
        this.name = name;
        this.title = title;
        this.post = post;
        this.id = id;
        this.date = date ;
    }

    public Module() {
    }

    public String getDate() {

        return date;
    }

    public String getName() {
        return name;
    }

    public String getTitle() {
        return title;
    }

    public String getPost() {
        return post;
    }

    public String getId() {
        return id;
    }
}
