package com.itb.tcc.mif3an.pizzariadomario.model.repository;


import com.itb.tcc.mif3an.pizzariadomario.model.entity.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ClienteRepository extends JpaRepository<Cliente, Long> {
}
