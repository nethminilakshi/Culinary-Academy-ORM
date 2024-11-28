package lk.ijse.culinaryacademy.bo;

import lk.ijse.culinaryacademy.bo.custom.impl.*;
import lk.ijse.culinaryacademy.entity.StudentCoursesDetails;

public class BOFactory {
    private static BOFactory boFactory;

    private BOFactory() {
    }

    public static BOFactory getBoFactory() {
        return (boFactory == null) ? (boFactory = new BOFactory()) : (boFactory);
    }

    public enum BOType {
        COURSE,STUDENT,PAYMENT, USER, STUDENTREGDETAILS

    }
    public SuperBO  getBoType(BOType boType) {
        switch (boType) {
            case COURSE:
             return  new CoursesBOImpl();
            case STUDENT:
                return  new StudentBOImpl();
         case PAYMENT:
               return  new PaymentBOImpl();
           case STUDENTREGDETAILS:
                return  new StudentCoursesDetailsBOImpl();
            case USER:
                return new UserBOImpl();
            default:
                return null;
        }
    }
}
