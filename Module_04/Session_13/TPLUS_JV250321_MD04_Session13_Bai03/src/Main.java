import java.util.Arrays;
import java.util.List;
import java.util.Optional;

public class Main {
    public static void main(String[] args) {
        List<User> users = Arrays.asList(
                new User("alice", "alice@example.com"),
                new User("bob", "bob@example.com"),
                new User("charlie", "charlie@example.com")
        );

        Optional<User> userOptional = findUserByUserName(users, "bob");

        // Sử dụng isPresent
        if (userOptional.isPresent()) {
            System.out.println("Người dùng tìm thấy: " + userOptional.get());
        } else {
            System.err.println("Không tìm thấy người dùng");
        }

        // Sử dụng orElse
        User defaultUser = userOptional.orElse(
                new User("default", "default@example.com")
        );
        System.out.println("Người dùng mặc định nếu không tìm thấy: " + defaultUser);

        // Sử dụng ifPresent
        userOptional.ifPresent(user -> System.out.println("Email của người dùng: " + user.getEmail()));
    }

    public static Optional<User> findUserByUserName(List<User> users, String userName) {
        return users.stream()
                .filter((user -> user.getUserName().equalsIgnoreCase(userName)))
                .findFirst();
    }
}
