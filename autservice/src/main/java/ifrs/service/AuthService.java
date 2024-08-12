package ifrs.service;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import java.util.List;
import ifrs.service.entity.User;
import ifrs.service.factory.UserFactory;
import ifrs.service.repository.UserRepository;
import jakarta.enterprise.context.ApplicationScoped;
import java.util.logging.Logger;

@ApplicationScoped
public class AuthService {
    private static final Logger logger = Logger.getLogger(AuthService.class.getName());
    @Inject
    private UserRepository userRepository;

    @Transactional
    public void register(String name, String password, String accessLv) {
        try {
            User user = UserFactory.createUser(name, password, accessLv);
            userRepository.persist(user);
            logger.info("Usuario registrado com sucesso: " + name);
            
        } catch (Exception e) {
            throw new RuntimeException("Erro ao registrar usuario",e);
        }
    }

    public boolean login(String name, String password) {
        logger.info("Recebendo solicitação de login para: " + name);
        logger.info("Valores recebidos - Nome: " + name + ", Senha: " + (password != null ? "****" : "null"));
        if (name == null || password == null) {
            logger.warning("Nome de usuário ou senha nulos recebidos");
            return false;
        }
        
        logger.info("Tentativa de login para o usuario: " + name);
        User user = userRepository.findByName(name);
        logger.info("Resultado da busca pelo usuario: " + (user != null ? "Usuario encontrado" : "Usuario nao encontrado"));
        
        if(user == null) {
            logger.warning("Usuario nao encontrado");
            return false;
        }
        if(!user.getPassword().equals(password)) {
            logger.warning("Senha invalida");
            return false;
        }
        return true;
    }
    public List<User> listAll() {
        return userRepository.listAll();
    }
    
}
