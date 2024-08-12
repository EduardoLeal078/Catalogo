package ifrs.service.entity;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;

@Entity
@DiscriminatorValue("reader")
public class Reader extends User {

    public Reader() {
        setAccessLv("reader");
    }

    public Reader(String name, String password) {
        setName(name);
        setPassword(password);
        setAccessLv("reader");
    }
}
