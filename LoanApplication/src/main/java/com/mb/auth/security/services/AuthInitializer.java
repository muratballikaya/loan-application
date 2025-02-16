package com.mb.auth.security.services;

import com.mb.auth.models.RoleEnum;
import com.mb.auth.models.Role;
import com.mb.auth.repository.RoleRepository;
import com.mb.auth.repository.UserRepository;
import com.mb.customer.repository.CustomerRepository;
import com.mb.loan.repository.LoanInstalmentRepository;
import com.mb.loan.repository.LoanRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

@Component
public class AuthInitializer implements
        ApplicationListener<ContextRefreshedEvent> {

    private static final String CLEAN_ON_START = "E";
    @Autowired
    RoleRepository roleRepository;
    @Autowired
    UserRepository userRepository;

    @Autowired
    CustomerRepository customerRepository;

    @Autowired
    LoanRepository loanRepository;

    @Autowired
    LoanInstalmentRepository loanInstalmentRepository;

    @Value("${cleanOnStart}")
    private String cleanOnStart;

    @Override
    public void onApplicationEvent(ContextRefreshedEvent event) {

        // reset user and roles if is set to be clean
        if(!CLEAN_ON_START.equals(cleanOnStart))
            return;

        userRepository.deleteAll();;
        roleRepository.deleteAll();

        roleRepository.save(new Role(RoleEnum.ROLE_USER));
        roleRepository.save(new Role(RoleEnum.ROLE_ADMIN));


        loanInstalmentRepository.deleteAll();;
        loanRepository.deleteAll();
        customerRepository.deleteAll();


    }
}
