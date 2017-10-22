package io.orten.nano.impl;

import io.orten.nano.model.Project;
import io.orten.nano.model.User;
import io.orten.nano.util.Database;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import javax.ws.rs.core.Response;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class ProjectService {

    public static List<Project> p_list = new ArrayList<Project>();

    public static Project getProject(String projectID) throws Exception {
        Session session = Database.getSession();
        Project project = session.get(Project.class, projectID);
        session.close();
        return project;
    }

    public static List<Project> getProjects() throws Exception {
            Session session = Database.getSession();
            List projects = session.createQuery("from Project").list();
            session.close();
            return projects;
    }

    public static List getProjectsByName(String projectName) throws Exception {
        Session session = Database.getSession();
        Query query = session.createQuery("from Project where name like :projectName");
        query.setParameter("projectName", "%" + projectName + "%");
        List<Project> projects = query.list();
        session.close();
        return projects;
    }

    public static void saveProject(Project project) throws Exception {
        Session session = Database.getSession();
        Transaction tx = null;
        try {
            tx = session.beginTransaction();
            session.save(project);
            tx.commit();
        } catch (HibernateException e) {
            if (tx != null) tx.rollback();
            throw e;
        } finally {
            session.close();
        }
    }

    public static void deleteProject(String projectID) throws Exception {
        Session session = Database.getSession();
        Transaction tx = null;
        try {
            tx = session.beginTransaction();
            Project project= session.get(Project.class, projectID);
            session.delete(project);
            tx.commit();
        } catch (HibernateException e) {
            if (tx != null) tx.rollback();
            throw e;
        } finally {
            session.close();
        }
    }

    /**
     * saves a donor information of an specific project
     * @param donor
     * @throws Exception
     */
    public static void fundedBy(User donor,String projectID) throws Exception{
        Session session = Database.getSession();
        Transaction tx = null;
        try {
            tx = session.beginTransaction();
            Project project = getProject(projectID);
            project.setDonor(donor);
            tx.commit();
        } catch (HibernateException e) {
            if (tx != null) tx.rollback();
            throw e;
        } finally {
            session.close();
        }
    }
    public static List<Project> getFundedProjects(String donorID) throws Exception{
        List<Project> fundedProjects = new ArrayList<>();
        Session session = Database.getSession();
        Transaction tx= null;
        try{
            tx =session.beginTransaction();
            Query query = session.createQuery("from Project where donor.userID= :donorID");
            query.setParameter("donorID",donorID);
            fundedProjects=query.getResultList();
            if (fundedProjects.size()!=0){
                return fundedProjects;
            } else  return null;

        }catch(HibernateException e){
            if (tx != null) tx.rollback();
            throw e;
        }finally{
            session.close();
        }
    }
}