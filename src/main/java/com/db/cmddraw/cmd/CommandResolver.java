package com.db.cmddraw.cmd;

import com.db.cmddraw.cmd.impl.UnknownCommand;
import java.util.Set;

public class CommandResolver {
    private final Set<Command> commands;

    public CommandResolver(Set<Command> commands) {
        this.commands = commands;
    }

    public Command resolve(CommandInfo commandInfo) {
        return commands.stream()
                .filter(c -> c.support(commandInfo))
                .findFirst()
                .orElse(new UnknownCommand());
    }
}
