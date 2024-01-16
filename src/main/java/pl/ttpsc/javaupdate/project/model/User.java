package pl.ttpsc.javaupdate.project.model;

import pl.ttpsc.javaupdate.project.persistence.Persistable;

import java.util.HashSet;
import java.util.Set;

public class User implements Persistable {
    private String name;
    //private Set<Role> roles;

    public User(String name) {
        this.name = name;

    }

    public String getName() {
        return name;
    }


    @Override
    public String toString() {
        return "User{" +
                "name='" + name + '\'' +
                '}';
    }
}
