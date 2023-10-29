package com.example.adminhttm.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Data
@Entity
@Table(name = "favor")
@AllArgsConstructor
@NoArgsConstructor
public class Favor {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "name")
    private String name;

    @ManyToMany(mappedBy = "favors")
    private List<User> users;

    public void removeUser(User user){
        this.users.remove(user);
        user.getFavors().remove(this);
    }

}
