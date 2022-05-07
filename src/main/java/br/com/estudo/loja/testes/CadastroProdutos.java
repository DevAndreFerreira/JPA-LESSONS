package br.com.estudo.loja.testes;

import br.com.estudo.loja.dao.CategoriaDao;
import br.com.estudo.loja.dao.ProdutoDao;
import br.com.estudo.loja.entity.Categoria;
import br.com.estudo.loja.entity.Produto;
import br.com.estudo.loja.util.JPAUtil;

import javax.persistence.EntityManager;
import java.math.BigDecimal;

public class CadastroProdutos {

    public static void main(String[] args) {
        Categoria categoria = new Categoria("Eletronicos");

        Produto celular = new Produto();
        celular.setNome("Xiaomi Redmi");
        celular.setDescricao("TOP");
        celular.setPreco(BigDecimal.TEN);
        celular.setCategoria(categoria);

        EntityManager em = JPAUtil.getEntityManager();

        ProdutoDao produtoDao = new ProdutoDao(em);
        CategoriaDao categoriaDao = new CategoriaDao(em);

        em.getTransaction().begin();
        categoriaDao.cadastrar(categoria);
        produtoDao.cadastrar(celular);
        em.getTransaction().commit();
        em.close();

    }

}
