package api.model;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@Entity
@Table(name = "COOKIES")
public class Cookie implements Serializable {
    private Long id;
    private String name;
    private CookieType type;
    private double price;
    private int quantityOnStock;
    private double quantityOfSweeteners;

    private Set<Order> orders = new HashSet<>();
    private Set<User> users = new HashSet<>();
    private Set<Comment> comments = new HashSet<>();

    public Cookie() {
        //it is needed for hibernate, nothing more to be added
    }


    public Cookie(long id, String name, CookieType type, double price, double quantityOfSweeteners, int quantityOnStock) {
        this.id = id;
        this.name = name;
        this.type = type;
        this.price = price;
        this.quantityOfSweeteners = quantityOfSweeteners;
        this.quantityOnStock = quantityOnStock;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID_COOKIE")
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Column(name = "NAME", nullable = false)
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Enumerated(EnumType.STRING)
    @Column(name = "TYPE", nullable = false)
    public CookieType getType() {
        return type;
    }

    public void setType(CookieType type) {
        this.type = type;
    }

    @Column(name = "PRICE", nullable = false)
    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    @Column(name = "QUANTITY_ON_STOCK", nullable = false)
    public int getQuantityOnStock() {
        return quantityOnStock;
    }

    public void setQuantityOnStock(int quantityOnStock) {
        this.quantityOnStock = quantityOnStock;
    }

    @Column(name = "QUANTITY_OF_SWEETENERS", nullable = false)
    public double getQuantityOfSweeteners() {
        return quantityOfSweeteners;
    }

    public void setQuantityOfSweeteners(double quantityOfSweeteners) {
        this.quantityOfSweeteners = quantityOfSweeteners;
    }

    @ManyToMany(mappedBy = "favorites", fetch = FetchType.EAGER)
    public Set<User> getUsers() {
        return users;
    }

    public void setUsers(Set<User> users) {
        this.users = users;
    }

    @OneToMany(mappedBy = "cookie", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    public Set<Comment> getComments() {
        return comments;
    }

    public void setComments(Set<Comment> comments) {
        this.comments = comments;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Cookie cookie = (Cookie) o;
        return id == cookie.id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public String toString() {
        return "Cookie{" +
                "name='" + name + '\'' +
                '}';
    }
}
