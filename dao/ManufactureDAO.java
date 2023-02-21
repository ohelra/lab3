package dao;

import java.util.List;
import org.hibernate.Session;
import org.hibernate.query.Query;
import db.HibernateUtils;
import model.Manufacture;

public class ManufactureDAO {
	Session session = HibernateUtils.getFactory().openSession();

	public boolean add(Manufacture manufacture) {
		try {
			session.getTransaction().begin();
			session.save(manufacture);
			session.getTransaction().commit();
			session.close();
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			session.getTransaction().rollback();
			return false;
		}
	}

	public Manufacture get(int id) {
		try {
			session.getTransaction().begin(); //bắt đầu kb db tạm thời
			Manufacture manufacture = session.get(Manufacture.class, String.valueOf(id)); //kb 1 oj 
			session.getTransaction().commit(); //đẩy db(code) lên DB
			session.close(); //sau đó close
			return manufacture;
		} catch (Exception e) {
			e.printStackTrace();
			session.getTransaction().rollback();//quay lại thao tác ban đầu
			return null;
		}
	}

	public List<Manufacture> getAll() {
		try {
			session.getTransaction().begin();
			String hql = "FROM Manufacture"; //lấy từ model, câu tvan
			List<Manufacture> manufactures = session.createQuery(hql, Manufacture.class).getResultList();
			session.getTransaction().commit();
			session.close();
			return manufactures;
		} catch (Exception e) {
			e.printStackTrace();
			session.getTransaction().rollback();
			return null;
		}
	}

	public boolean remove(int id) {
		try {
			session.getTransaction().begin();
			Manufacture manufacture = session.get(Manufacture.class, String.valueOf(id));
			if (manufacture != null) {
				session.delete(manufacture);
				session.getTransaction().commit();
				session.close();
				return true;
			}
			return false;
		} catch (Exception e) {
			e.printStackTrace();
			session.getTransaction().rollback();
			return false;
		}
	}

	public boolean remove(Manufacture m) {
		try {
			session.getTransaction().begin();
			Manufacture manufacture = session.get(Manufacture.class, String.valueOf(m.getId()));
			if (manufacture != null) {
				session.delete(manufacture);
				session.getTransaction().commit();
				session.close();
				return true;
			}
			return false;
		} catch (Exception e) {
			e.printStackTrace();
			session.getTransaction().rollback();
			return false;
		}
	}

	public boolean update(Manufacture m) {
		try {
			session.getTransaction().begin();
			String hql = "UPDATE Manufacture SET Name = :name, Location = :location, Employee = :employee WHERE Id = :id";
			//lấy từ DB
			//update từ model
			Query<Manufacture> query = session.createQuery(hql);
			//jdbc
			query.setParameter("id", m.getId()); //lấy id truyền lại String hql
			query.setParameter("name", m.getName());
			query.setParameter("location", m.getLocation());
			query.setParameter("employee", m.getEmployee());

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

	public boolean moreThan100Employees() {
		try {
			session.getTransaction().begin();
			String hql = "FROM Manufacture";
			List<Manufacture> manufactures = session.createQuery(hql, Manufacture.class).getResultList();
			session.getTransaction().commit();
			session.close();

			// Check if all Manufacture have more than 100 employees
			for (Manufacture m : manufactures) {
				if (m.getEmployee() > 100) {
					return true;
				}
			}
			return false;
		} catch (Exception e) {
			e.printStackTrace();
			session.getTransaction().rollback();
			return false;
		}
	}

	public int sumEmployees() {
		try {
			session.getTransaction().begin();
			String hql = "FROM Manufacture";
			List<Manufacture> manufactures = session.createQuery(hql, Manufacture.class).getResultList();
			session.getTransaction().commit();
			session.close();

			// Calculate sum of all employees
			int sum = 0;
			for (Manufacture m : manufactures) {
				sum += m.getEmployee();
			}
			return sum;
		} catch (Exception e) {
			e.printStackTrace();
			session.getTransaction().rollback();
			return -1;
		}
	}

	public Manufacture lastInUS() {
		try {
			session.getTransaction().begin();
			String hql = "FROM Manufacture WHERE Location = 'US'";
			List<Manufacture> manufactures = session.createQuery(hql, Manufacture.class).getResultList();
			session.getTransaction().commit();
			session.close();

			// Get the last Manufacture object in the list
			if (!manufactures.isEmpty()) {
				return manufactures.get(manufactures.size() - 1);
			} else {
				throw new Exception("InvalidOperationException");
			}
		} catch (Exception e) {
			e.printStackTrace();
			session.getTransaction().rollback();
			return null;
		}
	}
}