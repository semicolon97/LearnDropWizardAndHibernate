
package com.javaeeeee.dwstart.db;

import java.util.Optional;
import com.javaeeeee.dwstart.core.Employee;
import com.javaeeeee.dwstart.core.Account;
import io.dropwizard.hibernate.AbstractDAO;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.query.Query;

public class EmployeeDAO extends AbstractDAO<Employee> {

    public EmployeeDAO(SessionFactory sessionFactory) {
        super(sessionFactory);
    }

    public List<Employee> findAll() {
        return list((Query<Employee>) namedQuery("com.javaeeeee.dwstart.core.Employee.findAll"));
    }

    public List<Employee> findByName(String name) {
        StringBuilder builder = new StringBuilder("%");
        builder.append(name).append("%");
        return list(
                (Query<Employee>) namedQuery("com.javaeeeee.dwstart.core.Employee.findByName").setParameter("name", builder.toString())
        );
    }

    public Optional<Employee> findById(long id) {
        return Optional.ofNullable(get(id));
    }

    public Session createSession() {
        Configuration config = new Configuration();
        config.configure();
        config.addAnnotatedClass(Employee.class);
        //config.addAnnotatedClass(Account.class);
        //config.addResource("Student.hbm.xml");
        StandardServiceRegistry serviceRegistry = new StandardServiceRegistryBuilder().applySettings(config.getProperties()).build();
        SessionFactory sessionFactory = config.buildSessionFactory(serviceRegistry);

        Session session = sessionFactory.openSession();

        return session;
    }

    public String createEmployee( Employee employee) {


        Session session = createSession();

        Transaction transaction = session.beginTransaction();

        Employee mergedEmployee = (Employee) session.merge(employee);

//        session.persist(mergedEmployee);

        transaction.commit();

        session.close();


        return "POST method executed";
    }

    public String deleteEmployee(int id) {

        Session session = createSession();

        Transaction transaction = session.beginTransaction();

        Employee toBeRemoved = session.get(Employee.class, id);

        if (toBeRemoved != null) {
            session.remove(toBeRemoved);

            transaction.commit();

            session.close();

            return "Employee "+ id + " removed from database";
        }

        else {
            return "Employee not found";
        }
    }

    public String updateEmployee(Employee employee, int id) {

        Session session = createSession();
        Transaction transaction = session.beginTransaction();

        Employee newEmployee = session.get(Employee.class, id);

        if (newEmployee!= null) {
            employee.setId(id);

            Employee mergedEmployee = (Employee) session.merge(employee);

//            session.persist(mergedEmployee);

            transaction.commit();

            session.close();
        }

        else {
            return "Employee not found";
        }

        return "Employee "+id+" records updated";
    }
}
