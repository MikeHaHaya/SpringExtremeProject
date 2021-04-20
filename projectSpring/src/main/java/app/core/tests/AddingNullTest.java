package app.core.tests;

import app.core.exceptions.ServiceException;
import app.core.services.AdminService;
import org.springframework.context.ConfigurableApplicationContext;

public class AddingNullTest {

    public static void runTest(ConfigurableApplicationContext context) {

        AdminService service = context.getBean(AdminService.class);
        try {

            service.addNewCompany(null);
        } catch (ServiceException e) {
            e.printStackTrace();
        }
    }
}
