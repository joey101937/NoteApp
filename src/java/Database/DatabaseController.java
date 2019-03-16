/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Database;

import Database.entities.Note;
import Database.entities.User;
import java.sql.Date;
import java.time.LocalDate;
import java.util.List;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;

/**
 * Class that interacts with SQL database
 * @author Joseph
 */
public class DatabaseController {
    private static SessionFactory sFactory;
    
    
    public static SessionFactory getSessionFactory() {
        if (sFactory == null) {
            sFactory = createSessionFactory();
        }
        return sFactory;
    }
    
    /**
     * creates session factory with the config file and adds annotated entity classes
     */
    private static SessionFactory createSessionFactory() {
        try {
            SessionFactory sessionFactory;
            Configuration configuration = new Configuration();
            configuration.configure("hibernate.cfg.xml");
            configuration.addAnnotatedClass(Note.class);
            configuration.addAnnotatedClass(User.class);
            StandardServiceRegistry serviceRegistry = new StandardServiceRegistryBuilder().applySettings(
                    configuration.getProperties()).build();
            sessionFactory = configuration.buildSessionFactory(serviceRegistry);
            return sessionFactory;
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("CRITICAL ERROR unable to generate session factory");
            System.exit(-1);
            return null;
        }
    }
    
    
    
    public static User getUserByName(String s){
        if(s==null)return null;
        Session session = null;
        try{
            session = getSessionFactory().openSession();
            session.beginTransaction();
            User u = (User)session.get(User.class, s);
            session.getTransaction().commit();
            session.close();
            return u;
        }catch(Exception e){
            e.printStackTrace();
            if(session!=null)session.close();
            return null;
        }
    }
    
    public static List<Note> getNotesByUser(User u){
        if(u==null)return null;
        Session session = null;
        try{
            List<Note> output;
            session = getSessionFactory().openSession();
            session.beginTransaction();
            output = session.createQuery("from Note n where n.owner='" + u.getUsername() + "'").list();
            session.getTransaction().commit();
            session.close();
            return output;
        }catch(Exception e){
            e.printStackTrace();
            if(session!=null)session.close();
            return null;
        }
    }

    public static List<Note> getNotesByUser(String username) {
        Session session = null;
        try {
            List<Note> output;
            session = getSessionFactory().openSession();
            session.beginTransaction();
            output = session.createQuery("from Note n where n.owner='" + username + "'").list();
            session.getTransaction().commit();
            session.close();
            return output;
        } catch (Exception e) {
            e.printStackTrace();
            if (session != null) {
                session.close();
            }
            return null;
        }
    }
    
       public static Note getNoteById(int i){
        Session session = null;
        try{
            session = getSessionFactory().openSession();
            session.beginTransaction();
            Note u = (Note)session.get(Note.class, i);
            session.getTransaction().commit();
            session.close();
            return u;
        }catch(Exception e){
            e.printStackTrace();
            if(session!=null)session.close();
            return null;
        }
    }

    /**
     * saves a given entity to database
     * @param note entity to save
     * @return true means success, false means not
     */
    public static boolean save(Note note) {
        Session session = null;
        try {
            session = getSessionFactory().openSession();
            session.beginTransaction();
            session.save(note);
            session.getTransaction().commit();
            session.close();
            return true;
        } catch (Exception e) {
            if (session != null) {
                session.close();
            }
            e.printStackTrace();
            return false;
        }
    }

    
        /**
     * saves a given entity to database
     * @param user entity to save
     * @return true means success, false means not
     */
    public static boolean save(User user) {
        Session session = null;
        try {
            session = getSessionFactory().openSession();
            session.beginTransaction();
            session.save(user);
            session.getTransaction().commit();
            session.close();
            return true;
        } catch (Exception e) {
            if (session != null) {
                session.close();
            }
            e.printStackTrace();
            return false;
        }
    }
    
    /**
     * updates contents of note with given ID
     * @param noteID ID
     * @param newContents new Contetents value
     * @return success or not
     */
    public static boolean updateNoteContents(int noteID, String newContents){
     Session session = null;
        try {
            session = getSessionFactory().openSession();
            session.beginTransaction();
            Note n = (Note)session.get(Note.class, noteID);
            n.setContents(newContents);
            session.getTransaction().commit();
            session.close();
            return true;
        } catch (Exception e) {
            if (session != null) {
                session.close();
            }
            e.printStackTrace();
            return false;
        }
    }
    
    /**
     * updates name of note with given ID
     * @param noteID ID
     * @param newName new Name
     * @return is successful
     */
      public static boolean updateNoteName(int noteID, String newName) {
        Session session = null;
        try {
            session = getSessionFactory().openSession();
            session.beginTransaction();
            Note n = (Note)session.get(Note.class, noteID);
            n.setName(newName);
            session.getTransaction().commit();
            session.close();
            return true;
        } catch (Exception e) {
            if (session != null) {
                session.close();
            }
            e.printStackTrace();
            return false;
        }
    }

      public static boolean updateNoteDate(Note n){
          return updateNoteDate(n.getID());
      }
      
      public static boolean updateNoteDate(int id){
           Session session = null;
        try {
            session = getSessionFactory().openSession();
            session.beginTransaction();
            Note n = (Note)session.get(Note.class, id);
            n.setLastEditedDate(Date.valueOf(LocalDate.now()));
            session.getTransaction().commit();
            session.close();
            return true;
        } catch (Exception e) {
            if (session != null) {
                session.close();
            }
            e.printStackTrace();
            return false;
        }
      }
      
    /**
     * removes Note from database
     *
     * @param n note to remove
     * @return success or not
     */
    public static boolean delete(Note n) {
        Session session = null;
        try {
            session = getSessionFactory().openSession();
            session.beginTransaction();
            session.delete(n);
            session.getTransaction().commit();
            session.close();
            return true;
        } catch (Exception e) {
            if (session != null) {
                session.close();
            }
            e.printStackTrace();
            return false;
        }
    }

    /**
     * removes User from database
     *
     * @param user user to remove
     * @return success or not
     */
    public static boolean delete(User user) {
        Session session = null;
        try {
            session = getSessionFactory().openSession();
            session.beginTransaction();
            session.delete(user);
            session.getTransaction().commit();
            session.close();
            return true;
        } catch (Exception e) {
            if (session != null) {
                session.close();
            }
            e.printStackTrace();
            return false;
        }
    }
    
}
