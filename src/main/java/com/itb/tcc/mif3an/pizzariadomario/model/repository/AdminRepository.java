package com.itb.tcc.mif3an.pizzariadomario.model.repository;


import com.itb.tcc.mif3an.pizzariadomario.model.entity.Admin;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AdminRepository extends JpaRepository<Admin, Long> {
}
