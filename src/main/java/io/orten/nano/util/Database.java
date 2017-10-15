package io.orten.nano.util;

import io.orten.nano.model.Organization;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.Metadata;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.query.Query;

import javax.xml.ws.Response;
import java.util.List;

//  Creates and manages connections with and transactions to the database

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

    //saves an organization's object to the database

    public static void saveOrganization(Organization org) {
        if (sessionFactory == null)
            init();
        try (Session s = sessionFactory.openSession()) {
            s.beginTransaction();
            s.save(org);
            s.getTransaction().commit();
        }
    }

    //updates an organization's object already saved in saved in the database

    public static void updateOrganization(Organization org) {
        if (sessionFactory == null)
            init();
        try (Session s = sessionFactory.openSession()) {
            s.beginTransaction();
            s.update(org);
            s.getTransaction().commit();
        }

    }

    //gets one organization's object from the database based on its ID

    public static List<Organization> getOrganization(String orgID) {
        if (sessionFactory == null)
            init();
        try (Session s = sessionFactory.openSession()) {
            s.beginTransaction();
            Query q = s.createQuery("from Organization as org where org.organizationID = :orgID");
            q.setParameter("orgID", orgID);
            List<Organization> list = q.getResultList();
            s.getTransaction().commit();
            return list;
        }

    }

    //gets all the organization's objects  from the database

    public static List<Organization> getAllOrganizations(){
        if (sessionFactory == null)
        init();
        try (Session s = sessionFactory.openSession()) {
            s.beginTransaction();
            Query q = s.createQuery("from Organization");
            List list = q.getResultList();
            s.getTransaction().commit();
            return list;


        }
    }

    public static String deleteOrganization(String orgID){
        if (sessionFactory== null)
            init();
        try(Session s = sessionFactory.openSession()){
            s.beginTransaction();
            Query q = s.createQuery("from Organization org where org.organizationID = :orgID");
            q.setParameter("orgID",orgID);
            List<Organization> list = q.getResultList();
            String name = list.get(0).organizationName;
            String ID = list.get(0).organizationID;
            s.delete(list.get(0));
            s.getTransaction().commit();
            return "Organization :"+name + " OrgnaizationID :"+ ID +" is deleted";
        }
    }
}


