package controller;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.TypedQuery;

import model.Produto;

import java.util.List;
import javax.persistence.EntityTransaction;

public class ProdutoJpaController {

    private final EntityManagerFactory emf;

    public ProdutoJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }

    private EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public List<Produto> findAll() {
        EntityManager em = getEntityManager();
        try {
            TypedQuery<Produto> query = em.createQuery("SELECT p FROM Produto p", Produto.class);
            return query.getResultList();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public Produto findById(int id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Produto.class, id);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void atualizarQuantidade(int idProduto, int quantidade) {
        EntityManager em = getEntityManager(); // Método para obter o EntityManager
        EntityTransaction tx = em.getTransaction();

        try {
            tx.begin();
            Produto produto = em.find(Produto.class, idProduto);
            if (produto != null) {
                // Atualiza a quantidade do produto
                produto.setQuantidadeEstoque(produto.getQuantidadeEstoque() + quantidade);
                em.merge(produto); // Persiste as alterações
            } else {
                throw new Exception("Produto não encontrado.");
            }
            tx.commit();
        } catch (Exception e) {
            if (tx.isActive()) {
                tx.rollback(); // Desfaz as alterações em caso de erro
            }
            e.printStackTrace();
        } finally {
            em.close(); // Fecha o EntityManager
        }
    }

}
