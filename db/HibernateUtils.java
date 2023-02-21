package db;

import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;

import model.Manufacture;
import model.Phone;

public class HibernateUtils {
	// muốn hằng số tạo ra 1 lần dùng chung static final
	// tạo getFactory
	private static final SessionFactory FACTORY;

	// khối tĩnh này chạy 1 lần
	static {
		Configuration conf = new Configuration();
		conf.configure("hibernate.cfg.xml");

		// Khai bao tồn tại Category
		conf.addAnnotatedClass(Manufacture.class);
		conf.addAnnotatedClass(Phone.class);
		
		// ServiceRegistry lớp trừ tượng không thay đổi theo thời gian
		ServiceRegistry registry = new StandardServiceRegistryBuilder().applySettings(conf.getProperties()).build();
		FACTORY = conf.buildSessionFactory(registry);
	}

	public static SessionFactory getFactory() {
		return FACTORY;
	}
}