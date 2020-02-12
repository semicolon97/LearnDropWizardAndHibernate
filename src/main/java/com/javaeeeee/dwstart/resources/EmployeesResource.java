
package com.javaeeeee.dwstart.resources;

import com.javaeeeee.dwstart.core.Employee;
import com.javaeeeee.dwstart.db.EmployeeDAO;
//import io.dropwizard.Configuration;
import io.dropwizard.hibernate.UnitOfWork;
import io.dropwizard.jersey.params.LongParam;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.io.Serializable;
import java.util.List;
import java.util.Optional;

@Path("/employees")
@Produces(MediaType.APPLICATION_JSON)
public class EmployeesResource {

    private final EmployeeDAO employeeDAO;

    public EmployeesResource(EmployeeDAO employeeDAO) {
        this.employeeDAO = employeeDAO;
    }

    @GET
    @UnitOfWork
    public List<Employee> findByName(
            @QueryParam("name") Optional<String> name
    ) {
        if (name.isPresent()) {
            return employeeDAO.findByName(name.get());
        } else {
            return employeeDAO.findAll();
        }
    }

    @GET
    @Path("/{id}")
    @UnitOfWork
    public Optional<Employee> findById(@PathParam("id") LongParam id) {
        return employeeDAO.findById(id.get());
    }

    @POST
    @Path("/addEmployees")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.TEXT_PLAIN)
    public String createEmployee(Employee employee) {
//        String firstName = employee.getFirstName();
//        String lastName = employee.getLastName();
//        String email = employee.getE_mail();
//
//        System.out.println("First Name : "+firstName);
//        System.out.println("Second Name : "+lastName);
//        System.out.println("Email : "+email);


        return employeeDAO.createEmployee(employee);

        //System.out.println("POST method executed");

    }

    @DELETE
    @Path("/deleteEmployees/{id}")
    public String deleteEmployee(@PathParam("id") int id) {
        return employeeDAO.deleteEmployee(id);

    }

    @PUT
    @Path("/updateEmployees/{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.TEXT_PLAIN)
    public String updateEmployee(Employee employee, @PathParam("id") int id) {
        return employeeDAO.updateEmployee(employee, id);
    }

//    @POST
//    @Path("/interns")
//    @Consumes(MediaType.APPLICATION_JSON)
//    @Produces(MediaType.TEXT_PLAIN)
//    public String createEmployee() {
//        String firstName = employee.getFirstName();
//        String lastName = employee.getLastName();
//
//        System.out.println("First Name : "+firstName);
//        System.out.println("Second Name : "+lastName);

//
//        return "POST method executed";
//
//        //System.out.println("POST method executed");
//
//    }
}
