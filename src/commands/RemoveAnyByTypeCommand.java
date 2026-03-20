package commands;

import data.Ticket;
import data.TicketType;

/**
 * Команда удаления одного элемента коллекции по указанному типу билета.
 * <p>
 * Реализует функциональность команды "remove_any_by_type". Удаляет из коллекции один билет
 * (любой) с указанным типом.
 * </p>
 *
 * <p>Особенности работы:</p>
 * <ul>
 *   <li>Требует аргумент - тип билета (VIP, USUAL, BUDGETARY, CHEAP)</li>
 *   <li>Удаляет ТОЛЬКО ОДИН билет с указанным типом</li>
 *   <li>Если билетов с указанным типом несколько, удаляется только один</li>
 *   <li>Перед удалением проверяет существование хотя бы одного билета с таким типом</li>
 * </ul>
 *
 * @see Command
 * @see Ticket
 * @see TicketType
 */
public class RemoveAnyByTypeCommand extends Command {

    /**
     * Проверяет наличие аргумента команды.
     *
     * @return {@code true} если аргумент не пустой, иначе {@code false}
     */
    @Override
    public boolean valArgs() {
        return !arg.isEmpty();
    }

    /**
     * Возвращает информацию об использовании команды.
     *
     * @return строка с синтаксисом: "remove_any_by_type type"
     */
    @Override
    public String getUsage() {
        return "remove_any_by_type type";
    }

    /**
     * Находит первый билет с указанным типом.
     * <p>
     * Вспомогательный метод для проверки существования хотя бы одного билета
     * заданного типа перед выполнением удаления.
     * </p>
     *
     * @param type тип билета для поиска
     * @return первый найденный билет с указанным типом или {@code null}, если таких билетов нет
     */
    private Ticket findTicketByType(TicketType type) {
        for (Ticket ticket : collection.getCollection()) {
            if (ticket.getType() == type) {
                return ticket;
            }
        }
        return null;
    }

    /**
     * Выполняет команду удаления одного билета с указанным типом.
     * <p>
     * Процесс выполнения:
     * </p>
     * <ol>
     *   <li>Преобразует аргумент в верхний регистр и парсит в значение {@link TicketType}</li>
     *   <li>Проверяет существование хотя бы одного билета с таким типом через {@link #findTicketByType}</li>
     *   <li>Если билеты не найдены - выводит сообщение и завершает выполнение</li>
     *   <li>Если билеты найдены - вызывает {@code collection.removeByType(type)} для удаления ОДНОГО билета</li>
     *   <li>Выводит подтверждение об успешном удалении</li>
     * </ol>
     *
     * <p>Обработка ошибок:</p>
     * <ul>
     *   <li>При {@link IllegalArgumentException} (неверный тип) - выводит сообщение об ошибке</li>
     * </ul>
     */
    @Override
    public void execute() {
        try {
            TicketType type = TicketType.valueOf(arg.toUpperCase());
            Ticket ticket = findTicketByType(type);
            if (ticket == null) {
                System.out.println("Билета с таким типом не существует " + type);
                return;
            }
            collection.removeByType(type);
            System.out.println("Билет удален");
        } catch (IllegalArgumentException e) {
            System.out.println("Ошибка в типе. Введите еще раз");
        }
    }

    /**
     * Возвращает описание команды.
     *
     * @return строковое описание функциональности команды
     */
    @Override
    public String getDescription() {
        return "Удаление элемента из коллекции по типу";
    }

    /**
     * Возвращает имя команды.
     *
     * @return строковое имя команды для вызова ("remove_any_by_type")
     */
    @Override
    public String getName() {
        return "remove_any_by_type";
    }
}