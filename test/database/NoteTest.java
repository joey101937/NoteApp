/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package database;

import Database.DatabaseController;
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
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

/**
 *
 * @author Joseph
 */
public class NoteTest {
    static SessionFactory factory;
    
    public NoteTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
       // factory = new Configuration().configure("hibernate.cfg.xml").addAnnotatedClass(Note.class).buildSessionFactory();
       factory = createSessionFactory();
    }

       private static SessionFactory createSessionFactory() {
        SessionFactory sessionFactory;
        Configuration configuration = new Configuration();
        configuration.configure("hibernate.cfg.xml");
        configuration.addAnnotatedClass(Note.class);
        configuration.addAnnotatedClass(User.class);
        StandardServiceRegistry serviceRegistry = new StandardServiceRegistryBuilder().applySettings(
                configuration.getProperties()).build();
        sessionFactory = configuration.buildSessionFactory(serviceRegistry);
        return sessionFactory;
    }

    @AfterClass
    public static void tearDownClass() {
        factory.close();
        System.out.println("closed");
    }

    @Before
    public void setUp() {
    }
    
    @After
    public void tearDown() {
    }
    
    /**
     * Make sure we can get the session working
     */
    @Test
    public void connectionTest(){
        
        assert factory!=null;
        Session session = factory.openSession();
        assert session!=null;     
        
    }

    /**
     * Test saving things in DB
     */
    @Test
    public void testSave() {
        
        Session session = factory.openSession();
        Date date = Date.valueOf(LocalDate.now());
        
        Note note = new Note();
        note.setName("Joseph's note");
        note.setContents("blah blah blah");
        note.setOwner("Joseph");
        note.setLastEditedDate(Date.valueOf(LocalDate.now()));
        
        session.beginTransaction();
        session.save(note);
        
        session.getTransaction().commit();
        session.close();
        System.out.println(date);

    }
    
    
    @Test
    public void testSave2(){
        Note note = new Note();
        note.updateDate();
        note.setOwner("Joseph");
        note.setName("Note" + (int)(Math.random()*9999));
        note.setContents("Blah Blah Blah");
        assert DatabaseController.save(note);
    }
    
    @Test
    public void getUser(){
        assert DatabaseController.getUserByName("Joseph")!=null;
    }

    @Test
    public void query(){
        List<Note> notes = DatabaseController.getNotesByUser("Joseph");
        assert(notes!=null);
        for(Note n : notes){
            System.out.println(n.getName());
        }
    }
    
    @Test
    public void testUpdate(){
        List<Note> notes = DatabaseController.getNotesByUser("Joseph");
        for(Note n : notes){
           DatabaseController.updateNoteContents(n.getID(), "new Contents");
        }
    }
    
    @Test
    public void testDelete(){
        Note note = new Note();
        note.updateDate();
        note.setName("test note");
        note.setContents("asgasgag");
        note.setOwner("Test");
        User u = DatabaseController.getUserByName("Test");
        DatabaseController.save(note);
        System.out.println("username: " + u.getUsername());
        for(Note n : DatabaseController.getNotesByUser(u)){
            assert DatabaseController.delete(n);
        }
        assert DatabaseController.getNotesByUser("Test").isEmpty();
    }
    
}
