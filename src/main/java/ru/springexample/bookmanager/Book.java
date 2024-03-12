package ru.springexample.bookmanager;

public class Book {
    private final String name;
    private int id;
    private int year;
    

    public String getName() {
        return name;
    }    

    public Book(String name, int year, int id) {
        this.name = name;
        this.year = year;
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    
    
}
