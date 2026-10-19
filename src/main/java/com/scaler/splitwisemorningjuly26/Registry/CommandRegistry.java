package com.scaler.splitwisemorningjuly26.Registry;

import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
@Component
public class CommandRegistry {
    List<Command> commands  = new ArrayList<>();
    public void addCommand(Command command){
        commands.add(command);
    }
    public void removeCommand(Command command){
        commands.remove(command);
    }
    public void matchAndExecute(String input){
        for(Command command : commands){
            if(command.matches(input)){
                command.execute(input);
            }
        }
    }
}
