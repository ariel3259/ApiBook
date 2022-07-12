package org.ariel.ApiBooks.Model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.PostLoad;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.persistence.UniqueConstraint;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(
    uniqueConstraints = {
        @UniqueConstraint(columnNames = {"email"})
    }
)
public class Users {
    @Id @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    private String name;
    private String lastName;
    private String email;
    private String password;
    private boolean state;

    @Transient
    private Users oldState;

    @PostLoad
    public void postLoad(){
        oldState = new Users();
    }

    @PrePersist
    public void prePersist(){
        state = true;
    }

    @PreUpdate
    public void preUpdate(){
        state = oldState.isState();
        password = oldState.getPassword();
    }
}
