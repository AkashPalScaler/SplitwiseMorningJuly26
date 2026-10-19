package com.scaler.splitwisemorningjuly26;

import com.scaler.splitwisemorningjuly26.Controllers.UserController;
import com.scaler.splitwisemorningjuly26.Controllers.UserRegistratonRequestDTO;
import com.scaler.splitwisemorningjuly26.Registry.CommandRegistry;
import com.scaler.splitwisemorningjuly26.Registry.RegisterCommand;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

@SpringBootApplication
public class SplitwiseMorningJuly26Application implements CommandLineRunner {
    Scanner scanner = new Scanner(System.in);
    @Autowired
    CommandRegistry commandRegistry;
    public static void main(String[] args) {
        SpringApplication.run(SplitwiseMorningJuly26Application.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        setupCommandRegistry();
        System.out.println("Enter your command:");
        String command  = scanner.nextLine();
        commandRegistry.matchAndExecute(command);
    }

    private void setupCommandRegistry() {
        commandRegistry.addCommand(new RegisterCommand());
//        commandRegistry.addCommand(new LoginCommand());
//        commandRegistry.addCommand(new CreateGroupCommand());
    }
}

// CLI input pattern
// AWS SDK, ls -l, grep, git
// git commit -m "message"
// app_name command_name inputs

//command inputs

// create_group uid 3 m_id m_id m_id group_name group_desc
// register u_name u_email u_password
// create_expense uid name/desc group_id 2 paid_by_uid amount paid_by_uid amount 3 owed_by_uid amount owed_by_uid amount owed_by_uid amount