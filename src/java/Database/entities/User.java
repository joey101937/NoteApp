/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Database.entities;

import java.io.Serializable;
import javax.persistence.*;

/**
 *
 * @author Joseph
 */
@Entity
@Table(name="users")
public class User implements Serializable{
    private String username;
    //no arg constructor, required
    public User(){
        
    }
    
    /**
     * User constructor that sets username
     * @param username username
     */
    public User(String username){
        this.username = username;
    }
    
    @Id
    @Column(name="Username")
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
    
}
