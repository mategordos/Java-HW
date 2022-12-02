package com.epam.training.ticketservice.ui.command;

import com.epam.training.ticketservice.core.user.UserService;
import com.epam.training.ticketservice.core.user.model.UserDto;
import java.util.Optional;
import lombok.AllArgsConstructor;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import org.springframework.shell.standard.commands.Quit;

@ShellComponent
@AllArgsConstructor
public class UserCommand {

    private final UserService userService;

    @ShellMethod(key = "sign out", value = "Signing out")
    public String signOut() {
        Optional<UserDto> user = userService.logout();
        if (user.isEmpty()) {
            return "You need to login first!";
        }
        return user.get() + " is logged out!";
    }

    @ShellMethod(key = "sign in privileged", value = "Signing in with admin")
    public String signIn(String username, String password) {
        Optional<UserDto> user = userService.login(username, password);
        if (user.isEmpty()) {
            return "Login failed due to incorrect credentials";
        }
        return "Successful login!";
    }

    @ShellMethod(key = "describe account", value = "Get account description")
    public String describeAccount() {
        Optional<UserDto> user = userService.describe();
        if (user.isEmpty()) {
            return "You are not signed in";
        }
        return "Signed in with privileged account '" + user.get().getUsername() + "'";
    }


}