package il.ac.shenkar.todolistapi.hibernatetransactionmanager;

import org.hibernate.Session;
import org.hibernate.Transaction;

/**
 * Created by ysapir on 11/26/2016.
 */
public class HibernateTransactionManager {

	public Object performTransaction(TransactionLogic transactionLogic, Object...args) {

		Session session = HibernateSessionConfigurer.openSession();
		Transaction tx = null;
		Object object = null;
		try {
			tx = session.getTransaction();
			tx.begin();
			object = transactionLogic.performTransactionLogic(session, args);
			tx.commit();
		} catch (Exception e) {
			if (tx != null) {
				tx.rollback();
			}
			e.printStackTrace();
		} finally {
			session.close();
		}
		return object;
	}
}
