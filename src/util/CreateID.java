package util;
import java.util.HashSet;
import java.util.Set;
/**
 * Класс для генерации уникальных идентификаторов билетов (ticket) и мест проведения (venue).
 * <p>
 * Класс обеспечивает создание уникальных ID. Все сгенерированные ID хранятся в
 * соответствующих множествах для контроля уникальности.
 * </p>
 */
public class CreateID {
    private static Set<Integer> ticketID = new HashSet<>();
    private static Set<Long> venueID = new HashSet<>();
    /**
     * Генерирует уникальный идентификатор для билета.
     * @return уникальный ID билета
     */
    public static int createTicketID(){
        int id = 1;
        while (ticketID.contains(id)) id++;
        ticketID.add(id);
        return id;
    }
    /**
     * Генерирует уникальный идентификатор для места проведения.
     * @return уникальный ID места проведения
     */
    public static long createVenueID(){
        long id = 1;
        while (venueID.contains(id)) id++;
        venueID.add(id);
        return id;
    }
    /**
     * Удаляет ID билета из множества существующих идентификаторов.
     * @param id ID билета, который необходимо удалить из множества
     */
    public static void removeTicketID(int id){
        ticketID.remove(id);
    }
    /**
     * Удаляет ID места из множества существующих идентификаторов.
     * @param id ID места, который необходимо удалить из множества
     */
    public static void removeVenueID(long id){
        venueID.remove(id);
    }
    /**
     * Добавляет ID билета в множество идентификаторов.
     * <p>
     * Используется при загрузке данных из файла.
     * </p>
     *
     * @param id ID билета для добавления (должен быть больше 0)
     */
    public static void addTicketID(int id){
        if (id > 0) ticketID.add(id);
    }
    /**
     * Добавляет ID места в множество идентификаторов.
     * <p>
     * Используется при загрузке данных из файла.
     * </p>
     *
     * @param id ID места для добавления (должен быть больше 0)
     */
    public static void addVenueID(long id){
        if (id > 0) venueID.add(id);
    }
}
