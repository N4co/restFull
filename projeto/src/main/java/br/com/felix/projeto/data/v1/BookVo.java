package br.com.felix.projeto.data.v1;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.github.dozermapper.core.Mapping;
import org.springframework.hateoas.RepresentationModel;

import java.io.Serializable;
import java.util.Date;
import java.util.Objects;


@JsonPropertyOrder("{id, author, launch_date, price, title }")
public class BookVo extends RepresentationModel<BookVo> implements Serializable {
    private static final long serialVersionUID = 1L;

    @JsonProperty("id")
    @Mapping("id")
    private Long key;


    private String author;

    private Date launch_date;
    private Double price;

    private String title;

    public BookVo() {

    }


    public Long getKey() {
        return key;
    }

    public void setKey(Long key) {
        this.key = key;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public Date getLaunchDate() {
        return launch_date;
    }

    public void setLaunchDate(Date launchDate) {
        this.launch_date = launchDate;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof BookVo book)) return false;
        return Objects.equals(key, book.key) && Objects.equals(author, book.author) && Objects.equals(launch_date, book.launch_date) && Objects.equals(price, book.price) && Objects.equals(title, book.title);
    }

    @Override
    public int hashCode() {
        return Objects.hash(key, author, launch_date, price, title);
    }
}
