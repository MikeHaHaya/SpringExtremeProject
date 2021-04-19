package app.core.login;

import app.core.exceptions.LoginException;
import app.core.services.AdminService;
import app.core.services.ClientService;
import app.core.services.CompanyService;
import app.core.services.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

@Scope("singleton")
public class LoginManager {

    @Autowired
    private AdminService adminService;
    @Autowired
    private CompanyService companyService;
    @Autowired
    private CustomerService customerService;

    /**
     * Empty Constructor
     */
    public LoginManager() {
    }

    public ClientService login(String email, String password, ClientType clientType) throws LoginException {

        ClientService service = null;

        switch (clientType) {

            case Administrator:
                service = adminService;
                break;
            case Company:
                service = companyService;
                break;
            case Customer:
                service = customerService;
                break;
            default:
                throw new LoginException("Failed to log in, client has to be either an administrator, a company or a customer.");
        }

        if (service.login(email, password))
            return service;
        else
            throw new LoginException("Failed to log in, the email or password you entered is incorrect. ");

    }
}