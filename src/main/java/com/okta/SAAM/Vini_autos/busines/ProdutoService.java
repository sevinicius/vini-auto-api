package com.okta.SAAM.Vini_autos.busines;

import java.util.List;

import org.springframework.stereotype.Service;

import com.okta.SAAM.Vini_autos.infraestructure.entitys.Produto;
import com.okta.SAAM.Vini_autos.infraestructure.repository.ProdutoRepository;

@Service
public class ProdutoService {

    private final ProdutoRepository repository;

    public ProdutoService(ProdutoRepository produtoRepository) {
        this.repository = produtoRepository;

    }

    public void criarProduto(Produto produto) {
        repository.saveAndFlush(produto);
    }

    public List<Produto> listarTodosProdutos() {
        return repository.findAll();
    }

    public Produto buscarProdutoPorNome(String nome){

        return repository.findByNome(nome).orElseThrow(
                () -> new RuntimeException("Nome Não encontrado")
        );
    }

    public void deletarProdutoPorNome (String nome) {
        repository.deleteByNome(nome);
    }

    public void atualizarProdutPorId(Integer id, Produto produto) {
        Produto produtoEntity = repository.findById(id).orElseThrow(() ->
                new RuntimeException("Produto não encontrado"));
        Produto produtoUpdated = Produto.builder()
                .nome(produto.getNome() != null ? produto.getNome() :
                        produtoEntity.getNome())
                .quantidade(produto.getQuantidade() != null ? produto.getQuantidade() :
                        produtoEntity.getQuantidade())
                .descricao(produto.getDescricao() != null ? produto.getDescricao() :
                        produtoEntity.getDescricao())
                .preco(produto.getPreco() != null ? produto.getPreco() :
                        produtoEntity.getPreco())
                .id(produtoEntity.getId())
                .build();

        repository.saveAndFlush(produtoUpdated);
    }

}
