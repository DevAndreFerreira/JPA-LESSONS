package br.com.estudo.loja.testes;

import br.com.estudo.loja.dao.CategoriaDao;
import br.com.estudo.loja.dao.ProdutoDao;
import br.com.estudo.loja.entity.Categoria;
import br.com.estudo.loja.entity.Produto;
import br.com.estudo.loja.util.JPAUtil;

import javax.persistence.EntityManager;
import java.math.BigDecimal;

public class CadastroProdutos {

/*    Ciclos de vida: Transient: não gerenciado.
    Managed: Gerenciada pela JPA, sincronizada com o BD.
    Detached: persistido, deixou de ser gerenciado.
    Método merge() volta de Detached para Managed (uma nova refência do objeto)
    Ultimo estado: removed
    */

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
        produtoDao.cadastrar(celular); //persist de transiente para managed
        em.flush(); //em.commit() -> salvamos no banco de dados
        em.clear(); //em.close() -> transiente para detached

        celular = em.merge(celular); //merge -> de detached para managed
        celular.setNome("XPTO");
        em.flush();
        em.remove(celular);
        em.flush();


    }

}
