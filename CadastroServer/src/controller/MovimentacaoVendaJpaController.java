package controller;

import model.MovimentacaoVenda;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

public class MovimentacaoVendaJpaController {

    private final EntityManagerFactory emf;

    public MovimentacaoVendaJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }

    public void create(MovimentacaoVenda movimentacaoVenda) {
        EntityManager em = emf.createEntityManager();
        try {
            em.getTransaction().begin();
            em.persist(movimentacaoVenda);
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
