package lk.ijse.culinaryacademy.dao;


import lk.ijse.culinaryacademy.dao.custom.impl.*;

public class DAOFactory {
    private static DAOFactory daoFactory;

    private DAOFactory() {

    }

    public static DAOFactory getDaoFactory() {
        return (daoFactory == null) ? (daoFactory = new DAOFactory()) : daoFactory;
    }

    public enum DAOTypes {
        COURSE, STUDENT, PAYMENT, USER, STUDENTREGDETAILS
    }

    public SuperDAO getDAOTypes(DAOTypes daoTypes) {
        switch (daoTypes) {
            case COURSE:
                return new CourseDAOImpl();
            case STUDENT:
                return new StudentDAOImpl();
            case PAYMENT:
                return new PaymentDAOImpl();
            case USER:
                return new UsetDAOImpl();
         case STUDENTREGDETAILS:
                return new StudentCoursesDetailsDAOImpl();

            default:
                return null;
        }
    }
}
