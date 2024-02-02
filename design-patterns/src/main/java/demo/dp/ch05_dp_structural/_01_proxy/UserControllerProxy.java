package demo.dp.ch05_dp_structural._01_proxy;

public class UserControllerProxy extends UserController {

    public String login(String username, String passwrod) {
        System.out.println("before login");
        String result = super.login(username, passwrod);
        System.out.println("after login");
        return result;
    }

    public static void main(String[] args) {
        UserControllerProxy proxy = new UserControllerProxy();
        String result = proxy.login("zhangsan", null);
        System.out.println(result);
    }
}
