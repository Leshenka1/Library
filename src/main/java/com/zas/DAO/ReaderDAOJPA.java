package com.zas.DAO;

import com.zas.JPA.ReaderJPA;
import com.zas.JPA.ReaderJPA_;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Root;
import org.apache.logging.log4j.LogManager;


import java.util.Collections;
import java.util.List;

public class ReaderDAOJPA {
    private EntityManagerFactory emf = Persistence.createEntityManagerFactory("LibraryUnit");
    public static final org.apache.logging.log4j.Logger logger = LogManager.getRootLogger();

    public List<ReaderJPA> findReadersWithOverdueFees() {
        EntityManager entityManager = emf.createEntityManager();
        try {
            CriteriaBuilder cb = entityManager.getCriteriaBuilder();
            CriteriaQuery<ReaderJPA> cq = cb.createQuery(ReaderJPA.class);
            Root<ReaderJPA> root = cq.from(ReaderJPA.class);
            cq.select(root)
                    .where(
                            cb.and(
                                    cb.equal(root.get(ReaderJPA_.hasOverdueFees), true),
                                    cb.greaterThan(root.get(ReaderJPA_.feesDays), 30)
                            )
                    );
            return entityManager.createQuery(cq).getResultList();
        } catch (Exception e) {
            logger.error("Error finding readers with overdue fees", e);
            return Collections.emptyList();
        } finally {
            if (entityManager != null) {
                entityManager.close();
            }
        }
    }
}