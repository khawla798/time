package tn.esprit.spring.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;
import org.springframework.data.repository.query.Param;

import tn.esprit.spring.entities.User;

import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    // Rechercher par prénom
    List<User> findByFirstName(String fname);

    // Rechercher par nom de famille
    List<User> findByLastName(String lname);

    // Rechercher les utilisateurs dont l'ID est supérieur à une valeur donnée
    List<User> findByIdGreaterThan(Long id);

    // Récupérer le plus grand ID (JPQL)
    @Query("SELECT MAX(e.id) FROM User e")
    Long getMaxId();

    // Mettre à jour le rôle d'un utilisateur par son prénom (JPQL)
    @Modifying
    @Query("UPDATE User u SET u.role = :role WHERE u.firstName = :fname")
    int updateUserStatusForFirstName(@Param("role") String role, @Param("fname") String fname);

    // Mise à jour de l'état d'un utilisateur par son nom (requête native)
    @Modifying
    @Query(value = "UPDATE T_USER u SET u.role = ? WHERE u.lastName = ?", nativeQuery = true)
    int updateUserStatusForLastName(String role, String lastName);

    // Insérer un nouvel utilisateur (requête native)
    @Modifying
    @Query(value = "INSERT INTO T_USER (firstName, lastName, role) VALUES (:fn, :ln, :role)", nativeQuery = true)
    void insertUser(@Param("fn") String firstName, @Param("ln") String lastName, @Param("role") String role);
}
