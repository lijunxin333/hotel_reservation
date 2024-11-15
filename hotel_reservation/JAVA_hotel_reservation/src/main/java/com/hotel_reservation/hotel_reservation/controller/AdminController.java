package com.hotel_reservation.hotel_reservation.controller;

import com.hotel_reservation.hotel_reservation.dto.AdminLoginRequest;
import com.hotel_reservation.hotel_reservation.entity.Administrator;
import com.hotel_reservation.hotel_reservation.service.AdminService;
import com.hotel_reservation.hotel_reservation.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
//使用 @RestController 的类会自动将所有方法的返回值序列化为
// JSON 或 XML 格式，并作为 HTTP 响应体返回给客户端
@RestController
@RequestMapping("/api/admin")  // 控制器的请求路径
public class AdminController {
//防止更改
    private final AdminService adminService;
    private final OrderService orderService;

    // 通过构造器注入 AdminService
    @Autowired
    public AdminController(AdminService adminService,OrderService orderService) {
        this.adminService = adminService;
        this.orderService=orderService;
    }
    //@ResponseBody 是 Spring MVC 中的一个注解，
    // 主要用于将控制器方法的返回值直接作为 HTTP 响应体返回
    //管理员登录
    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody AdminLoginRequest request) {
        // 调用 AdminService 中的 loginAdmin 方法进行登录验证
        String result = adminService.loginAdmin(request);

        // 判断登录结果并返回相应的 ResponseEntity
        if ("登录成功".equals(result))
        {
            return ResponseEntity.ok("登录成功");
               }
        else if ("密码不正确".equals(result))
        {
            return ResponseEntity.status(400).body("密码不正确");
        }
        else if ("没找到管理员".equals(result))
        {
            return ResponseEntity.status(404).body("管理员ID不存在");
        }
        else
        {
            return ResponseEntity.status(500).body("系统错误，请稍后再试");
        }
    }
    // 通过管理员ID获取管理员信息
    @GetMapping("/{adminId}/profile")
    public ResponseEntity<Administrator> getAdminProfile(@PathVariable Integer adminId) {
        Optional<Administrator> adminOptional = adminService.findAdminById(adminId);

        if (adminOptional.isPresent()) {
            return ResponseEntity.ok(adminOptional.get());
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }
    // 管理员手动确认入住
    @PostMapping("/order/{orderNum}/checkin")
    public ResponseEntity<String> confirmCheckIn(@PathVariable Long orderNum) {
        return orderService.confirmCheckIn(orderNum);
    }
    // 管理员手动标记为已支付
    @PostMapping("/order/{orderNum}/pay")
    public ResponseEntity<String> markAsPaid(@PathVariable Long orderNum) {
        return orderService.markAsPaid(orderNum);
    }
    // 确认退房
    @PostMapping("/order/{orderNum}/checkout")
    public ResponseEntity<String> confirmCheckOut(@PathVariable Long orderNum) {
        return orderService.confirmCheckOut(orderNum);
    }

}
