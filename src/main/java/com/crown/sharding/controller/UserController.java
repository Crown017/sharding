package com.crown.sharding.controller;

import com.crown.sharding.entity.Book;
import com.crown.sharding.entity.Order;
import com.crown.sharding.entity.User;
import com.crown.sharding.mapper.BookMapper;
import com.crown.sharding.mapper.OrderMapper;
import com.crown.sharding.mapper.UserMapper;
import org.apache.shardingsphere.core.strategy.keygen.SnowflakeShardingKeyGenerator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.Date;

@Controller
public class UserController {
    @Resource
    private UserMapper userMapper;

    @Resource
    private BookMapper bookMapper;

    @Resource
    private OrderMapper orderMapper;

    @Resource
    SnowflakeShardingKeyGenerator userKeyGenerator;

    @RequestMapping("/user/save")
    @ResponseBody
    public String save() {
        for (int i = 0; i < 50; i++) {
            Long id = (Long) userKeyGenerator.generateKey();
            User user = new User();
            user.setId(id);
            user.setName("test" + i);
            user.setCityId(i);
            user.setCreateTime(new Date());
            user.setSex(i % 2 == 0 ? 1 : 2);
            user.setPhone("11111111" + i);
            user.setEmail("xxxxx");
            user.setCreateTime(new Date());
            user.setPassword("eeeeeeeeeeee");
            userMapper.save(user);
        }

        return "success";
    }

    @RequestMapping("/user/get/{id}")
    @ResponseBody
    public User get(@PathVariable Long id) {
        User user = userMapper.get(id);
        return user;
    }


    @GetMapping("/book/save")
    @ResponseBody
    public String create(){
        for (int i = 0; i < 10; i++) {
            Book book = new Book();
            book.setBookName("books"+i);
            Long id = (Long) userKeyGenerator.generateKey();
            book.setId(id);
            book.setReleaseTime(new Date());
            book.setPrice(11);

            bookMapper.save(book);
        }
        return "success";
    }


    @GetMapping("/book/{bookId}")
    @ResponseBody
    public Book getBook(@PathVariable("bookId") Long bookId){
        Book book = bookMapper.getBook(bookId);
        return book;
    }


    @GetMapping("/order/createOrder")
    @ResponseBody
    public String createOrder(@RequestParam("userId") Integer userId){
        Order order = new Order();
        order.setOrderId((Long) userKeyGenerator.generateKey());
        order.setUserId(userId);
        orderMapper.save(order);
        return "success";
    }


    @GetMapping("/getOrderInfo/{orderId}")
    @ResponseBody
    public Order getOrderInfo(@PathVariable("orderId") Long order){
        Order orderById = orderMapper.getOrderById(order);
        return orderById;
    }

}
