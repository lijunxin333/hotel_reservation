package com.hotel_reservation.hotel_reservation.entity;

import jakarta.persistence.*;

@Table(name="Administrator")
@Entity
public class Administrator
{
    @Id
    @Column(name = "Admin_ID")
    private Integer adminId;
    @Column(name = "Admin_psw")
    private String adminPsw;
    @Column(name = "Admin_name")
    private String adminName;
    @Enumerated(EnumType.STRING)
    @Column(name = "Admin_Role")
    private AdminRole adminRole = AdminRole.Manager;  // 管理员角色，默认接待
    // 枚举类型表示管理员角色
    public enum AdminRole {
        Manager, Receptionist, Cleaner
    }

    public Integer getAdminId() {
        return adminId;
    }

    public void setAdminId(Integer adminId) {
        this.adminId = adminId;
    }

    public String getAdminPsw() {
        return adminPsw;
    }

    public void setAdminPsw(String adminPsw) {
        this.adminPsw = adminPsw;
    }

    public String getAdminName() {
        return adminName;
    }

    public void setAdminName(String adminName) {
        this.adminName = adminName;
    }

    public AdminRole getAdminRole() {
        return adminRole;
    }

    public void setAdminRole(AdminRole adminRole) {
        this.adminRole = adminRole;
    }

    @Override
    public String toString() {
        return "Administrator{" +
                "adminId=" + adminId +
                ", adminPsw='" + adminPsw + '\'' +
                ", adminName='" + adminName + '\'' +
                ", adminRole=" + adminRole +
                '}';
    }
}
