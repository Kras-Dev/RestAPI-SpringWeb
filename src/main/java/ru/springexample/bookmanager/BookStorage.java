package ru.springexample.bookmanager;

import java.util.Collection;
import java.util.HashMap;
import java.util.Optional;

public class BookStorage {

    private static int id = 0;
    private static HashMap<Integer, Book> bookList = new HashMap<>();
    // Метод для добавления новой книги
    public static int addBook(Book book){
        // Генерация нового уникального id для книги и добавление ее в хранилище
        int newId = ++id;
        book.setId(newId);
        bookList.put(newId, book);
        return newId;
    }
    // Перегруженный метод для добавления новой книги с указанным id
    public static int addBook(Book book, Integer specifiedId){
        // Проверка наличия указанного id или генерация нового id
        int newId = (specifiedId != null && specifiedId > 0) ? specifiedId : ++id;
        book.setId(newId);
        bookList.put(newId, book);
        return newId;
    }
    // Метод для получения списка всех книг
    public static Collection<Book> getBookList(){
        return bookList.values();
    }
    // Метод для удаления книги по id
    public static void deleteBook(int id){
        bookList.remove(id);
    }
    // Метод для обновления информации о книге по id
    public static void updateBook(int id, Book book){
        book.setId(id);
        bookList.put(id, book);        
    }
    // Метод для получения книги по id
    public static Optional<Book> getBook(int id){
        return bookList.containsKey(id) ?
            Optional.of(bookList.get(id)) :
            Optional.empty();
    }
}
