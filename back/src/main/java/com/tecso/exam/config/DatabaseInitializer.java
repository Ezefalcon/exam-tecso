package com.tecso.exam.config;

import com.tecso.exam.domain.JuridicPerson;
import com.tecso.exam.domain.PhysicalPerson;
import com.tecso.exam.domain.User;
import com.tecso.exam.service.JuridicPersonService;
import com.tecso.exam.service.PhysicalPersonService;
import com.tecso.exam.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
@Profile("dev")
public class DatabaseInitializer implements CommandLineRunner {

    private UserService userService;
    private JuridicPersonService juridicPersonService;
    private PhysicalPersonService physicalPersonService;

    @Override
    public void run(String... args) throws Exception {
        // Users
        User user = new User("123", "admin", "admin", "admin", "admin", true);
        this.userService.save(user);


        for(int i = 0; i < 5; i++) {
            // JuridicPerson
            JuridicPerson juridicPerson = new JuridicPerson("1" + i, "Tecso"+i, 2021 + i);
            this.juridicPersonService.save(juridicPerson);

            // PhysicalPerson
            PhysicalPerson physicalPerson = new PhysicalPerson("2" +i, "Juan " + i, "Martin", "cc");
            this.physicalPersonService.save(physicalPerson);
        }



    }
}
