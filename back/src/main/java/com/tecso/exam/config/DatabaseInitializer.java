package com.tecso.exam.config;

import com.tecso.exam.domain.*;
import com.tecso.exam.service.*;
import lombok.AllArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Component
@AllArgsConstructor
@Profile("dev")
public class DatabaseInitializer implements CommandLineRunner {

    private UserService userService;
    private JuridicPersonService juridicPersonService;
    private PhysicalPersonService physicalPersonService;
    private AccountService accountService;
    private MovementService movementService;

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

        Account account = new Account(123L, Currency.USD, BigDecimal.valueOf(1500));
        Account savedAccount = accountService.save(account);

        Movement movement = new Movement(LocalDateTime.of(2020, 05, 02, 0,0), MovementType.DEBIT, "", BigDecimal.TEN, savedAccount);
        Movement movement2 = new Movement(LocalDateTime.of(2020, 06, 02, 0,0), MovementType.DEBIT, "", BigDecimal.TEN, savedAccount);

        movementService.save(movement);
        movementService.save(movement2);

    }
}
