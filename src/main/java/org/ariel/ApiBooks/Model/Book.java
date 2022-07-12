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
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@NoArgsConstructor
@Data
@Table(
    uniqueConstraints = {
        @UniqueConstraint(columnNames = {"code"})
    }
)
public class Book {
    @Id  @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    private String name;
    private String description;
    private boolean state;
    private long code;
    @Transient
    private Book oldState;

    public Book(long i, String n, String d, boolean s, long c){
        id = i;
        name = n;
        description = d;
        state = s;
        code = c;
    }

    @PostLoad
    public void postLoad(){
        oldState = new Book();
    }

    @PrePersist
    public void preInsert(){
        state = true;
    }

    @PreUpdate
    public void preUpdate(){
        state = oldState.isState();
    }
}
