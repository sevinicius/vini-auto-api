package com.okta.SAAM.Vini_autos.infraestructure.repository;

import com.okta.SAAM.Vini_autos.infraestructure.entitys.Produto;
import javax.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ProdutoRepository extends JpaRepository<Produto, Integer> {

    Optional<Produto> findByNome(String nome);

    @Transactional
    void deleteByNome(String nome);

}
