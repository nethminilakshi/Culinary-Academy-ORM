package lk.ijse.culinaryacademy.Config;


import lk.ijse.culinaryacademy.entity.Courses;
import lk.ijse.culinaryacademy.entity.Payment;
import lk.ijse.culinaryacademy.entity.StudentCoursesDetails;
import lk.ijse.culinaryacademy.entity.Students;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import java.io.IOException;
import java.util.Properties;

public class FactoryConfiguration {

    private static FactoryConfiguration factoryConfiguration;
    private final SessionFactory sessionFactory;
    private FactoryConfiguration() throws IOException {
        Configuration configuration = new Configuration();
        Properties properties = new Properties();
        properties.load(Thread.currentThread().getContextClassLoader().getResourceAsStream("hibernate.properties"));
        configuration.setProperties(properties);
        configuration.addAnnotatedClass(Students.class);
        configuration.addAnnotatedClass(Courses.class);
//        configuration.addAnnotatedClass(User.class);
        configuration.addAnnotatedClass(Payment.class);
        configuration.addAnnotatedClass(StudentCoursesDetails.class);
        sessionFactory = configuration.buildSessionFactory();
    }

    public static FactoryConfiguration getInstance() throws IOException {
        return (factoryConfiguration==null) ? factoryConfiguration=new FactoryConfiguration():factoryConfiguration;
    }

    public Session getSession(){
        return sessionFactory.openSession();
    }

}
