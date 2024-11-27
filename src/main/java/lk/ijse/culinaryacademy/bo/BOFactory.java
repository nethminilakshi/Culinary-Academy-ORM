package lk.ijse.culinaryacademy.bo;

import lk.ijse.culinaryacademy.bo.custom.impl.CoursesBOImpl;
import lk.ijse.culinaryacademy.bo.custom.impl.StudentBOImpl;

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
//         case PAYMENT:
//               return  new PaymentBOImpl();
//            case STUDENTREGDETAILS:
//                return  new StudentRegDetailsBOImpl();
            default:
                return null;
        }
    }
}
