package ifrs.service;

import ifrs.entity.Content;
import ifrs.repository.ContRepository;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@ApplicationScoped
public class ContService {

    @Inject
    ContRepository contRepository;

    public List<Content> listAllCont() {
        return contRepository.listAll();
    }

    @Transactional
    public void addCont(Content cont) {
        if (cont == null) {
            throw new IllegalArgumentException("Content cannot be null");
        }

        if (cont.getId() != null) {
            Content existingCont = contRepository.findById(cont.getId());
            if (existingCont != null) {
                contRepository.getEntityManager().merge(cont);
            } else {
                contRepository.persist(cont);
            }
        } else {
            contRepository.persist(cont);
        }
    }

    @Transactional
    public boolean removeCont(Long id) {
        return contRepository.deleteById(id);
    }

    @Transactional
    public void removeAllCont() {
        contRepository.deleteAll();
    }

    @Transactional
    public Optional<Content> updateCont(Long id, Content updatedCont) {
        return contRepository.findByIdOptional(id).map(existingCont -> {
            existingCont.setTitle(updatedCont.getTitle());
            existingCont.setDescription(updatedCont.getDescription());
            existingCont.setContType(updatedCont.getContType());
            existingCont.setContData(updatedCont.getContData());
            contRepository.persist(existingCont);
            return existingCont;
        });
    }
    public Optional<Content> getCont(Long id) {
        return contRepository.findByIdOptional(id);
    }
}