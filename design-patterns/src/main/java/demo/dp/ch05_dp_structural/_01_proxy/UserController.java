package demo.dp.ch05_dp_structural._01_proxy;

public class UserController implements IUserController {
    @Override
    public String login(String username, String password) {
        System.out.println("do login in UserController");
        return "hello, " + username;
    }
}
