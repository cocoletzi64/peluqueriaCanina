package org.example.percistencia;
import jakarta.persistence.*;
import org.example.logica.Mascota;

import java.util.List;
public class ControllerMascota {
    private EntityManagerFactory emf = Persistence.createEntityManagerFactory("MiUnidadPersistencia");

    public ControllerMascota() {
        emf = Persistence.createEntityManagerFactory("MiUnidadPersistencia");
    }

    public void create(Mascota mascota) {
        EntityManager em = emf.createEntityManager();
        try {
            // Verificar si ya existe una mascota con el mismo num_cliente
            TypedQuery<Mascota> query = em.createQuery(
                    "SELECT m FROM Mascota m WHERE m.num_cliente = :num_cliente",
                    Mascota.class
            );
            query.setParameter("num_cliente", mascota.getNum_cliente());

            if (!query.getResultList().isEmpty()) {
                System.out.println("La mascota ya existe en la base de datos con ese número de cliente.");
                return;
            }

            em.getTransaction().begin();
            em.persist(mascota);
            em.getTransaction().commit();
            System.out.println("Mascota creada exitosamente.");
        } catch (jakarta.persistence.PersistenceException e) {
            if (e.getCause() instanceof java.sql.SQLIntegrityConstraintViolationException) {
                System.out.println("Error al insertar la mascota: Ya existe una mascota con el mismo número de cliente.");
            } else {
                System.out.println("Error de persistencia: " + e.getMessage());
            }
        } finally {
            em.close();
        }
    }

    public void edit(Mascota mascota) {
        EntityManager em = emf.createEntityManager();
        try {
            em.getTransaction().begin();

            Mascota mascotaExistente = em.find(Mascota.class, mascota.getNum_cliente());
            if (mascotaExistente != null) {
                mascotaExistente.setNombre(mascota.getNombre());
                mascotaExistente.setRaza(mascota.getRaza());
                mascotaExistente.setColor(mascota.getColor());
                mascotaExistente.setAlergico(mascota.getAlergico());
                mascotaExistente.setEspecial(mascota.getEspecial());
                mascotaExistente.setObservaciones(mascota.getObservaciones());
                mascotaExistente.setDuenio(mascota.getDuenio());

                em.getTransaction().commit();
                System.out.println("Mascota editada exitosamente.");
            } else {
                System.out.println("No se encontró la mascota con el número de cliente: " + mascota.getNum_cliente());
            }
        } finally {
            em.close();
        }
    }

    public void destroy(Integer num_cliente) {
        EntityManager em = emf.createEntityManager();
        try {
            em.getTransaction().begin();
            Mascota mascota = em.find(Mascota.class, num_cliente);
            if (mascota != null) {
                em.remove(mascota);
                System.out.println("Mascota eliminada exitosamente.");
            } else {
                System.out.println("No se encontró la mascota con número de cliente: " + num_cliente);
            }
            em.getTransaction().commit();
        } finally {
            em.close();
        }
    }

    public Mascota find(Integer num_cliente) {
        EntityManager em = emf.createEntityManager();
        try {
            return em.find(Mascota.class, num_cliente);
        } finally {
            em.close();
        }
    }

    public List<Mascota> listarTodos() {
        EntityManager em = emf.createEntityManager();
        try {
            return em.createQuery("SELECT m FROM Mascota m", Mascota.class).getResultList();
        } finally {
            em.close();
        }
    }
}
