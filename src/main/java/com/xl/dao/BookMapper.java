package com.xl.dao;

import com.xl.vo.Book;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Component;

@Component
public class BookMapper {
    @Cacheable("book")
    public Book getBook(String name){
        simulateSlowService();
        Book book = new Book(name,"书");
        return book;
    }

    private void simulateSlowService() {
        try {
            long time = 3000L;
            Thread.sleep(time);
        } catch (InterruptedException e) {
            throw new IllegalStateException(e);
        }
    }
}
