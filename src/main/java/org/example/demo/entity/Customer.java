package org.example.demo.entity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Getter

public class Customer{
    private String customer_id;
    private String name;
    private String email;
    private String contact;

    public Customer() {
    }

    public Customer(String customer_id, String name, String email, String contact) {
        this.customer_id = customer_id;
        this.name = name;
        this.email = email;
        this.contact = contact;
    }

    @Override
    public String toString() {
        return "Customer{" +
                "customer_id='" + customer_id + '\'' +
                ", name='" + name + '\'' +
                ", email='" + email + '\'' +
                ", contact='" + contact + '\'' +
                '}';
    }

    public String getCustomer_id() {
        return customer_id;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public String getContact() {
        return contact;
    }
    public void setCustomer_id(String customer_id) {
        this.customer_id = customer_id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setContact(String contact) {
        this.contact = contact;
    }


}
