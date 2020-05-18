package questions;

import java.util.EnumMap;

public class ModellingActions {

    public interface Command {
        void execute();
    }

    public static class InstallCommand implements Command {

        @Override
        public void execute() {
            System.out.println("Executed Install Command");
        }
    }

    public static class StartCommand implements Command {

        @Override
        public void execute() {
            System.out.println("Executed Start Command");
        }
    }

    public static class StopCommand implements Command {

        @Override
        public void execute() {
            System.out.println("Executed Stop Command");
        }
    }

    public static class RestartCommand implements Command {

        @Override
        public void execute() {
            System.out.println("Executed Restart Command");
        }
    }

    public enum CommandIdentifier {
        INSTALL,
        START,
        STOP,
        RESTART;
    }

    public static class CommandControl {
        EnumMap<CommandIdentifier, Command> _commandMap;

        public CommandControl() {
            _commandMap = new EnumMap<>(CommandIdentifier.class);
        }

        public void registerCommand(CommandIdentifier commandIdentifier, Command command) {
            _commandMap.put(commandIdentifier, command);
        }

        public void execute(CommandIdentifier commandIdentifier) {
            if(_commandMap.containsKey(commandIdentifier))
                _commandMap.get(commandIdentifier).execute();
        }
    }

    public static void main(String[] args) {
        CommandControl commandControl = new CommandControl();
        commandControl.registerCommand(CommandIdentifier.INSTALL, new InstallCommand());
        commandControl.registerCommand(CommandIdentifier.START, new StartCommand());
        commandControl.registerCommand(CommandIdentifier.STOP, new StopCommand());
        commandControl.registerCommand(CommandIdentifier.RESTART, new RestartCommand());

        commandControl.execute(CommandIdentifier.INSTALL);
        commandControl.execute(CommandIdentifier.START);
        commandControl.execute(CommandIdentifier.STOP);
        commandControl.execute(CommandIdentifier.RESTART);
    }
}
