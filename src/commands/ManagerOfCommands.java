package commands;

import collection.TicketCollection;
import java.util.*;

/**
 * Менеджер команд - центральный класс для управления и выполнения команд приложения.
 * <p>
 * Отвечает за:
 * <ul>
 *   <li>Регистрацию всех доступных команд</li>
 *   <li>Хранение карты команд для быстрого доступа по имени</li>
 *   <li>Выполнение команд с передачей им необходимого контекста</li>
 *   <li>Отслеживание выполняемых скриптов для предотвращения рекурсии</li>
 *   <li>Обработку ошибок при выполнении команд</li>
 * </ul>
 *
 * <p>Менеджер команд является связующим звеном между пользовательским вводом
 * и конкретными реализациями команд.</p>
 *
 * @see Command
 * @see TicketCollection
 */
public class ManagerOfCommands {
    /** Карта команд, где ключ - имя команды, значение - объект команды */
    private final Map<String, Command> commandMap = new HashMap<>();
    /** Коллекция билетов, с которой работают команды */
    private final TicketCollection collection;
    /** Имя файла для сохранения/загрузки коллекции */
    private final String filename;
    /** Множество имён файлов скриптов, выполняемых в данный момент (для защиты от рекурсии) */
    private final Set<String> scriptFile = new HashSet<>();

    /**
     * Конструктор менеджера команд.
     *
     * @param collection коллекция билетов для работы команд
     * @param filename   имя файла для сохранения/загрузки коллекции
     */
    public ManagerOfCommands(TicketCollection collection, String filename) {
        this.collection = collection;
        this.filename = filename;
        addAllCommands();
    }

    /**
     * Регистрирует одну команду в карте команд.
     *
     * @param command объект команды для регистрации
     */
    private void addOneCommand(Command command) {
        commandMap.put(command.getName(), command);
    }

    /**
     * Регистрирует все доступные команды приложения.
     * <p>
     * Создаёт экземпляры всех команд и добавляет их в карту команд.
     * </p>
     *
     */
    private void addAllCommands() {
        addOneCommand(new HelpCommand(this));
        addOneCommand(new InfoCommand());
        addOneCommand(new ShowCommand());
        addOneCommand(new AddCommand());
        addOneCommand(new UpdateCommand());
        addOneCommand(new RemoveByIDCommand());
        addOneCommand(new ClearCommand());
        addOneCommand(new SaveCommand(filename));
        addOneCommand(new ExecuteScriptCommand(this));
        addOneCommand(new ExitCommand());
        addOneCommand(new HeadCommand());
        addOneCommand(new RemoveHeadCommand());
        addOneCommand(new RemoveGreaterCommand());
        addOneCommand(new RemoveAllByPriceCommand());
        addOneCommand(new RemoveAnyByTypeCommand());
        addOneCommand(new MinByVenueCommand());
    }

    /**
     * Выполняет команду по её имени с переданным аргументом.
     * <p>
     * Процесс выполнения:
     * </p>
     * <ol>
     *   <li>Поиск команды в карте по имени (регистронезависимый)</li>
     *   <li>Если команда не найдена - вывод сообщения об ошибке</li>
     *   <li>Установка контекста (коллекция и аргумент) для команды</li>
     *   <li>Проверка валидности аргументов через {@link Command#valArgs()}</li>
     *   <li>Выполнение команды с обработкой возможных исключений</li>
     * </ol>
     *
     * @param commandName имя команды для выполнения (регистронезависимо)
     * @param arg         аргумент команды (может быть пустой строкой)
     * @return {@code false} если выполнена команда "exit", иначе {@code true}
     */
    public boolean executeCommand(String commandName, String arg) {
        Command command = commandMap.get(commandName.toLowerCase());
        if (command == null) {
            System.out.println("Неизвестная команда: " + commandName);
            return true;
        }
        command.setContext(collection, arg);
        if (!command.valArgs()) {
            System.out.println("Неверные аргументы. Использование: " + command.getUsage());
            return true;
        }
        try {
            command.execute();
        } catch (Exception e) {
            System.out.println("Ошибка при выполнении: " + e.getMessage());
        } catch (IllegalAccessError e) {
            System.out.println("Ошибка данных: " + e.getMessage());
        }

        return !commandName.equals("exit");
    }

    /**
     * Возвращает карту всех зарегистрированных команд.
     *
     * @return карта команд (ключ - имя команды, значение - объект команды)
     */
    public Map<String, Command> getCommandMap() {
        return commandMap;
    }

    /**
     * Возвращает коллекцию билетов, с которой работают команды.
     *
     * @return объект {@link TicketCollection}
     */
    public TicketCollection getCollection() {
        return collection;
    }

    /**
     * Возвращает множество имён файлов скриптов, выполняемых в данный момент.
     *
     * @return множество имён файлов скриптов в стеке выполнения
     */
    public Set<String> getScriptFile() {
        return scriptFile;
    }
}