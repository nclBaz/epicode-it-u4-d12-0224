package riccardogulin;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import riccardogulin.dao.StudentsDAO;
import riccardogulin.entities.Student;
import riccardogulin.entities.StudentType;
import riccardogulin.exceptions.NotFoundException;

public class Application {
	private static final EntityManagerFactory emf = Persistence.createEntityManagerFactory("u4d12");
	// Il parametro di createEntityManagerFactory DEVE corrispondere precisamente al nome del persistence-unit dichiarato nel persistence.xml
	// L'EntityManagerFactory ci consente di creare gli EntityManager, ovvero quegli oggetti che consento di interagire col db

	public static void main(String[] args) {
		EntityManager em = emf.createEntityManager();
		StudentsDAO sd = new StudentsDAO(em);

		// *********************************************** SAVE ********************************************

		Student aldo = new Student("Aldo", "Baglio", StudentType.BACKEND);
		Student giovanni = new Student("Giovanni", "Storti", StudentType.FULLSTACK);
		Student giacomo = new Student("Giacomo", "Poretti", StudentType.FRONTEND);
		// Quando creiamo una nuova istanza della classe Student (che è una entity), NON stiamo aggiungendo automaticamente una nuova riga
		// alla tabella students. Il processo è più complicato di così. Bisognerà prima di tutto prendere questo oggetto e renderlo
		// parte del PERSISTENCE CONTEXT, per poi una volta pronti decidere di chiedere al db di salvarlo definitivamente.
/*		sd.save(aldo);
		sd.save(giovanni);
		sd.save(giacomo);*/

		// *********************************************** FIND BY ID ********************************************
		try {
			Student aldoFromDB = sd.findById(10);
			System.out.println(aldoFromDB.getSurname());

		} catch (NotFoundException ex) {
			System.out.println(ex.getMessage());
		}

		// *********************************************** FIND BY ID AND DELETE ********************************************
		try {
			sd.findByIdAndDelete(1);
		} catch (NotFoundException ex) {
			System.out.println(ex.getMessage());

		}

		// Anche se nelle nostre applicazioni non è richiesto, sarebbe bene come best practice alla fine di tutto chiudere entity manager & factory
		em.close();
		emf.close();
	}
}
