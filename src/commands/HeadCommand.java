package commands;

import data.Ticket;

/**
 * Команда вывода первого элемента коллекции.
 * <p>
 * Реализует функциональность команды "head". Выводит первый билет из коллекции
 * (в порядке, определяемом текущей сортировкой коллекции).
 * </p>
 *
 * <p>Особенности работы:</p>
 * <ul>
 *   <li>Не требует аргументов</li>
 *   <li>Не изменяет коллекцию (только чтение)</li>
 *   <li>Если коллекция пуста, выводит соответствующее сообщение</li>
 * </ul>
 *
 * @see Command
 * @see Ticket
 */
public class HeadCommand extends Command {

    /**
     * Выполняет команду вывода первого элемента коллекции.
     * <p>
     * Получает первый билет из коллекции через метод {@code head()} менеджера коллекции.
     * </p>
     *
     */
    @Override
    public void execute() {
        Ticket ticket = collection.head();
        if (ticket == null) {
            System.out.println("Коллекция пустая");
        } else {
            System.out.println(ticket);
        }
    }

    /**
     * Возвращает описание команды.
     *
     * @return строковое описание функциональности команды
     */
    @Override
    public String getDescription() {
        return "Первый билет коллекции";
    }

    /**
     * Возвращает имя команды.
     *
     * @return строковое имя команды для вызова ("head")
     */
    @Override
    public String getName() {
        return "head";
    }
}