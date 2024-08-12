package ifrs.service.entity;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;

@Entity
@DiscriminatorValue("editor")
public class Editor extends User {

    public Editor() {
        setAccessLv("editor");
    }

    public Editor(String name, String password) {
        setName(name);
        setPassword(password);
        setAccessLv("editor");
    }
}
