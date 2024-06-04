package riccardogulin.entities;

import jakarta.persistence.*;

@Entity // <-- Dichiariamo che questa classe dovrà essere mappata ad una tabella nel db
@Table(name = "students") // <-- OPZIONALE. Mi serve per personalizzare il nome della tabella
public class Student {
	@Id // OBBLIGATORIA. Serve per stabilire chi sarà la chiave primaria della tabella
	@GeneratedValue // OPZIONALE. Si usa quando si vuol far gestire automaticamente gli id al db.
	// Se non la usassi vorrebbe dire che dovrei essere io manualmente a dover inserire un valore per ogni studente come id
	private long id; // Ad es. se uso un long con @GeneratedValue, mi ritroverò ad avere un bigserial come tipo di dato della colonna

	@Column(name = "nome") // OPZIONALE. Mi serve per customizzare il nome della colonna e per aggiungere dei vincoli ad essa
	private String name;
	@Column(name = "cognome")
	private String surname;
	@Column(name = "tipo_studente")
	@Enumerated(EnumType.STRING) // Di default gli enum vengono trattati come numeri interi nella tabella
	// Se voglio che essi invece vengano rappresentati come testo, devo usare @Enumerated(EnumType.STRING)
	private StudentType studentType;

	public Student() { // <-- OBBLIGATORIO
		// Se voglio che JPA sia in grado di restituirmi i dati del DB sotto forma di oggetti Student, allora è obbligatorio avere il costruttore vuoto
	}

	public Student(String name, String surname, StudentType studentType) {
		this.name = name;
		this.surname = surname;
		this.studentType = studentType;
	}

	public long getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getSurname() {
		return surname;
	}

	public void setSurname(String surname) {
		this.surname = surname;
	}

	public StudentType getStudentType() {
		return studentType;
	}

	public void setStudentType(StudentType studentType) {
		this.studentType = studentType;
	}

	@Override
	public String toString() {
		return "Student{" +
				"id=" + id +
				", name='" + name + '\'' +
				", surname='" + surname + '\'' +
				", studentType=" + studentType +
				'}';
	}
}
