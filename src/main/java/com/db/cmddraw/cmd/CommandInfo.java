package com.db.cmddraw.cmd;

import org.apache.commons.lang3.StringUtils;

import java.util.ArrayList;
import java.util.Collections;
import java.util.EnumSet;
import java.util.List;

public class CommandInfo {

    public enum CommandKind {
        CANVAS('C'),
        LINE('L'),
        RECT('R'),
        QUIT('Q'),
        UNKNOWN(null);

        private final Character kind;

        public Character getKind() {
            return this.kind;
        }

        CommandKind(Character kind) {
            this.kind = kind;
        }

        public static CommandKind of(char c) {
            return EnumSet.allOf(CommandKind.class).stream().filter(ck -> ck != UNKNOWN && ck.getKind() == c).findFirst().orElse(CommandKind.UNKNOWN);
        }
    }

    public static final CommandInfo UNKNOWN_COMMAND = new CommandInfo(CommandKind.UNKNOWN, Collections.emptyList());
    private final CommandKind kind;
    private final List<String> params;

    public CommandInfo(CommandKind kind, List<String> params) {
        this.kind = kind;
        this.params = params;
    }

    public List<String> getParams() {
        return params;
    }

    public CommandKind getKind() {
        return kind;
    }

    public static CommandInfo parse(String cmd) {
        if (StringUtils.isBlank(cmd))
            return UNKNOWN_COMMAND;

        cmd = cmd.trim();
        String[] splitParams = StringUtils.split(cmd, " ");

        CommandKind commandKind = CommandKind.of(splitParams[0].charAt(0));
        if (commandKind == CommandKind.UNKNOWN)
            return UNKNOWN_COMMAND;

        List<String> listParams = new ArrayList<>();
        for (int i = 1; i < splitParams.length; i++) {
            if (!StringUtils.isEmpty(splitParams[i]))
                listParams.add(splitParams[i]);
        }

        return new CommandInfo(commandKind, listParams);
    }
}
