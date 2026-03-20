package commands;

import data.Ticket;
import util.CreateID;
/**
 * Команда удаления элемента коллекции по уникальному идентификатору.
 * <p>
 * Реализует функциональность команды "remove_by_id". Удаляет из коллекции билет
 * с указанным ID.
 * </p>
 *
 * <p>Особенности работы:</p>
 * <ul>
 *   <li>Требует аргумент - ID билета для удаления (целое положительное число)</li>
 *   <li>Удаляет ТОЛЬКО ОДИН билет с указанным ID (уникальность ID гарантирует это)</li>
 *   <li>Перед удалением проверяет существование билета с таким ID</li>
 *   <li>ID билета должен быть больше 0 (согласно спецификации класса {@link Ticket})</li>
 *   <li>После удаления ID может быть повторно использован при создании новых билетов</li>
 * </ul>
 *
 * @see Command
 * @see Ticket
 * @see Ticket#getId()
 * @see CreateID#removeTicketID(int)
 */
public class RemoveByIDCommand extends Command {

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
     * @return строка с синтаксисом: "remove_by_id id"
     */
    @Override
    public String getUsage() {
        return "remove_by_id id";
    }

    /**
     * Находит билет по его уникальному идентификатору.
     * <p>
     * Вспомогательный метод для проверки существования билета
     * с заданным ID перед выполнением удаления.
     * </p>
     *
     * @param id идентификатор билета для поиска
     * @return найденный билет с указанным ID или {@code null}, если билет не найден
     */
    private Ticket findTicketById(int id) {
        for (Ticket ticket : collection.getCollection()) {
            if (ticket.getId() == id) {
                return ticket;
            }
        }
        return null;
    }

    /**
     * Выполняет команду удаления билета по ID.
     * <p>
     * Процесс выполнения:
     * </p>
     * <ol>
     *   <li>Парсит аргумент команды в целое число (int)</li>
     *   <li>Проверяет существование билета с таким ID через {@link #findTicketById}</li>
     *   <li>Если билет не найден - выводит сообщение и завершает выполнение</li>
     *   <li>Если билет найден - вызывает {@code collection.removeById(id)} для удаления</li>
     *   <li>Выводит подтверждение об успешном удалении</li>
     * </ol>
     *
     * <p>Обработка ошибок:</p>
     * <ul>
     *   <li>При {@link NumberFormatException} (аргумент не является числом) - выводит сообщение об ошибке</li>
     * </ul>
     */
    @Override
    public void execute() {
        try {
            int id = Integer.parseInt(arg);
            Ticket ticket = findTicketById(id);
            if (ticket == null) {
                System.out.println("Билета с таким ID не существует " + id);
                return;
            }
            collection.removeById(id);
            System.out.println("Билет удален");
        } catch (NumberFormatException e) {
            System.out.println("Ошибка в ID. Введите число");
        }
    }

    /**
     * Возвращает описание команды.
     *
     * @return строковое описание функциональности команды
     */
    @Override
    public String getDescription() {
        return "Удаление элемента из коллекции по ID";
    }

    /**
     * Возвращает имя команды.
     *
     * @return строковое имя команды для вызова ("remove_by_id")
     */
    @Override
    public String getName() {
        return "remove_by_id";
    }
}