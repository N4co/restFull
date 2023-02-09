package br.com.felix.projeto.data.v2;


import java.io.Serializable;
import java.util.Date;
import java.util.Objects;


public class PersonVo2 implements Serializable {
    private static final long serialVersionUID = 1L;
    private Long id;
    private String firstName;
    private String lastName;
    private String address;
    private String gender;

    private Date happyBirthay;
    public PersonVo2() {
    }
    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public String getFirstName() {
        return firstName;
    }
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }
    public String getLastName() {
        return lastName;
    }
    public void setLastName(String lastName)
    {
        this.lastName = lastName;
    }
    public String getAddress() {
        return address;
    }
    public void setAddress(String address) {
        this.address = address;
    }
    public String getGender() {
        return gender;
    }
    public void setGender(String gender) {
        this.gender = gender;
    }
    public Date getHappyBirthay() {
        return happyBirthay;
    }
    public void setHappyBirthay(Date happyBirthay) {
        this.happyBirthay = happyBirthay;
    }
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof PersonVo2 personVo2)) return false;
        return Objects.equals(id, personVo2.id) && Objects.equals(firstName, personVo2.firstName) && Objects.equals(lastName, personVo2.lastName) && Objects.equals(address, personVo2.address) && Objects.equals(gender, personVo2.gender) && Objects.equals(happyBirthay, personVo2.happyBirthay);
    }
    @Override
    public int hashCode() {
        return Objects.hash(id, firstName, lastName, address, gender, happyBirthay);
    }
}