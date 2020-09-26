package com.demo;

import com.demo.entity.Book;
import com.demo.service.BookService;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.ArrayList;
import java.util.List;

public class SpringTest {
    private static final ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");

    @Test
    public void testSpring() {
    }

    @Test
    public void testJdbcTemplateInsert() {
        BookService bookService = (BookService) context.getBean("bookService");
        Book book = new Book();
        book.setBookId(4);
        book.setBookName("GitHub");
        book.setBookAuthor("GitHub");
        bookService.addBook(book);
    }

    @Test
    public void testJdbcTemplateUpdate() {
        BookService bookService = (BookService) context.getBean("bookService");
        Book book = new Book();
        book.setBookAuthor("Microsoft");
        book.setBookName("GitHub");
        book.setBookId(4);
        bookService.updateBook(book);
    }

    @Test
    public void testJdbcTemplateQuery() {
        JdbcTemplate jdbcTemplate = (JdbcTemplate) context.getBean("jdbcTemplate");

        // 1.查询返回记录数
        String sql1 = "SELECT COUNT(*) FROM book WHERE 1=1";
        Integer count = jdbcTemplate.queryForObject(sql1, Integer.class);
        System.out.println("book表记录数:" + count);
        // 2.查询返回对象
        String sql2 = "SELECT * FROM book where book_id=?";
        Book book = jdbcTemplate.queryForObject(sql2, new BeanPropertyRowMapper<>(Book.class), 3);
        System.out.println(book);

        // 3.查询返回集合,分页
        String sql3 = "SELECT * FROM book";
        List<Book> bookList = jdbcTemplate.query(sql3, new BeanPropertyRowMapper<>(Book.class));
        System.out.println(bookList);
    }

    /**
     * 批量操作
     */
    @Test
    public void testJdbcTemplateBatch() {
        JdbcTemplate jdbcTemplate = (JdbcTemplate) context.getBean("jdbcTemplate");
        String sql = "INSERT INTO book VALUES (?,?,?)";
        Object[] args1 = {6, "Spring", "Spring"};
        Object[] args2 = {7, "Bose", "Bose"};
        List ls = new ArrayList();
        ls.add(args1);
        ls.add(args2);
        jdbcTemplate.batchUpdate(sql, ls);
    }

    @Test
    public void testTx() {

    }
}
