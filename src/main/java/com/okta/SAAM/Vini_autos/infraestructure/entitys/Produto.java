package com.okta.SAAM.Vini_autos.infraestructure.entitys;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Table( name = "produto")
@Entity
public class Produto {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    @NotBlank(message = "Nome é obrigatório")
    @Column(name = "nome", unique = true)
    private String nome;

    @NotBlank(message = "Quantidade é obrigatória")
    @Column(name = "quantidade")
    private String quantidade;

    @NotBlank(message = "Descrição é obrigatória")
    @Column(name = "descricao")
    private String descricao;

    @NotNull(message = "Preço é obrigatório")
    @Positive(message = "Preço deve ser positivo")
    @Column(name = "preco")
    private Double preco;

}
