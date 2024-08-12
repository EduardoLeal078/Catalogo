package ifrs.repository;

import ifrs.entity.Content;
import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class ContRepository implements PanacheRepository<Content> {
    
}
