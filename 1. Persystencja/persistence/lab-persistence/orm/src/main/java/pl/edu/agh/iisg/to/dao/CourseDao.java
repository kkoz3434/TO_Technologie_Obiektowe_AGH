package pl.edu.agh.iisg.to.dao;

import java.util.Optional;

import javax.persistence.PersistenceException;

import pl.edu.agh.iisg.to.model.Course;
import pl.edu.agh.iisg.to.model.Student;

public class CourseDao extends GenericDao<Course> {

	public Optional<Course> create(final String name) {
		try {
			save(new Course(name));
			return findByName(name);
		} catch (PersistenceException e) {
			e.printStackTrace();
		}
		return Optional.empty();
	}

	public Optional<Course> findById(final int id) {
		try {
			Course course = currentSession().createQuery("SELECT c FROM Course c WHERE c.id = :id", Course.class)
					.setParameter("id", id).getSingleResult();
			return Optional.of(course);
		} catch (PersistenceException e) {
			e.printStackTrace();
		}
		return Optional.empty();
	}

	public Optional<Course> findByName(final String name) {
		try {
			Course course = currentSession().createQuery("SELECT c FROM Course c WHERE c.name = :name", Course.class)
					.setParameter("name", name).getSingleResult();
			return Optional.of(course);
		} catch (PersistenceException e) {
			e.printStackTrace();
		}
		return Optional.empty();
	}

	public boolean enrollStudent(final Course course, final Student student) {
		// TODO implement
		return false;
	}
}
