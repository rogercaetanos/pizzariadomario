package com.itb.tcc.mif3an.pizzariadomario.model.repository;

import com.itb.tcc.mif3an.pizzariadomario.model.entity.ItemPedido;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ItemPedidoRepository extends JpaRepository<ItemPedido, Long> {
}

