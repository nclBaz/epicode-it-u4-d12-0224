package riccardogulin.dao;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import riccardogulin.entities.Student;
import riccardogulin.exceptions.NotFoundException;

public class StudentsDAO {
	// DAO (Data Access Object) è un Design Pattern. Questa classe che serve per semplificare l'interazione col db, nascondendo i dettagli implementativi
	// dei metodi che accederanno al db, che in alcuni potrebbero essere anche abbastanza complessi. Nascondendoli quindi forniremo al main
	// e a chiunque abbia bisogno di interagire con la tabella degli studenti, dei metodi semplici e pratici da utilizzare, magari anche con dei
	// nomi appropriati e "parlanti"
	private final EntityManager em;

	public StudentsDAO(EntityManager em) {
		this.em = em;
	}

	public void save(Student student) {
		// 0. Chiedo all'Entity Manager di creare una nuova transazione
		EntityTransaction transaction = em.getTransaction();
		// 1. Inizio la transazione
		transaction.begin();
		// 2. Aggiungo lo studente al Persistence Context (in questo momento lo studente non è ancora salvato nel db)
		em.persist(student);
		// 3. Concludo la transazione, ottenendo l'effettivo salvataggio nel db del nuovo studente
		transaction.commit();
		System.out.println("Lo studente " + student.getSurname() + " è stato correttamente salvato nel db!");

	}

	public Student findById(long studentId) {
		Student student = em.find(Student.class, studentId); // Primo parametro è la classe dell'entity, secondo è l'id da cercare
		if (student == null) throw new NotFoundException(studentId);
		return student;
	}

	public void findByIdAndDelete(long studentId) {
		// 0. Cerco lo studente nel DB
		Student found = this.findById(studentId);

		// 1. Chiedo all'Entity Manager di creare una transazione
		EntityTransaction transaction = em.getTransaction();
		// 2. Inizio la transazione
		transaction.begin();
		// 3. Rimuovo lo studente dal Persistence Context (in questo momento non è ancora effettivamente stato cancellato)
		em.remove(found);
		// 4. Concludo la transazione, ottenendo l'effettiva eliminazione
		transaction.commit();
		System.out.println("Lo studente " + found.getSurname() + " è stato correttamente eliminato dal db!");

	}
}
