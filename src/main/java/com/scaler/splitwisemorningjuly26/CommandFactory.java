package com.scaler.splitwisemorningjuly26;

import com.scaler.splitwisemorningjuly26.Commands.Command;
import com.scaler.splitwisemorningjuly26.Commands.Login;
import com.scaler.splitwisemorningjuly26.Commands.RegisterCommand;

public class CommandFactory {
    public static Command getCommand(String input){
        String commandName = input.split(" ")[0];
        if(commandName.equalsIgnoreCase("register")){
            return new RegisterCommand();
        }else if (commandName.equalsIgnoreCase("login")){
            return new Login();
        }else{
            throw  new IllegalArgumentException("Invalid command name");
        }
    }
}
