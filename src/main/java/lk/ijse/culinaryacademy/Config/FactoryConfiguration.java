package lk.ijse.culinaryacademy.Config;


import lk.ijse.culinaryacademy.entity.*;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import java.io.IOException;
import java.util.Properties;

public class FactoryConfiguration {

    private static FactoryConfiguration factoryConfiguration;
    private final SessionFactory sessionFactory;
    private FactoryConfiguration() {
        try {
            Configuration configuration = new Configuration();
            Properties properties = new Properties();
            properties.load(Thread.currentThread().getContextClassLoader().getResourceAsStream("hibernate.properties"));
            configuration.setProperties(properties);
            configuration.addAnnotatedClass(Students.class);
            configuration.addAnnotatedClass(Courses.class);
            configuration.addAnnotatedClass(Payment.class);
            configuration.addAnnotatedClass(User.class);
            configuration.addAnnotatedClass(StudentCoursesDetails.class);

            sessionFactory = configuration.buildSessionFactory();
        } catch (Exception e) {
            e.printStackTrace();  // Log or print the exception details
            throw new RuntimeException("Failed to create session factory: " + e.getMessage());
        }
    }


    public static FactoryConfiguration getInstance() throws IOException {
        return (factoryConfiguration==null) ? factoryConfiguration=new FactoryConfiguration():factoryConfiguration;
    }

    public Session getSession(){
        return sessionFactory.openSession();
    }

}
