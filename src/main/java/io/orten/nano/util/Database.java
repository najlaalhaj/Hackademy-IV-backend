package io.orten.nano.util;

import io.orten.nano.model.Organization;
//import io.orten.nano.model.Transaction;
import io.orten.nano.model.Project;
import io.orten.nano.model.User;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.Metadata;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.query.Query;
import javax.xml.ws.Response;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Creates and manages connections with and transactions to the database
 */
public class Database {

    private static SessionFactory sessionFactory;
    private static void init() {
        final StandardServiceRegistry registry = new StandardServiceRegistryBuilder()
                .configure("hibernate.cfg.xml") // configures settings from hibernate.cfg.xml
                .build();
        MetadataSources mds = new MetadataSources(registry);
        Metadata md = mds.buildMetadata();
        sessionFactory = md.buildSessionFactory();
        //Make sure that the service registry is destroyed on shutdown by adding a shutdown hook to the runtime
        Runtime.getRuntime().addShutdownHook(new Thread(() ->
        {
            StandardServiceRegistryBuilder.destroy(registry);
        }));
    }

    /**
     * saves an organization object to the database
     */
    public static boolean saveOrganization(Organization org) {
        if (sessionFactory == null)
            init();
        try (Session s = sessionFactory.openSession()) {
            s.beginTransaction();
            String orgID=org.organizationID;
            if (getOrganization(orgID)==null)
            {
                s.save(org);
                s.getTransaction().commit();
                return true;
            }else{
                return false;
            }
        }
    }

    /**
     * updates an organization object already saved in saved in the database
     */
    public static void updateOrganization(Organization org) {
        if (sessionFactory == null)
            init();
        try (Session s = sessionFactory.openSession()) {
            s.beginTransaction();
            s.update(org);
        }
    }

    /**
     * gets one organization object from the database based on its ID
     */
    public static Organization getOrganization(String orgID) {
        if (sessionFactory == null)
            init();
        try (Session s = sessionFactory.openSession()) {
            List<Organization> list = new ArrayList<>();
            s.beginTransaction();
            Query q = s.createQuery("from Organization as org where org.organizationID = :orgID");
            q.setParameter("orgID", orgID);
            list = q.getResultList();
            if (list.isEmpty()){
                return null;
            } else {
                return list.get(0);
            }
        }
    }
    /**
     * gets all the organizations' objects from the database
     */
    public static List<Organization> getAllOrganizations(){
        List<Organization> list= new ArrayList<>();
        if (sessionFactory == null)
            init();
        try (Session s = sessionFactory.openSession()) {
            s.beginTransaction();
            Query q = s.createQuery("from Organization");
            list = q.getResultList();
            return list;
        }
    }

    /**
     * delets an organization object from the database
     */
    public static boolean deleteOrganization(String orgID){
        if (sessionFactory== null)
            init();
        try(Session s = sessionFactory.openSession()){
            s.beginTransaction();
            Query q = s.createQuery("from Organization org where org.organizationID = :orgID");
            q.setParameter("orgID",orgID);
            List<Organization> list = q.getResultList();
            if (!list.isEmpty()){
                s.delete(list.get(0));
                s.getTransaction().commit();
                return true ;
            }
            else {return false;}
        }
    }

    /**
     * saves a donor record in the database
     */
    public static boolean saveUser(User user) {
        if (sessionFactory == null)
            init();
        try (Session s = sessionFactory.openSession()) {
            s.getTransaction();
            if (user.getUserID() != null) {
                s.save(user);
                s.beginTransaction().commit();
                return  true;
            } else {
                return false;
            }
        }
    }


    /**
     * belongs to ProjectService class
     */
    public static Session getSession() throws Exception {
        if(sessionFactory == null) {
            init();
        }
        Session session = sessionFactory.openSession();
        return session;
    }



    }


