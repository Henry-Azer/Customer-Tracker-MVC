package customer.dao;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import customer.entity.Customer;

@Repository("CustomerDAO")
public class CustomerDAOImp implements CustomerDAO {
	
	@Autowired
	private SessionFactory sessionFactory;

	@Override
	public List<Customer> getCustomers() {
		Query<Customer> query = session().createQuery("From Customer ORDER BY firstName", Customer.class);
		
		return query.getResultList();
	}

	@Override
	public void saveCustomer(Customer customer) {
		session().saveOrUpdate(customer);
	}

	@Override
	public Customer getCustomer(int id) {
		return session().get(Customer.class, id);
	}

	@Override
	public void deleteCustomer(int id) {
		session().delete(getCustomer(id));
	}
	
	private Session session() {
		return sessionFactory.getCurrentSession();
	}
}
