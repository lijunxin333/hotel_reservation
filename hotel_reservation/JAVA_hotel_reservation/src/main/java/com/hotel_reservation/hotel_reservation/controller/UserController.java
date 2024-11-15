package com.hotel_reservation.hotel_reservation.controller;

import com.hotel_reservation.hotel_reservation.dto.RegisterRequest;
import com.hotel_reservation.hotel_reservation.entity.Order;
import com.hotel_reservation.hotel_reservation.entity.User;
import com.hotel_reservation.hotel_reservation.service.OrderService;
import com.hotel_reservation.hotel_reservation.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.*;

import java.util.List;

@RestController
/*接口方法返回对象，这是一个组合注解，表示该类是一个控制器，
 并且所有的方法都将返回JSON格式的数据，而不是视图（例如HTML页面）*/
@RequestMapping("/api/user") //localhost:8088/user/**
/*@RequestMapping("/api/user"): 指定了控制器的根URL路径，
所有方法的请求路径都会基于这个根路径。*/
public class UserController {

    @Autowired
    private UserService userService;
    @Autowired
    private OrderService orderService;
    //用来用户注册的
    @PostMapping("/register")  // 最终路径变为 /api/user/register
    public ResponseEntity<String> register(@RequestBody RegisterRequest request)
    {
        if (userService.registerUser(request))
        {
            return ResponseEntity.ok("注册成功");
        }
        else
        {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("用户已存在");
        }
    }
    //用户登录@RequestBody 将这些请求体中的数据（如 JSON）转换为方法参数中指定的 Java 对象
    @PostMapping("/userLogin")//用来用户登录的
    public ResponseEntity<String> login(@RequestBody RegisterRequest request)
    {
        String loginResult = userService.loginUser(request);
        switch (loginResult) {
            case "登录成功":
                return ResponseEntity.ok("登录成功！");

            case "密码不正确":
                return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("密码不正确");

            case "账户不存在":
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("账户不存在");

            default:
                return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("登录错误，请稍后重试");
        }
    }
    //{userId} 是路径变量的占位符，并不需要$,告诉 Spring 从 URL 中提取这一部分的值并传递给控制器方法。
    // @PathVariable 注解会自动识别并映射它，使用 userId 名称即可。
    @GetMapping("/{userId}")//这是用来在用户界面显示用户信息的
    public ResponseEntity<User> getUserInfo(@PathVariable Integer userId) {
        return ResponseEntity.ok(userService.getUserById(userId));
    }

}
/*@GetMapping("/{id}/orders")
public List<Order> getUserOrders(@PathVariable int id) {
    return userService.getUserOrders(id);
    @GetMapping("/{id}/orders"): 这个注解表示该方法处理GET请求，路径为/api/users/{id}/orders，其中{id}是一个路径变量，表示用户的ID。
@PathVariable int id: 这个注解用于从请求路径中提取用户ID。
方法返回用户的订单列表，调用userService.getUserOrders(id)来获取数据。

    @PostMapping("/reserve")
public Order reserveRoom(@RequestBody ReservationRequest request) {
    return userService.reserveRoom(request);
}
@PostMapping("/reserve"): 这个注解表示该方法处理POST请求，路径为/api/users/reserve。
@RequestBody ReservationRequest request: 这个注解用于将请求体中的JSON数据映射到ReservationRequest对象中。这个对象应该包含预定房间所需
的数据，例如房间号、入住时间、退房时间等。方法调用userService.reserveRoom(request)，并返回预定的订单。
}*/