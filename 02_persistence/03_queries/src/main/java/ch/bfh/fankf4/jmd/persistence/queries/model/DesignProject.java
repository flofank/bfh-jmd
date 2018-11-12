package ch.bfh.fankf4.jmd.persistence.queries.model;

import javax.persistence.Entity;

@Entity
public class DesignProject extends Project {

    public DesignProject() {
    }

    public DesignProject(String name) {
        super(name);
    }
}
