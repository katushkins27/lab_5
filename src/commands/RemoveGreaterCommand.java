package commands;

import data.Coordinates;
import data.Ticket;
import data.TicketType;
import data.Venue;
import util.CreateID;
import util.InputReader;

import java.time.LocalDateTime;

/**
 * Команда удаления всех элементов коллекции, превышающих заданный.
 * <p>
 * Реализует функциональность команды "remove_greater". Создаёт эталонный билет на основе
 * введённых пользователем данных, а затем удаляет из коллекции все билеты, которые
 * больше этого эталонного согласно естественному порядку сравнения {@link Ticket}.
 * </p>
 *
 * <p>Особенности работы в разных режимах:</p>
 * <ul>
 *   <li><b>Интерактивный режим</b> - все данные запрашиваются у пользователя</li>
 *   <li><b>Режим скрипта</b> - название билета берётся из аргумента команды,
 *       остальные данные читаются из файла</li>
 * </ul>
 *
 * @see Command
 * @see Ticket
 * @see Ticket#compareTo(Ticket)
 * @see InputReader
 * @see CreateID
 */
public class RemoveGreaterCommand extends Command {

    /**
     * Возвращает информацию об использовании команды.
     *
     * @return строка с синтаксисом: "remove_greater"
     */
    @Override
    public String getUsage() {
        return "remove_greater";
    }

    /**
     * Выполняет команду удаления всех билетов, превышающих заданный.
     * <p>
     * Процесс выполнения в зависимости от режима:
     * </p>
     *
     * <p><b>Режим скрипта:</b></p>
     * <ol>
     *   <li>Проверяет наличие аргумента (названия билета)</li>
     *   <li>Выводит отладочное сообщение "[скрипт] remove_greater с билетом: [название]"</li>
     *   <li>Читает из файла через {@link InputReader}, создаёт временный билет для сравнения</li>
     * </ol>
     *
     * <p><b>Интерактивный режим:</b></p>
     * <ol>
     *   <li>Выводит приглашение "Введите данные"</li>
     *   <li>Запрашивает у пользователя:
     *       <ul>
     *         <li>Название билета</li>
     *         <li>Координаты</li>
     *         <li>Цену</li>
     *         <li>Тип билета</li>
     *         <li>Место проведения</li>
     *       </ul>
     *   </li>
     *   <li>Создаёт временный билет для сравнения</li>
     * </ol>
     *
     * <p><b>Общие шаги для обоих режимов:</b></p>
     * <ol>
     *   <li>Вызывает {@code collection.removeAllGreater(cmpticket)} для удаления билетов</li>
     *   <li>Выводит результат:
     *       <ul>
     *         <li>Если удалено 0 билетов - сообщение об отсутствии подходящих билетов</li>
     *         <li>Иначе - сообщение "Билеты удалены"</li>
     *       </ul>
     *   </li>
     *   <li>Освобождает ID временного билета через {@link CreateID#removeTicketID(int)}</li>
     *   <li>Если у временного билета есть Venue, освобождает и его ID</li>
     * </ol>
     *
     * <p>Обработка ошибок:</p>
     * <ul>
     *   <li>{@link IllegalArgumentException} - в режиме скрипта, если не указано название</li>
     *   <li>{@link NumberFormatException} - при ошибках парсинга чисел</li>
     * </ul>
     */
    @Override
    public void execute() {
        try {
            Ticket cmpticket;

            if (InputReader.isScriptRegime()) {
                if (arg == null || arg.trim().isEmpty()) {
                    throw new IllegalArgumentException("В скрипте не указано название билета");
                }

                System.out.println("  [скрипт] remove_greater с билетом: " + arg);

                String newName = arg.trim();
                Coordinates newCoordinates = InputReader.readCoordinates();
                Long newPrice = InputReader.readPrice("");
                TicketType newType = InputReader.readTicketType();
                Venue newVenue = InputReader.readVenue();

                cmpticket = new Ticket(CreateID.createTicketID(), newName, newCoordinates,
                        LocalDateTime.now(), newPrice, newType, newVenue);

            } else {
                System.out.println("\nВведите данные");
                String newName = InputReader.readNotEmptyStr("Название: ");
                Coordinates newCoordinates = InputReader.readCoordinates();
                Long newPrice = InputReader.readPrice("Цена: ");
                TicketType newType = InputReader.readTicketType();
                Venue newVenue = InputReader.readVenue();

                cmpticket = new Ticket(CreateID.createTicketID(), newName, newCoordinates,
                        LocalDateTime.now(), newPrice, newType, newVenue);
            }

            int removedCount = collection.removeAllGreater(cmpticket);
            if (removedCount == 0) {
                System.out.println("Билетов превышающий данный не существует. Попробуйте еще раз");
            } else {
                System.out.println("Билеты удалены");
            }

            CreateID.removeTicketID(cmpticket.getId());
            if (cmpticket.getVenue() != null) {
                CreateID.removeVenueID(cmpticket.getVenue().getID());
            }

        } catch (NumberFormatException e) {
            System.out.println("Ошибка в билете. Попробуйте снова");
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
        }
    }

    /**
     * Возвращает описание команды.
     *
     * @return строковое описание функциональности команды
     */
    @Override
    public String getDescription() {
        return "Удаление элементов из коллекции превышающие заданный";
    }

    /**
     * Возвращает имя команды.
     *
     * @return строковое имя команды для вызова ("remove_greater")
     */
    @Override
    public String getName() {
        return "remove_greater";
    }
}