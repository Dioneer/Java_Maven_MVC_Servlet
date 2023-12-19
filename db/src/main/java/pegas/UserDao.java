package pegas;

import java.util.Optional;

public class UserDao {
    public Optional<User> findById(long id){
        User user = new User();
        user.setName("Igor");
        return Optional.of(user);
    }
}
