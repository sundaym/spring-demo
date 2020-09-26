package com.demo.dao;

import com.demo.entity.Book;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class BookDaoImpl implements BookDao {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    /**
     * add book
     * @param book
     */
    public void addBook(Book book) {
        String sql = "INSERT INTO book VALUES (?,?,?)";
        Object[] args = {book.getBookId(), book.getBookName(), book.getBookAuthor()};
        jdbcTemplate.update(sql, args);
    }

    @Override
    public void updateBook(Book book) {
        String sql = "UPDATE book SET book_name=?,book_author=? WHERE book_id=?";
        Object[] args = {book.getBookName(), book.getBookAuthor(), book.getBookId()};
        jdbcTemplate.update(sql, args);
    }

    @Override
    public void deleteBook(Integer id) {
        String sql = "DELETE FROM book WHERE book_id=?";
        jdbcTemplate.update(sql, id);
    }
}
