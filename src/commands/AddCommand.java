package commands;

import data.Ticket;
import util.InputReader;

/**
 * Команда добавления нового элемента в коллекцию.
 * <p>
 * Реализует функциональность команды "add". При выполнении запрашивает у пользователя
 * данные для нового билета (через {@link InputReader}) и добавляет созданный билет в коллекцию.
 * </p>
 *
 * <p>Команда поддерживает два режима работы:</p>
 * <ul>
 *   <li><b>Интерактивный режим</b> - данные запрашиваются у пользователя через консоль</li>
 *   <li><b>Режим скрипта</b> - данные читаются из файла скрипта</li>
 * </ul>
 *
 * @see Command
 * @see InputReader#readTicket()
 * @see InputReader#readTicket(String)
 */
public class AddCommand extends Command {

    /**
     * Выполняет команду добавления билета в коллекцию.
     * <p>
     * В зависимости от режима работы (интерактивный или скрипт) вызывает соответствующий
     * метод {@link InputReader} для чтения данных билета. После создания билета добавляет
     * его в коллекцию и выводит сообщение с присвоенным ID.
     * </p>
     *
     * <p>В режиме скрипта используется перегрузка {@link InputReader#readTicket(String)},
     * где имя билета передается из аргумента команды.</p>
     */
    @Override
    public void execute() {
        if (InputReader.isScriptRegime()) {
            Ticket ticket = InputReader.readTicket(arg);
            collection.addElement(ticket);
            System.out.println("Билет добавлен с ID: " + ticket.getId());
        } else {
            Ticket ticket = InputReader.readTicket();
            collection.addElement(ticket);
            System.out.println("Билет добавлен с ID: " + ticket.getId());
        }
    }

    /**
     * Возвращает описание команды.
     *
     * @return строковое описание функциональности команды
     */
    @Override
    public String getDescription() {
        return "Добавление элемента в коллекцию";
    }

    /**
     * Возвращает имя команды.
     *
     * @return строковое имя команды для вызова ("add")
     */
    @Override
    public String getName() {
        return "add";
    }
}