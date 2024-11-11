package controller;

import model.MovimentacaoCompra;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

public class MovimentacaoCompraJpaController {

    private final EntityManagerFactory emf;

    public MovimentacaoCompraJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }

    public void create(MovimentacaoCompra movimentacaoCompra) {
        EntityManager em = emf.createEntityManager();
        try {
            em.getTransaction().begin();
            em.persist(movimentacaoCompra);
            em.getTransaction().commit();
        } catch (Exception e) {
            if (em.getTransaction().isActive()) {
                em.getTransaction().rollback();
            }
            throw e;
        } finally {
            em.close();
        }
    }
}
