package com.scaler.splitwisemorningjuly26.Commands;

import org.springframework.stereotype.Component;

@Component
public class Login implements Command{
    @Override
    public boolean matchCommand(String input) {
        return false;
    }

    @Override
    public void execute(String input) {

    }
}
