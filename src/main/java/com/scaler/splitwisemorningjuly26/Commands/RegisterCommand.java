package com.scaler.splitwisemorningjuly26.Commands;

import com.scaler.splitwisemorningjuly26.Controllers.UserController;
import com.scaler.splitwisemorningjuly26.DTO.UserRegistrationRequestDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.List;

@Component
public class RegisterCommand implements Command{
    final private String command = "register";
    @Autowired
    UserController userController;
    @Override
    public boolean matchCommand(String input) {
        String inputCommand = Arrays.asList(input.split(" ")).get(0);
        return inputCommand.equalsIgnoreCase(command);
    }

    @Override
    public void execute(String input) {
        List<String> inputs = Arrays.asList(input.split(" "));
        String username = inputs.get(1);
        String password = inputs.get(2);
        String email = inputs.get(3);
        userController.register(new UserRegistrationRequestDTO(username,password,email));
    }
}
// Spring boots - Object lifecycle