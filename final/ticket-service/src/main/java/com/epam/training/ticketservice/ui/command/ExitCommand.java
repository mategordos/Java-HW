package com.epam.training.ticketservice.ui.command;

import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import org.springframework.shell.standard.commands.Quit;

@ShellComponent
public class ExitCommand implements Quit.Command {
    @ShellMethod(key = {"exit"}, value = "Exit the shell.")
    public void exit() {
        System.exit(0);
    }
}
