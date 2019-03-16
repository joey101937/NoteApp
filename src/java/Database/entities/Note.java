/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Database.entities;

import java.io.Serializable;
import java.sql.Date;
import java.time.LocalDate;
import javax.persistence.*;

/**
 *
 * @author Joseph
 */
@Entity
@Table(name = "notes")
public class Note implements Serializable{

    private int ID;
    private Date lastEditedDate;
    private String name = "";
    private String owner = "";
    private String contents = "";

    //no arg constructor, required
    public Note(){
        
    }
    
    /**
     * sets last edited date to current date for this item in memory
     */
    public void updateDate(){
        setLastEditedDate(Date.valueOf(LocalDate.now()));
    }

    @Id
    @Column(name = "ID")
    @GeneratedValue(strategy=GenerationType.AUTO)
    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    @Column(name = "LastEditedDate")
    public Date getLastEditedDate() {
        return lastEditedDate;
    }

    public void setLastEditedDate(Date lastEditedDate) {
        this.lastEditedDate = lastEditedDate;
    }

    @Column(name = "Name")
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Column(name="Owner")
    //@JoinColumn(name = "Username")
    public String getOwner() {
        return owner;
    }

    public void setOwner(String u) {
        this.owner = u;
    }

    @Column(name = "Contents")
    public String getContents() {
        return contents;
    }

    public void setContents(String contents) {
        this.contents = contents;
    }

}
