package controller;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.TypedQuery;

import model.Produto;

import java.util.List;

public class ProdutoJpaController {

    private final EntityManagerFactory emf;

    public ProdutoJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }

    // Método para listar todos os produtos
    public List<Produto> findAll() {
        EntityManager em = null;
        try {
            em = emf.createEntityManager();
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

    // Método para encontrar um produto pelo ID
    public Produto findById(Long id) {
        EntityManager em = null;
        try {
            em = emf.createEntityManager();
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
}
