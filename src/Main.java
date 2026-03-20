import collection.TicketCollection;
import data.Ticket;
import commands.*;
import org.jline.reader.*;
import org.fusesource.jansi.AnsiConsole;
import org.jline.terminal.Terminal;
import org.jline.terminal.TerminalBuilder;

import java.io.IOException;
import java.util.*;


public class Main {
    public static void main(String[] args) {
        if (args.length == 0) {
            System.out.println("Ошибка: не указан файл с данными.");
            return;
        }
        String filename = args[0];
        System.out.println("Загрузка данных из файла: " + filename);
        TicketCollection collection = loadCollection(filename);
        commands.ManagerOfCommands commandManager = new commands.ManagerOfCommands(collection, filename);
        runInteractiveMode(commandManager);
    }
    private static TicketCollection loadCollection(String filename) {
        try {
            ArrayDeque<Ticket> loadedCollection = util.Parser.parseFile(filename);
            System.out.println("Загружено " + loadedCollection.size() + " билетов.");
            return new TicketCollection(loadedCollection);
        } catch (IOException e) {
            System.out.println("Ошибка при загрузке файла: " + e.getMessage());
            System.out.println("Будет создана пустая коллекция.");
            return new TicketCollection();
        }
    }
    private static void runInteractiveMode(commands.ManagerOfCommands managerOfCommands) {
        AnsiConsole.systemInstall();

        System.setProperty("org.jline.terminal.dumb", "false");
        System.setProperty("org.jline.terminal.ansi", "true");


        System.out.println("\nНачало работы");
        System.out.println("Введите 'help' для списка команд.");
        System.out.println("Для выхода введите 'exit'.");


        try {
            Terminal terminal = TerminalBuilder.builder().jansi(true).system(true).build();
            // Создаем комплетер для команд
            Completer completer = (reader, line, candidates) -> {

                String buffer = line.line();
                String[] parts = buffer.split("\\s+");

                if (parts.length <= 1) {
                    // Дополняем команды
                    for (String cmd : managerOfCommands.getCommandMap().keySet()) {
                        if (cmd.startsWith(parts[0])) {
                            candidates.add(new Candidate(cmd));
                        }
                    }
                } else {
                    // Здесь можно добавить дополнение для аргументов
                    // Например, для remove_by_id можно предложить ID
                    String command = parts[0];
                    if (command.equals("remove_by_id") || command.equals("update")) {
                        // Предложить существующие ID из коллекции
                        // Это сложнее, нужно получать коллекцию
                    }
                }
            };

            LineReader reader = LineReaderBuilder.builder()
                    .terminal(terminal)
                    .completer(completer)
                    .option(LineReader.Option.AUTO_MENU, true)           // Автоматически показывать меню
                    .option(LineReader.Option.AUTO_FRESH_LINE, true)     // Обновлять строку
                    .option(LineReader.Option.USE_FORWARD_SLASH, true)   // Использовать /
                    .variable(LineReader.BELL_STYLE, "none")              // Без звука
                    .build();

            boolean running = true;

            while (running) {
                try {
                    String line = reader.readLine("> ");
                    if (line == null) continue;

                    String trimmed = line.trim();
                    if (trimmed.isEmpty()) continue;

                    String[] parts = trimmed.split("\\s+", 2);
                    String commandName = parts[0];
                    String arg = parts.length > 1 ? parts[1] : "";

                    running = managerOfCommands.executeCommand(commandName, arg);
                } catch (UserInterruptException e) {
                    System.out.println("CtrlC");
                    break;
                } catch (EndOfFileException e) {
                    System.out.println("CtrlD");
                    break;
                }
            }
            terminal.close();
        } catch (IOException e){
            System.out.println("Ошибка терминала");
            runFallback(managerOfCommands);
        }
        System.out.println("\nПрограмма завершена");
        AnsiConsole.systemUninstall();
    }
    private static void runFallback(ManagerOfCommands commandManager) {
        System.out.println("\nОбычный режим");

        try (Scanner scanner = new Scanner(System.in)) {
            boolean running = true;

            while (running) {
                System.out.print("> ");
                if (!scanner.hasNextLine()) break;

                String line = scanner.nextLine().trim();
                if (line.isEmpty()) continue;

                String[] parts = line.split("\\s+", 2);
                String commandName = parts[0];
                String arg = parts.length > 1 ? parts[1] : "";

                running = commandManager.executeCommand(commandName, arg);
            }
        }
    }
}


