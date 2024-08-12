package ifrs.service.entity;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.*;


@Entity
@DiscriminatorValue("admin")
public class Admin extends User {

    public Admin() {
    }
    public Admin(String name, String password) {
        setName(name);
        setPassword(password);
        setAccessLv("admin");

    }
    
}
