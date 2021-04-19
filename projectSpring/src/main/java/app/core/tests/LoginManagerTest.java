package app.core.tests;

import app.core.entities.Company;
import app.core.exceptions.CouponSystemException;
import app.core.login.ClientType;
import app.core.login.LoginManager;
import app.core.services.AdminService;
import app.core.services.ClientService;
import org.springframework.context.ConfigurableApplicationContext;

public class LoginManagerTest {

    public static void runTest(ConfigurableApplicationContext context) {

        LoginManager manager = context.getBean(LoginManager.class);
        try {
            ClientService service = manager.login("admin@admin.com", "admin", ClientType.Administrator);
            if (service instanceof AdminService) {
                ((AdminService) service).addNewCompany(new Company(2, "Ester", "email@email", "password1234"));
            }
            System.out.println("we did it boys, login manager works");
        } catch (CouponSystemException e) {
            System.out.println(e.getMessage());
            e.printStackTrace();
        }

    }
}
