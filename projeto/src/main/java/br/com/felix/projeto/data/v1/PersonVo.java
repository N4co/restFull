package br.com.felix.projeto.data.v1;


import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.github.dozermapper.core.Mapping;
import org.springframework.hateoas.RepresentationModel;

import java.io.Serializable;

import java.util.Objects;


@JsonPropertyOrder({"id","firstName","lastName","address", "gender"})
public class PersonVo extends RepresentationModel <PersonVo> implements Serializable {
    private static final long serialVersionUID = 1L;

    @Mapping("id")
    @JsonProperty("id")
    private Long key;

    private String firstName;
    private String lastName;
    private String address;
    private String gender;

    public PersonVo() {
    }
    public Long getKey() {
        return key;
    }
    public void setKey(Long key) {
        this.key = key;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof PersonVo personVo)) return false;
        return Objects.equals(key, personVo.key) && Objects.equals(firstName, personVo.firstName) && Objects.equals(lastName, personVo.lastName) && Objects.equals(address, personVo.address) && Objects.equals(gender, personVo.gender);
    }

    @Override
    public int hashCode() {
        return Objects.hash(key, firstName, lastName, address, gender);
    }
}
