package com.hotel_reservation.hotel_reservation.service;

import com.hotel_reservation.hotel_reservation.dto.RegisterRequest;
import com.hotel_reservation.hotel_reservation.entity.User;
import com.hotel_reservation.hotel_reservation.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

//服务类处理注册服务
@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;
    public boolean registerUser(RegisterRequest request)
    {
        // 检查用户是否已存在
        if (userRepository.findByUserID(request.getUserID()).isPresent())
        {
            return false; // 用户已存在
        }
        // 创建新用户并保存
        User user = new User();
        user.setUserIdCard(request.getUserIdCard());
        user.setUserPsw(request.getuserPsw()); // 直接保存密码
        user.setUserName(request.getUserName());
        user.setUserTel(request.getUserTel());
        userRepository.save(user);
        return true;
    }
    public String loginUser(RegisterRequest request) {
        Integer userId = request.getUserID();
        String inputPassword = request.getuserPsw();
//        返回String类型
        Optional<String> storedPasswordOpt = userRepository.findPswByUserID(userId);
        // 如果用户存在且密码匹配，则返回 true
        if (storedPasswordOpt.isEmpty()) {
            // 用户不存在
            return "账户不存在";
        }
        String storedPassword = storedPasswordOpt.get();
        if (storedPassword.equals(inputPassword)) {
            // 密码正确
            return "登录成功";
        } else {
            // 密码不正确
            return "密码不正确";
        }
    }
    public User getUserById(Integer userId) {
        return userRepository.findByUserID(userId).orElse(null);
    }
}
