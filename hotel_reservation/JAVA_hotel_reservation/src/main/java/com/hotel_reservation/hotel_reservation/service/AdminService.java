package com.hotel_reservation.hotel_reservation.service;

import com.hotel_reservation.hotel_reservation.dto.AdminLoginRequest;
import com.hotel_reservation.hotel_reservation.entity.Administrator;
import com.hotel_reservation.hotel_reservation.repository.AdministratorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AdminService {
    private final AdministratorRepository adminRepository;

    // 通过构造器注入 AdminRepository
   //用于管理员登录的服务层次
    @Autowired
    public AdminService(AdministratorRepository adminRepository)
    {
        this.adminRepository = adminRepository;
    }
    public String loginAdmin(AdminLoginRequest request)
    {
        Integer adminId = request.getAdminId();
        String inputPassword = request.getAdminPsw();

        // 查找管理员并比较密码
        Optional<Administrator> adminOptional = adminRepository.findByAdminId(adminId);
        if (adminOptional.isPresent())
        {
            Administrator admin = adminOptional.get();
            if (admin.getAdminPsw().equals(inputPassword))
            {
                return "登录成功";  // 密码正确，返回登录成功
            } else {
                return "密码不正确";  // 密码错误
            }
        } else
        {
            return "没找到管理员";  // 管理员ID不存在
        }
    }
//    根据管理员ID查找管理员并返回（用于管理员登陆后获取管理员的信息）
    public Optional<Administrator> findAdminById(Integer adminId) {
        return adminRepository.findById(adminId);
    }
}
