package tn.esprit.spring.services;

import java.util.Date;
import java.util.List;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tn.esprit.spring.entities.User;
import tn.esprit.spring.repository.UserRepository;

@Service
public class UserServiceImpl implements IUserService {

	@Autowired
	UserRepository userRepository;

	private static final Logger l = LogManager.getLogger(UserServiceImpl.class);

	@Override
	public List<User> retrieveAllUsers() {
		return userRepository.findAll(); // Implémentation pour récupérer tous les utilisateurs
	}

	@Override
	public User addUser(User u) {
		User utilisateur = null;

		try {
			// Log à ajouter en début de la méthode
			l.info("Ajout d'un utilisateur: " + u.getFirstName() + " " + u.getLastName());
			utilisateur = userRepository.save(u);
			// Log à ajouter à la fin de la méthode
			l.info("Utilisateur ajouté: " + u.getFirstName() + " " + u.getLastName());
		} catch (Exception e) {
			l.error("Erreur dans addUser() : " + e);
		}

		return utilisateur;
	}

	@Override
	public User updateUser(User u) {
		User userUpdated = null;

		try {
			// Log à ajouter en début de la méthode
			l.info("Mise à jour de l'utilisateur: " + u.getFirstName() + " " + u.getLastName());
			userUpdated = userRepository.save(u);
			// Log à ajouter à la fin de la méthode
			l.info("Utilisateur mis à jour: " + u.getFirstName() + " " + u.getLastName());
		} catch (Exception e) {
			l.error("Erreur dans updateUser() : " + e);
		}

		return userUpdated;
	}

	@Override
	public void deleteUser(String id) {
		try {
			// Log à ajouter en début de la méthode
			l.info("Suppression de l'utilisateur avec ID: " + id);
			userRepository.deleteById(Long.parseLong(id));
			// Log à ajouter à la fin de la méthode
			l.info("Utilisateur supprimé avec succès");
		} catch (Exception e) {
			l.error("Erreur dans deleteUser() : " + e);
		}
	}

	@Override
	public User retrieveUser(String id) {
		User u = null;
		try {
			u = userRepository.findById(Long.parseLong(id)).orElse(null);
		} catch (Exception e) {
			l.error("Erreur dans retrieveUser() : " + e);
		}

		return u;
	}

	// Méthode pour vérifier si l'utilisateur est majeur
	public boolean isUserAdult(Long userId) {
		User user = retrieveUser(userId.toString());
		return user != null && user.getDateNaissance().before(new Date(System.currentTimeMillis() - (18L * 365 * 24 * 60 * 60 * 1000))); // 18 ans
	}

	// Méthode pour obtenir le nom complet de l'utilisateur
	public String getUserFullName(Long userId) {
		User user = retrieveUser(userId.toString());
		return user != null ? user.getFirstName() + " " + user.getLastName() : null;
	}
}
