package org.example.percistencia;
import jakarta.persistence.*;
import org.example.logica.Duenio;

import java.util.List;

public class ControllerDuenio {
    private EntityManagerFactory emf = Persistence.createEntityManagerFactory("MiUnidadPersistencia");

    public ControllerDuenio() {
        emf = Persistence.createEntityManagerFactory("MiUnidadPersistencia");
    }

    public void create(Duenio duenio) {
        EntityManager em = emf.createEntityManager();
        try {
            // Verificar si ya existe un dueño con el mismo ID
            TypedQuery<Duenio> query = em.createQuery(
                    "SELECT d FROM Duenio d WHERE d.id_duenio = :id_duenio",
                    Duenio.class
            );
            query.setParameter("id_duenio", duenio.getId_duenio());

            if (!query.getResultList().isEmpty()) {
                System.out.println("El dueño ya existe en la base de datos con ese ID.");
                return;
            }

            em.getTransaction().begin();
            em.persist(duenio);
            em.getTransaction().commit();
            System.out.println("Dueño creado exitosamente.");
        } catch (jakarta.persistence.PersistenceException e) {
            if (e.getCause() instanceof java.sql.SQLIntegrityConstraintViolationException) {
                System.out.println("Error al insertar el dueño: Ya existe un dueño con el mismo ID.");
            } else {
                System.out.println("Error de persistencia: " + e.getMessage());
            }
        } finally {
            em.close();
        }
    }

    public void edit(Duenio duenio) {
        EntityManager em = emf.createEntityManager();
        try {
            em.getTransaction().begin();

            Duenio duenioExistente = em.find(Duenio.class, duenio.getId_duenio());
            if (duenioExistente != null) {
                duenioExistente.setNombre(duenio.getNombre());
                duenioExistente.setCel(duenio.getCel());

                em.getTransaction().commit();
                System.out.println("Dueño editado exitosamente.");
            } else {
                System.out.println("No se encontró el dueño con el ID: " + duenio.getId_duenio());
            }
        } finally {
            em.close();
        }
    }

    public void destroy(Integer id_duenio) {
        EntityManager em = emf.createEntityManager();
        try {
            em.getTransaction().begin();
            Duenio duenio = em.find(Duenio.class, id_duenio);
            if (duenio != null) {
                em.remove(duenio);
                System.out.println("Dueño eliminado exitosamente.");
            } else {
                System.out.println("No se encontró el dueño con ID: " + id_duenio);
            }
            em.getTransaction().commit();
        } finally {
            em.close();
        }
    }

    public Duenio find(Integer id_duenio) {
        EntityManager em = emf.createEntityManager();
        try {
            return em.find(Duenio.class, id_duenio);
        } finally {
            em.close();
        }
    }

    public List<Duenio> listarTodos() {
        EntityManager em = emf.createEntityManager();
        try {
            return em.createQuery("SELECT d FROM Duenio d", Duenio.class).getResultList();
        } finally {
            em.close();
        }
    }
}
