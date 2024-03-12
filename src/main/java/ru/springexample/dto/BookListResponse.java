package ru.springexample.dto;

import java.util.ArrayList;
import java.util.Collection;

import ru.springexample.bookmanager.Book;

public class BookListResponse {
    private ArrayList<Book> books;

    public BookListResponse(Collection<Book> books){
        this.books = new ArrayList<>();
        this.books.addAll(books);
    }

    public ArrayList<Book> getBooks() {
        return books;
    }

    
}
