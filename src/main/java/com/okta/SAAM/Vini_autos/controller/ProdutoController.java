package com.okta.SAAM.Vini_autos.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.okta.SAAM.Vini_autos.busines.ProdutoService;
import com.okta.SAAM.Vini_autos.infraestructure.entitys.Produto;

import javax.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/produto")
@RequiredArgsConstructor
public class ProdutoController {

    private final ProdutoService produtoService;

    @PostMapping
    public ResponseEntity<?> salvarProduto(@Valid @RequestBody Produto produto) {
        try {
            produtoService.criarProduto(produto);
            return ResponseEntity.status(HttpStatus.CREATED).build();
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Erro ao salvar produto: " + e.getMessage());
        }
    }

    @GetMapping
    public ResponseEntity<List<Produto>> listarTodosProdutos() {
        return ResponseEntity.ok(produtoService.listarTodosProdutos());
    }

    @GetMapping("/buscar")
    public ResponseEntity<?> buscarProdutoPorNome(@RequestParam String nome) {
        try {
            Produto produto = produtoService.buscarProdutoPorNome(nome);
            return ResponseEntity.ok(produto);
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping
    public ResponseEntity<?> deletarProdutoPorNome(@RequestParam String nome) {
        try {
            produtoService.deletarProdutoPorNome(nome);
            return ResponseEntity.ok().build();
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Erro ao deletar produto: " + e.getMessage());
        }
    }

    @PutMapping
    public ResponseEntity<?> atualizarProdutPorId(@RequestParam int id, @Valid @RequestBody Produto produto) {
        try {
            produtoService.atualizarProdutPorId(id, produto);
            return ResponseEntity.ok().build();
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Produto não encontrado");
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Erro ao atualizar produto: " + e.getMessage());
        }
    }
}
