package commands;

import data.*;
import util.InputReader;

/**
 * Команда обновления элемента коллекции по идентификатору.
 * <p>
 * Реализует функциональность команды "update". Заменяет существующий билет с указанным ID
 * на новый билет с теми же ID и датой создания, но обновлёнными остальными полями.
 * </p>
 *
 * <p>Особенности работы:</p>
 * <ul>
 *   <li>Требует аргумент - ID билета для обновления (целое положительное число)</li>
 *   <li>Перед обновлением проверяет существование билета с таким ID</li>
 *   <li>Сохраняет оригинальные ID и дату создания, обновляет все остальные поля</li>
 *   <li>ID билета и ID venue (если есть) не меняются и не пересоздаются</li>
 *   <li>Работает как в интерактивном режиме, так и в режиме скрипта</li>
 * </ul>
 *
 * @see Command
 * @see Ticket
 * @see InputReader
 */
public class UpdateCommand extends Command {

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
     * @return строка с синтаксисом: "update id"
     */
    @Override
    public String getUsage() {
        return "update id";
    }

    /**
     * Находит билет по его уникальному идентификатору.
     * <p>
     * Вспомогательный метод для проверки существования билета
     * с заданным ID перед выполнением обновления.
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
     * Выполняет команду обновления билета по ID.
     * <p>
     * Процесс выполнения:
     * </p>
     * <ol>
     *   <li>Парсит аргумент команды в целое число (int)</li>
     *   <li>Проверяет существование билета с таким ID через {@link #findTicketById}</li>
     *   <li>Если билет не найден - выводит сообщение и завершает выполнение</li>
     *   <li>В режиме скрипта ({@link InputReader#isScriptRegime()}) выводит приглашение "Введите новые данные"</li>
     *   <li>Запрашивает у пользователя через {@link InputReader} новые данные:
     *       <ul>
     *         <li>Новое название</li>
     *         <li>Новые координаты</li>
     *         <li>Новую цену (может быть null)</li>
     *         <li>Новый тип билета</li>
     *         <li>Новое место проведения (может быть null)</li>
     *       </ul>
     *   </li>
     *   <li>Создаёт новый объект билета с тем же ID и оригинальной датой создания</li>
     *   <li>Вызывает {@code collection.update(id, updTicket)} для замены билета в коллекции</li>
     *   <li>Выводит подтверждение об успешном обновлении</li>
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
            Ticket oldTicket = findTicketById(id);
            if (oldTicket == null) {
                System.out.println("Билета с таким ID не существует " + id);
                return;
            }
            if (InputReader.isScriptRegime()) {
                System.out.println("\nВведите новые данные");
            }

            String newName = InputReader.readNotEmptyStr("Новое название: ");
            Coordinates newCoordinates = InputReader.readCoordinates();
            Long newPrice = InputReader.readPrice("Новая цена: ");
            TicketType newType = InputReader.readTicketType();
            Venue newVenue = InputReader.readVenue();

            Ticket updTicket = new Ticket(
                    id,
                    newName,
                    newCoordinates,
                    oldTicket.getCreationDate(),
                    newPrice,
                    newType,
                    newVenue
            );

            collection.update(id, updTicket);
            System.out.println("Билет обновлен. ID: " + id);

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
        return "Обновление значения элемента коллекции по ID";
    }

    /**
     * Возвращает имя команды.
     *
     * @return строковое имя команды для вызова ("update")
     */
    @Override
    public String getName() {
        return "update";
    }
}