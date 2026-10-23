package com.scaler.splitwisemorningjuly26.Commands;

public interface Command {
    boolean matchCommand(String input);
    void execute(String input);
}
