package jpa.util;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class SmsUtil {
    private static final String PERSISTENCE_UNIT_NAME = "sms";
    private static EntityManagerFactory factory;

    public static EntityManagerFactory getEntityManagerFactory() {
        if (factory == null) {
            factory = Persistence.createEntityManagerFactory(PERSISTENCE_UNIT_NAME);
        }//end if
        return factory;
    }//end getEntityManagerFactory

    public static void shutdown() {
        if (factory != null) {
            factory.close();
        }//end if
    }//end shutdown
}//end class
