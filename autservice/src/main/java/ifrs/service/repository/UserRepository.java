package ifrs.service.repository;
import ifrs.service.entity.User;
import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class UserRepository implements PanacheRepository<User> {
    public User findByName(String name) {
        return find("name", name).firstResult();
    }
    
}
