package com.scaler.splitwisemorningjuly26.Registry;

public interface Command {
    boolean matches(String s);
    void execute(String input);
}
