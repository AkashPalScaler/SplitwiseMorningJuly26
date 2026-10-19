package com.scaler.splitwisemorningjuly26.Registry;

import com.scaler.splitwisemorningjuly26.Controllers.UserController;
import com.scaler.splitwisemorningjuly26.Controllers.UserRegistratonRequestDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.List;

@Component
public class RegisterCommand implements Command {
    final private String commandString = "register";
    @Autowired
    UserController userController;
    @Override
    public boolean matches(String input) {
        String command = Arrays.asList(input.split(" ")).get(0);
        return command.equalsIgnoreCase(commandString);
    }

    @Override
    public void execute(String input) {
        List<String> inputs = Arrays.asList(input.split(" "));
        String username = inputs.get(1);
        String email = inputs.get(2);
        String password = inputs.get(3);
        userController.register(new UserRegistratonRequestDTO(username, email, password));
    }
}
