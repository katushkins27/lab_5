package commands;

import data.Ticket;

/**
 * Команда удаления всех элементов коллекции с указанной ценой.
 * <p>
 * Реализует функциональность команды "remove_all_by_price". Удаляет из коллекции все билеты,
 * цена которых точно совпадает с заданным значением.
 * </p>
 *
 * <p>Особенности работы:</p>
 * <ul>
 *   <li>Требует аргумент - цену для удаления (целое число)</li>
 *   <li>Удаляет ВСЕ билеты с указанной ценой</li>
 *   <li>Перед удалением проверяет существование хотя бы одного билета с такой ценой</li>
 *   <li>Цена сравнивается точно (используется {@code equals})</li>
 *   <li>Если билетов с указанной ценой нет, выводит сообщение об этом</li>
 * </ul>
 *
 * @see Command
 * @see Ticket
 */
public class RemoveAllByPriceCommand extends Command {

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
     * @return строка с синтаксисом: "remove_all_by_price price"
     */
    @Override
    public String getUsage() {
        return "remove_all_by_price price";
    }

    /**
     * Находит билет с указанной ценой.
     * <p>
     * Вспомогательный метод для проверки существования хотя бы одного билета
     * с заданной ценой перед выполнением массового удаления.
     * </p>
     *
     * @param price цена для поиска
     * @return первый найденный билет с указанной ценой или {@code null}, если таких билетов нет
     */
    private Ticket findTicketByPrice(Long price) {
        for (Ticket ticket : collection.getCollection()) {
            Long ticketPrice = ticket.getPrice();
            if (price == null && ticketPrice == null) {
                return ticket;
            }
            if (price != null && price.equals(ticketPrice)) {
                return ticket;
            }
        }
        return null;
    }

    /**
     * Выполняет команду удаления всех билетов с указанной ценой.
     * <p>
     * Процесс выполнения:
     * </p>
     * <ol>
     *   <li>Парсит аргумент команды в значение типа {@link Long}</li>
     *   <li>Проверяет существование хотя бы одного билета с такой ценой через {@link #findTicketByPrice}</li>
     *   <li>Если билеты не найдены - выводит сообщение и завершает выполнение</li>
     *   <li>Если билеты найдены - вызывает {@code collection.removeAllByPrice(price)} для удаления всех</li>
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
            Long price = Long.parseLong(arg);
            Ticket ticket = findTicketByPrice(price);
            if (ticket == null) {
                System.out.println("Билета с такой ценой не существует " + price);
                return;
            }
            collection.removeAllByPrice(price);
            System.out.println("Билеты удалены");
        } catch (NumberFormatException e) {
            System.out.println("Ошибка в цене. Введите число");
        }
    }

    /**
     * Возвращает описание команды.
     *
     * @return строковое описание функциональности команды
     */
    @Override
    public String getDescription() {
        return "Удаление элементов из коллекции по заданной цене";
    }

    /**
     * Возвращает имя команды.
     *
     * @return строковое имя команды для вызова ("remove_all_by_price")
     */
    @Override
    public String getName() {
        return "remove_all_by_price";
    }
}