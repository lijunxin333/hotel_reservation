package com.hotel_reservation.hotel_reservation.repository;

import com.hotel_reservation.hotel_reservation.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

//方便完成对数据库的常见增删查改方法
@Repository//接口，可以有自己的方法，也可以继承父类的方法，对应的实体类和主键
public interface UserRepository extends JpaRepository<User,Integer> {
    Optional<User> findByUserID(Integer userID);

   //查找用户如果存在并返回密码，否则返回空
    @Query("SELECT u.userPsw FROM User u WHERE u.userID = :userId")
    Optional<String>findPswByUserID(@Param("userId") Integer userId);
    //如果找到该用户 ID，返回其密码；否则返回 Optional.empty()
}

/*拥有的方法
查找所有用户：userRepository.findAll()
保存或更新用户：userRepository.save(user)
通过 ID 删除用户：userRepository.deleteById(userId)
通过用户名查找用户：userRepository.findByUserName("Alice")
具体用法
 */