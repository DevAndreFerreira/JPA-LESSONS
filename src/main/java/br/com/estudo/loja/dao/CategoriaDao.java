package br.com.estudo.loja.dao;

import br.com.estudo.loja.entity.Categoria;
import br.com.estudo.loja.entity.Produto;

import javax.persistence.EntityManager;

public class CategoriaDao {

    private EntityManager em;

    public CategoriaDao(EntityManager em) {
        this.em = em;
    }

    public void cadastrar(Categoria categoria) {
        this.em.persist(categoria);
    }

}
