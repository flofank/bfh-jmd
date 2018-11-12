package ch.bfh.fankf4.jmd.persistence.queries.model;

import javax.persistence.*;

@Entity
public class Phone {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "phone_seq")
    @SequenceGenerator(name = "phone_seq", sequenceName = "phone_seq")
    private Integer id;

    private String phonenumber;
    @Enumerated(EnumType.STRING)
    private PhoneType type;

    @ManyToOne(optional = false)
    private Employee employee;

    public Phone() {
    }

    public Phone(String phonenumber, PhoneType type, Employee employee) {
        this.phonenumber = phonenumber;
        this.type = type;
        this.employee = employee;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getPhonenumber() {
        return phonenumber;
    }

    public void setPhonenumber(String phonenumber) {
        this.phonenumber = phonenumber;
    }

    public PhoneType getType() {
        return type;
    }

    public void setType(PhoneType type) {
        this.type = type;
    }

    public Employee getEmployee() {
        return employee;
    }

    public void setEmployee(Employee employee) {
        this.employee = employee;
    }
}
