package il.ac.shenkar.todolistapi.hibernatetransactionmanager;

import org.hibernate.Session;

/**
 * Created by ysapir on 11/26/2016.
 */
public interface TransactionLogic {

	Object performTransactionLogic(Session session, Object...args);
}
