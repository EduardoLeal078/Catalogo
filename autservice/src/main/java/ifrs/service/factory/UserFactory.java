package ifrs.service.factory;
import ifrs.service.entity.*;

public class UserFactory {
    public static User createUser(String name, String password, String accessLv) {
        if (name == null || password == null || accessLv == null) {
            throw new IllegalArgumentException("Nome, senha e nível de acesso são obrigatórios.");
        }

        User user;
        switch (accessLv.toUpperCase()) {
            case "ADMIN":
                user = new Admin();
                break;
            case "EDITOR":
                user = new Editor();
                break;
            case "READER":
                user = new Reader();
                break;
            default:
                throw new IllegalArgumentException("Nível de acesso inválido: " + accessLv);
        }

        user.setName(name);
        user.setPassword(password);
        user.setAccessLv(accessLv);

        return user;
    }
}

