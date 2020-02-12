package com.javaeeeee.dwstart;

import com.example.dwnh.LearnConfiguration;
import com.fasterxml.classmate.AnnotationConfiguration;
import com.javaeeeee.dwstart.core.Employee;
import com.javaeeeee.dwstart.core.Account;

//import com.javaeeeee.dwstart.core.User;
import com.javaeeeee.dwstart.db.EmployeeDAO;
//import com.javaeeeee.dwstart.resources.ConverterResource;
import com.javaeeeee.dwstart.resources.EmployeesResource;
//import com.javaeeeee.dwstart.resources.HelloResource;
//import com.javaeeeee.dwstart.resources.SecuredHelloResource;
import io.dropwizard.Application;
import io.dropwizard.auth.AuthDynamicFeature;
import io.dropwizard.auth.AuthValueFactoryProvider;
import io.dropwizard.auth.basic.BasicCredentialAuthFilter;
import io.dropwizard.client.JerseyClientBuilder;
import io.dropwizard.db.DataSourceFactory;
import io.dropwizard.hibernate.HibernateBundle;
import io.dropwizard.setup.Bootstrap;
import io.dropwizard.setup.Environment;
import javax.ws.rs.client.Client;
import org.glassfish.jersey.server.filter.RolesAllowedDynamicFeature;
import org.hibernate.SessionFactory;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;

import java.io.File;

public class LearnApplication
        extends Application<LearnConfiguration> {

    public static void main(final String[] args) throws Exception {

//        Configuration config = new Configuration();
//        config.configure();
//        config.addAnnotatedClass(Employee.class);
//        //config.addResource("Student.hbm.xml");
//        StandardServiceRegistry serviceRegistry = new StandardServiceRegistryBuilder().applySettings(config.getProperties()).build();
//        SessionFactory sessionFactory = config.buildSessionFactory(serviceRegistry);

//        Session session = sessionFactory.openSession();
//
//        Transaction transaction = session.beginTransaction();
//        Employee employee = new Employee();
//
//        employee.setE_mail("iit2016033@iiita.ac.in");
//        employee.setFirstName("Akash");
//        employee.setLastName("Mishra");
//        employee.setPhone("9696427244");
//        employee.setPosition("Intern");
//
//        session.save(employee);
//        session.getTransaction().commit();

//        Session session = sessionFactory.openSession();
//
//        Transaction transaction = session.beginTransaction();
//        Employee emp = new Employee();
//
//        emp.setPosition("Intern");
//        emp.setPhone("1234567890");
//        emp.setLastName("Gahlawat");
//        emp.setFirstName("Amit");
//        emp.setE_mail("ankit@gmail.com");
//
//        session.persist(emp);
//
//        emp.setPosition("FTC");
//
//        //emp = session.find(Employee.class, (long)2);
//        session.evict(emp);
//        //session.remove(emp);
//        emp.setLastName("Yadav");
//
//        Employee mergedEmp = (Employee) session.merge(emp);
//
//        transaction.commit();
//
//        session.close();

        new LearnApplication().run(new String[] {"server", "config.yml"});
    }

    private final HibernateBundle<LearnConfiguration> hibernateBundle
            = new HibernateBundle<LearnConfiguration>(Employee.class) {

        @Override
        public DataSourceFactory getDataSourceFactory(
                LearnConfiguration configuration
        ) {
            return configuration.getDataSourceFactory();
        }

    };




    @Override
    public void initialize(
            final Bootstrap<LearnConfiguration> bootstrap) {
        bootstrap.addBundle(hibernateBundle);
    }

    @Override
    public void run(final LearnConfiguration configuration,
                    final Environment environment) {
        final EmployeeDAO employeeDAO
                = new EmployeeDAO(hibernateBundle.getSessionFactory());


        environment.jersey().register(new EmployeesResource(employeeDAO));
    }


}