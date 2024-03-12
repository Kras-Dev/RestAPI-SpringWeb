package ru.springexample.bookmanager.controller;

import java.util.HashMap;
import java.util.Optional;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import ru.springexample.bookmanager.Book;
import ru.springexample.bookmanager.BookStorage;
import ru.springexample.dto.BookCreateOrUpdateRequest;
import ru.springexample.dto.BookIdResponce;
import ru.springexample.dto.BookListResponse;

// Контроллер для управления книгами
@RestController
public class BookController {
    // Метод для создания новой книги
    @PostMapping(path="/book")
    public BookIdResponce createBook(@RequestBody BookCreateOrUpdateRequest request){//(@RequestParam String name){
        // Создание новой книги на основе данных из запроса
        Book book = new Book(request.getName(), request.getYear(), request.getId());
        // Добавление книги в хранилище и получение ее id
        int id = BookStorage.addBook(book, request.getId());
        HashMap<String, Integer> response = new HashMap<>();
        response.put("id", id);
        // Формирование ответа с id созданной книги
        return new BookIdResponce(id);
    }
    // Метод для получения списка всех книг
    @GetMapping(path="/book")
    public BookListResponse getBookList(){
        return new BookListResponse(BookStorage.getBookList());
    }
    // Метод для получения информации о книге по ее id
    @GetMapping(path="/book/{id}")
    public ResponseEntity<Book> getBook(@PathVariable Integer id){
        // Поиск книги по id
        Optional<Book> book = BookStorage.getBook(id);
        // Возвращение найденной книги или статуса NOT_FOUND, если книга не была найдена
        return book.isPresent() ?
            new ResponseEntity<>(book.get(), HttpStatus.OK) :
            new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
    // Метод для удаления книги по ее id
    @DeleteMapping(path="/book/{id}")
    public void deleteBook(@PathVariable Integer id){
        BookStorage.deleteBook(id);
    }
    // Метод для обновления информации о книге по ее id
    @PutMapping(path="/book/{id}")
    public void updateBook(@PathVariable Integer id, @RequestBody BookCreateOrUpdateRequest request){
        // Создание новой версии книги на основе данных из запроса и обновление информации
        Book book = new Book(request.getName(), request.getYear(), request.getId());
        BookStorage.updateBook(id, book);
    }

}
