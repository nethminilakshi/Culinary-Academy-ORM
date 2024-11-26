package lk.ijse.culinaryacademy.dao;

import lk.ijse.culinaryacademy.dao.custom.impl.CourseDAOImpl;
import lk.ijse.culinaryacademy.dao.custom.impl.PaymentDAOImpl;
import lk.ijse.culinaryacademy.dao.custom.impl.StudentDAOImpl;

public class DAOFactory {
    private static DAOFactory daoFactory;

    private DAOFactory(){

    }
    public static DAOFactory getDaoFactory(){
        return (daoFactory==null)?(daoFactory=new DAOFactory()):daoFactory;
    }
    public enum DAOTypes{
        COURSE,STUDENT,PAYMENT,STUDENTREGDETAILS
    }
    public SuperDAO getDAOTypes(DAOTypes daoTypes){
        switch (daoTypes){
            case COURSE:
                return new CourseDAOImpl();
            case STUDENT:
                return new StudentDAOImpl();
            case PAYMENT: return new PaymentDAOImpl();
//            case STUDENTREGDETAILS:
//                return new StudentRegDetailsDAOImpl();

            default:
                return null;
        }
    }
}
