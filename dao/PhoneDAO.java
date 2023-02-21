package dao;

import java.util.List;

import javax.persistence.NoResultException;

import org.hibernate.Session;
import org.hibernate.query.Query;
import db.HibernateUtils;
import model.Phone;

public class PhoneDAO {
	Session session = HibernateUtils.getFactory().openSession();

	public boolean add(Phone phone) {
		try {
			session.getTransaction().begin();
			session.save(phone);
			session.getTransaction().commit();
			session.close();
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			session.getTransaction().rollback();
			return false;
		}
	}

	public Phone get(int id) {
		try {
			session.getTransaction().begin();
			Phone phone = session.get(Phone.class, String.valueOf(id));
			session.getTransaction().commit();
			session.close();
			return phone;
		} catch (Exception e) {
			e.printStackTrace();
			session.getTransaction().rollback();
			return null;
		}
	}

	public List<Phone> getAll() {
		try {
			session.getTransaction().begin();
			String hql = "FROM Phone";
			List<Phone> phones = session.createQuery(hql, Phone.class).getResultList();
			session.getTransaction().commit();
			session.close();
			return phones;
		} catch (Exception e) {
			e.printStackTrace();
			session.getTransaction().rollback();
			return null;
		}
	}

	public boolean remove(int id) {
		try {
			session.getTransaction().begin();
			Phone phone = session.get(Phone.class, String.valueOf(id));
			if (phone != null) {
				session.delete(phone);
				session.getTransaction().commit();
				return true;
			}
			return false;
		} catch (Exception e) {
			e.printStackTrace();
			session.getTransaction().rollback();
			session.close();
			return false;
		}
	}

	public boolean remove(Phone p) {
		try {
			session.getTransaction().begin();
			Phone phone = session.get(Phone.class, String.valueOf(p.getId()));
			if (phone != null) {
				session.delete(phone);
				session.getTransaction().commit();
				return true;
			}
			return false;
		} catch (Exception e) {
			e.printStackTrace();
			session.getTransaction().rollback();
			return false;
		}
	}

	public boolean update(Phone p) {
		try {
			session.getTransaction().begin();
			String hql = "UPDATE Phone SET Name = :name, Price = :price, Color = :color, Country = :country, Quantity = :quantity WHERE Id = :id";
			Query<Phone> query = session.createQuery(hql);

			query.setParameter("id", p.getId());
			query.setParameter("name", p.getName());
			query.setParameter("price", p.getPrice());
			query.setParameter("color", p.getColor());
			query.setParameter("country", p.getCountry());
			query.setParameter("quantity", p.getQuantity());

			int result = query.executeUpdate();
			session.getTransaction().commit();
			session.close();
			return result > 0;
		} catch (Exception e) {
			e.printStackTrace();
			session.getTransaction().rollback();
			return false;
		}
	}

	public Phone getPhoneWithHighestPrice() {
		try {
			session.getTransaction().begin();
			String hql = "FROM Phone ORDER BY Price DESC";
			Query<Phone> query = session.createQuery(hql, Phone.class);
			query.setMaxResults(1);
			Phone result = query.getSingleResult();
			session.getTransaction().commit();
			session.close();
			return result;
		} catch (Exception e) {
			e.printStackTrace();
			session.getTransaction().rollback();
			return null;
		}
	}

	public List<Phone> getPhonesSortedByCountry() {
		try {
			session.getTransaction().begin();
			String hql = "FROM Phone ORDER BY Country ASC, Price DESC";
			Query<Phone> query = session.createQuery(hql, Phone.class);
			List<Phone> result = query.getResultList();
			session.getTransaction().commit();
			session.close();
			return result;
		} catch (Exception e) {
			e.printStackTrace();
			session.getTransaction().rollback();
			return null;
		}
	}

	public boolean hasPhonePricedAbove50Million() {
		try {
			session.getTransaction().begin();
			String hql = "FROM Phone WHERE Price > 50000000";
			Query<Phone> query = session.createQuery(hql, Phone.class);
			List<Phone> result = query.getResultList();
			session.getTransaction().commit();
			session.close();
			return !result.isEmpty();
		} catch (Exception e) {
			e.printStackTrace();
			session.getTransaction().rollback();
			return false;
		}
	}

	public Phone getFirstPinkPhoneOver15Million() {
		try {
			session.getTransaction().begin();
			String hql = "FROM Phone WHERE Color = 'Pink' AND Price > 15000000 ORDER BY Price ASC";
			Query<Phone> query = session.createQuery(hql, Phone.class);
			query.setMaxResults(1);
			Phone result = query.getSingleResult();
			session.getTransaction().commit();
			session.close();
			return result;
		} catch (NoResultException e) {
			session.getTransaction().commit();
			return null;
		} catch (Exception e) {
			e.printStackTrace();
			session.getTransaction().rollback();
			return null;
		}
	}
}
