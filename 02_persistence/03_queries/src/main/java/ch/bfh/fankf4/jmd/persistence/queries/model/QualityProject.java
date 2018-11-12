package ch.bfh.fankf4.jmd.persistence.queries.model;

import javax.persistence.Entity;

@Entity
public class QualityProject extends Project {

    public QualityProject() {
    }

    public QualityProject(String name) {
        super(name);
    }
}
