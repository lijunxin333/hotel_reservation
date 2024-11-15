package com.hotel_reservation.hotel_reservation.repository;

import com.hotel_reservation.hotel_reservation.entity.Administrator;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AdministratorRepository extends JpaRepository<Administrator,Integer> {
    Optional<Administrator> findByAdminId(Integer adminId);


}
