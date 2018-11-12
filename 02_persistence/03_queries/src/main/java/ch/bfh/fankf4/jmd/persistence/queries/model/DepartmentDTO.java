package ch.bfh.fankf4.jmd.persistence.queries.model;

public class DepartmentDTO {

    private String name;
    private Long numberOfEmployees;

    public DepartmentDTO(String name, Long numberOfEmployees) {
        this.name = name;
        this.numberOfEmployees = numberOfEmployees;
    }

    public String getName() {
        return name;
    }

    public Long getNumberOfEmployees() {
        return numberOfEmployees;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setNumberOfEmployees(Long numberOfEmployees) {
        this.numberOfEmployees = numberOfEmployees;
    }
}
