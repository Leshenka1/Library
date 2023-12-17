package com.zas.DAO;



import com.zas.JPA.LibraryBookJPA;
import com.zas.JPA.LibraryBookJPA_;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.CriteriaUpdate;
import jakarta.persistence.criteria.Root;
import org.apache.logging.log4j.LogManager;

import java.util.Collections;
import java.util.List;

public class LibraryBookDAOJPA {
    private EntityManagerFactory emf = Persistence.createEntityManagerFactory("LibraryUnit");
    public static final org.apache.logging.log4j.Logger logger = LogManager.getRootLogger();
//    public List<LibraryBookJPA> findBooksByAuthor(String author) {
//        EntityManager em = emf.createEntityManager();
//        List<LibraryBookJPA> books = em.createNamedQuery("LibraryBook.findBooksByAuthor", LibraryBookJPA.class)
//                .setParameter("author", author)
//                .getResultList();
//        em.close();
//        return books;
//    }

    public List<LibraryBookJPA> findBooksByAuthor(String author) {
        EntityManager entityManager = emf.createEntityManager();
        try {
            CriteriaBuilder cb = entityManager.getCriteriaBuilder();
            CriteriaQuery<LibraryBookJPA> cq = cb.createQuery(LibraryBookJPA.class);
            Root<LibraryBookJPA> root = cq.from(LibraryBookJPA.class);
            cq.select(root)
                    .where(cb.equal(root.get(LibraryBookJPA_.author), author));
            return entityManager.createQuery(cq).getResultList();
        } catch (Exception e) {
            logger.error("Error finding books by author", e);
            return Collections.emptyList();
        } finally {
            if (entityManager != null) {
                entityManager.close();
            }
        }
    }

//    public int findAvailableCopies(String title) {
//        EntityManager em = emf.createEntityManager();
//        int availableCopies = em.createNamedQuery("LibraryBook.findAvailableCopies", Integer.class)
//                .setParameter("title", title)
//                .getSingleResult();
//        em.close();
//        return availableCopies;
//    }

    public List<Integer> findAvailableCopies(String title) {
        EntityManager entityManager = emf.createEntityManager();
        try {
            CriteriaBuilder cb = entityManager.getCriteriaBuilder();
            CriteriaQuery<Integer> cq = cb.createQuery(Integer.class);
            Root<LibraryBookJPA> root = cq.from(LibraryBookJPA.class);
            cq.select(root.get(LibraryBookJPA_.availableCopies))
                    .where(cb.equal(root.get(LibraryBookJPA_.title), title));
            return entityManager.createQuery(cq).getResultList();
        } catch (Exception e) {
            logger.error("Error finding available copies for book title", e);
            return Collections.emptyList();
        } finally {
            if (entityManager != null) {
                entityManager.close();
            }
        }
    }

//    public void issueBookToReader(int bookID, int readerID) {
//        EntityManager em = emf.createEntityManager();
//        em.getTransaction().begin();
//        int affectedRows = em.createNamedQuery("LibraryBook.issueBookToReader")
//                .setParameter("bookID", bookID)
//                .setParameter("readerID", readerID)
//                .executeUpdate();
//        em.getTransaction().commit();
//        em.close();
//        if (affectedRows == 0) {
//            throw new RuntimeException("Book could not be issued. Either the book is not available or the reader already has a book.");
//        }
//    }

    public void issueBookToReader(int readerID, int bookID, String title) {
        EntityManager entityManager = emf.createEntityManager();
        try {
            entityManager.getTransaction().begin();
            CriteriaBuilder cb = entityManager.getCriteriaBuilder();
            CriteriaUpdate<LibraryBookJPA> cu = cb.createCriteriaUpdate(LibraryBookJPA.class);
            Root<LibraryBookJPA> root = cu.from(LibraryBookJPA.class);
            cu.set(root.get(LibraryBookJPA_.readerID), readerID)
                    .where(
                            cb.and(
                                    cb.equal(root.get(LibraryBookJPA_.bookID), bookID),
                                    cb.greaterThan(root.get(LibraryBookJPA_.availableCopies), 0),
                                    cb.lessThanOrEqualTo(root.get(LibraryBookJPA_.readerID), 0)
                            )
                    );
            entityManager.createQuery(cu).executeUpdate();

            cu.set(root.get(LibraryBookJPA_.availableCopies), cb.diff(root.get(LibraryBookJPA_.availableCopies), 1))
                    .where(
                            cb.and(
                                    cb.equal(root.get(LibraryBookJPA_.title), title),
                                    cb.greaterThan(root.get(LibraryBookJPA_.availableCopies), 0)
                            )
                    );
            entityManager.createQuery(cu).executeUpdate();
            entityManager.getTransaction().commit();
        } catch (Exception e) {
            logger.error("Error issuing book to reader", e);
            if (entityManager.getTransaction().isActive()) {
                entityManager.getTransaction().rollback();
            }
        } finally {
            if (entityManager != null) {
                entityManager.close();
            }
        }
    }

//    public void decreaseAvailableCopies(String title) {
//        EntityManager em = emf.createEntityManager();
//        em.getTransaction().begin();
//        int affectedRows = em.createNamedQuery("LibraryBook.decreaseAvailableCopies")
//                .setParameter("title", title)
//                .executeUpdate();
//        em.getTransaction().commit();
//        em.close();
//        if (affectedRows == 0) {
//            throw new RuntimeException("Could not decrease available copies. Either the book is not available or all copies are already issued.");
//        }
//    }

    public void decreaseAvailableCopies(String title) {
        EntityManager entityManager = emf.createEntityManager();
        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaUpdate<LibraryBookJPA> cu = cb.createCriteriaUpdate(LibraryBookJPA.class);
        Root<LibraryBookJPA> root = cu.from(LibraryBookJPA.class);
        cu.set(root.get(LibraryBookJPA_.availableCopies), cb.diff(root.get(LibraryBookJPA_.availableCopies), 1))
                .where(
                        cb.and(
                                cb.equal(root.get(LibraryBookJPA_.title), title),
                                cb.greaterThan(root.get(LibraryBookJPA_.availableCopies), 0)
                        )
                );
        entityManager.createQuery(cu).executeUpdate();
    }
    // Other methods...
}