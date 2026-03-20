package commands;

import java.util.*;

/**
 * Команда вывода справки по доступным командам.
 * <p>
 * Реализует функциональность команды "help". Выводит список всех доступных команд
 * с их синтаксисом и описанием. Информация формируется на основе карты команд,
 * хранящейся в {@link ManagerOfCommands}.
 * </p>
 *
 * <p>Особенности работы:</p>
 * <ul>
 *   <li>Не требует аргументов</li>
 *   <li>Не изменяет коллекцию (только чтение)</li>
 *   <li>Для каждой команды выводит: "использование: описание"</li>
 *   <li>Автоматически включает все зарегистрированные в менеджере команды</li>
 * </ul>
 *
 * @see Command
 * @see ManagerOfCommands
 */
public class HelpCommand extends Command {

    /** Менеджер команд, содержащий карту всех доступных команд */
    private ManagerOfCommands manager;

    /**
     * Конструктор команды помощи.
     *
     * @param manager менеджер команд, из которого будет получена карта команд
     *                для отображения справочной информации
     */
    public HelpCommand(ManagerOfCommands manager) {
        this.manager = manager;
    }

    /**
     * Выполняет команду вывода справки. Выводит информацию о всех командах приложения.
     *
     * <p>Метод автоматически включает все команды, которые были зарегистрированы
     * в менеджере команд при инициализации приложения.</p>
     */
    @Override
    public void execute() {
        System.out.println("Всевозможные команды: ");
        for (Map.Entry<String, Command> entry : manager.getCommandMap().entrySet()) {
            Command cmmd = entry.getValue();
            System.out.println(cmmd.getUsage() + ": " + cmmd.getDescription());
        }
    }

    /**
     * Возвращает описание команды.
     *
     * @return строковое описание функциональности команды
     */
    @Override
    public String getDescription() {
        return "Вывод справочной информации по командам";
    }

    /**
     * Возвращает имя команды.
     *
     * @return строковое имя команды для вызова ("help")
     */
    @Override
    public String getName() {
        return "help";
    }
}