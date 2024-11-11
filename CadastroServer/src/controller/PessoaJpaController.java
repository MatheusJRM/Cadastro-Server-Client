package controller;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.NoResultException;
import javax.persistence.TypedQuery;
import model.Pessoa;
import model.PessoaFisica;
import model.PessoaJuridica;

public class PessoaJpaController {

    private final EntityManagerFactory emf;

    public PessoaJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }

    private EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public Pessoa getByIdPf(int id) {
        EntityManager em = getEntityManager();
        try {
            // Consulta para buscar Pessoa pelo ID
            TypedQuery<PessoaFisica> query = em.createQuery("SELECT pf FROM PessoaFisica pf WHERE pf.id = :id AND pf.idTipoPessoa.id = 1", PessoaFisica.class);
            query.setParameter("id", id);
            return query.getSingleResult(); // Retorna a única Pessoa encontrada
        } catch (NoResultException e) {
            System.out.println("Nenhuma pessoa encontrada com o ID: " + id);
            return null; // Retorna null se não encontrar nenhuma pessoa
        } catch (Exception e) {
            e.printStackTrace(); // Exibe a pilha de erro para outros tipos de exceções
            return null;
        } finally {
            if (em != null) {
                em.close(); // Fecha o EntityManager
            }
        }
    }

    public Pessoa getByIdPj(int id) {
        EntityManager em = getEntityManager();
        try {
            TypedQuery<PessoaJuridica> query = em.createQuery(
                    "SELECT pj FROM PessoaJuridica pj WHERE pj.id = :id AND pj.idTipoPessoa.id = 2",
                    PessoaJuridica.class);
            query.setParameter("id", id);
            return query.getSingleResult();
        } catch (NoResultException e) {
            System.out.println("Nenhuma pessoa jurídica encontrada com o ID: " + id);
            return null; // Retorna null se não encontrar nenhuma pessoa
        } catch (Exception e) {
            e.printStackTrace(); // Exibe a pilha de erro para outros tipos de exceções
            return null;
        } finally {
            if (em != null) {
                em.close(); // Fecha o EntityManager
            }
        }
    }

    public void close() {
        if (emf != null) {
            emf.close();
        }
    }
}
