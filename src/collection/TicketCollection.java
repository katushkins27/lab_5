package collection;
import data.*;
import util.*;

import java.util.*;
import java.time.LocalDateTime;
/**
 * Класс-менеджер для работы с коллекцией билетов.
 * <p>
 * Обеспечивает хранение и управление коллекцией объектов {@link Ticket} в структуре {@link ArrayDeque}.
 * Предоставляет методы для добавления, удаления, обновления и поиска элементов,
 * а также для управления идентификаторами через {@link CreateID}.
 * </p>
 *
 * <p>Основные возможности:</p>
 * <ul>
 *   <li>Автоматическая сортировка коллекции после изменений</li>
 *   <li>Управление уникальными ID билетов и мест проведения</li>
 *   <li>Различные операции удаления (по ID, по цене, по типу, все большие и т.д.)</li>
 *   <li>Получение информации о коллекции</li>
 * </ul>
 *
 * @see Ticket
 * @see CreateID
 * @see ArrayDeque
 */
public class TicketCollection {
    /** Коллекция билетов в формате {@link ArrayDeque} */
    private ArrayDeque<Ticket> collection;
    /** Дата и время инициализации коллекции */
    private LocalDateTime date;

    /**
     * Конструктор по умолчанию.
     * <p>
     * Создаёт пустую коллекцию и устанавливает текущую дату инициализации.
     * </p>
     */
    public TicketCollection() {
        this.collection = new ArrayDeque<>();
        this.date = LocalDateTime.now();
    }

    /**
     * Конструктор с начальной коллекцией.
     * <p>
     * Принимает существующую коллекцию билетов, устанавливает дату инициализации
     * и выполняет первичную сортировку.
     * </p>
     *
     * @param firstCollection начальная коллекция билетов
     */
    public TicketCollection(ArrayDeque<Ticket> firstCollection) {
        this.collection = firstCollection;
        this.date = LocalDateTime.now();
        sortCollection();
    }
    /**
     * Сортирует коллекцию согласно естественному порядку {@link Ticket}.
     * <p>
     * Вызывается после всех операций, изменяющих коллекцию.
     * </p>
     */
    private void sortCollection() {
        ArrayDeque<Ticket> sorted = new ArrayDeque<>();
        collection.stream().sorted().forEach(sorted::add);
        collection = sorted;
    }
    /**
     * Возвращает первый элемент коллекции.
     *
     * @return первый билет или {@code null}, если коллекция пуста
     */
    public Ticket head() {
        return collection.peekFirst();
    }
    /**
     * Добавляет новый билет в коллекцию.
     *
     * @param ticket билет для добавления
     */
    public void addElement(Ticket ticket) {
        collection.add(ticket);
        sortCollection();
    }
    /**
     * Обновляет существующий билет по ID.
     * <p>
     * Процесс обновления:
     * <ol>
     *   <li>Находит билет с указанным ID</li>
     *   <li>Удаляет его из коллекции</li>
     *   <li>Освобождает ID старого билета и его venue</li>
     *   <li>Добавляет новый билет с тем же ID</li>
     *   <li>Выполняет сортировку</li>
     * </ol>
     *
     * @param id        ID обновляемого билета
     * @param newTicket новый билет с обновлёнными данными
     * @return {@code true} если билет найден и обновлён, иначе {@code false}
     */
    public boolean update(int id, Ticket newTicket){
        Iterator<Ticket> iterator = collection.iterator();
        while (iterator.hasNext()){
            Ticket ticket = iterator.next();
            if (id == ticket.getId()){
                iterator.remove();
                CreateID.removeTicketID(id);
                if (ticket.getVenue()!=null){
                    CreateID.removeVenueID(ticket.getVenue().getID());
                }
                collection.add(newTicket);
                sortCollection();
                return true;
            }
        }
        return false;
    }
    /**
     * Очищает коллекцию.
     * <p>
     * Перед удалением освобождает все ID билетов и их мест проведения
     * через {@link CreateID}.
     * </p>
     */
    public void clearCollection() {
        for (Ticket ticket : collection) {
            CreateID.removeTicketID(ticket.getId());
            if (ticket.getVenue() != null) {
                CreateID.removeVenueID(ticket.getVenue().getID());
            }
        }
        collection.clear();
    }
    /**
     * Возвращает информацию о коллекции.
     *
     * @return строку с информацией о коллекции
     */
    public String getInfo(){
        return String.format("Тип коллекции: %s\nДата инициализации: %s\nРазмер коллекции: %d",
                collection.getClass().getSimpleName(), date, collection.size());
    }
    /**
     * Возвращает строковое представление всех элементов коллекции.
     * <p>
     * Каждый билет выводится с порядковым номером.
     * Если коллекция пуста, возвращает сообщение "Коллекция пустая".
     * </p>
     *
     * @return строку со всеми билетами или сообщение о пустой коллекции
     */
    public String showAll(){
        if (collection.isEmpty()) return "Коллекция пустая";
        StringBuilder str = new StringBuilder();
        int ind = 1;
        for (Ticket ticket: collection){
            str.append(ind++).append(": ").append(ticket).append("\n");
        }
        return str.toString();
    }
    /**
     * Возвращает внутреннюю коллекцию билетов.
     *
     * @return {@link ArrayDeque} с билетами
     */
    public ArrayDeque<Ticket> getCollection() {return collection;}
    /**
     * Возвращает размер коллекции.
     *
     * @return количество билетов в коллекции
     */
    public int size() {return collection.size();}

    /**
     * Удаляет первый элемент коллекции.
     * <p>
     * Если коллекция не пуста, освобождает ID удалённого билета и его venue,
     * затем удаляет элемент.
     * </p>
     */
    public void removeHead(){
        Ticket ticket = head();
        if (ticket != null){
            CreateID.removeTicketID(ticket.getId());
            if (ticket.getVenue()!=null){
                CreateID.removeVenueID(ticket.getVenue().getID());
            }
            collection.pollFirst();
        }
    }
    /**
     * Удаляет билет по ID.
     * <p>
     * Находит билет с указанным ID, удаляет его и освобождает ID.
     * После удаления выполняет сортировку.
     * </p>
     *
     * @param id идентификатор билета для удаления
     * @return {@code true} если билет найден и удалён, иначе {@code false}
     */
    public boolean removeById(int id){
        Iterator<Ticket> iterator = collection.iterator();
        while (iterator.hasNext()){
            Ticket ticket = iterator.next();
            if (id == ticket.getId()){
                iterator.remove();
                CreateID.removeTicketID(id);
                if (ticket.getVenue()!=null){
                    CreateID.removeVenueID(ticket.getVenue().getID());
                }
                sortCollection();
                return true;
            }
        }
        return false;
    }
    /**
     * Удаляет все билеты с указанной ценой.
     * <p>
     * Проходит по коллекции, удаляет все билеты, цена которых равна заданной.
     * Освобождает ID всех удалённых билетов и их мест проведения.
     * После удаления выполняет сортировку.
     * </p>
     *
     * @param price цена для удаления
     * @return количество удалённых билетов
     */
    public int removeAllByPrice(Long price){
        Iterator<Ticket> iterator = collection.iterator();
        int count = 0;
        while (iterator.hasNext()){
            Ticket ticket = iterator.next();
            if (price.equals(ticket.getPrice())){
                count = count +1;
                iterator.remove();
                CreateID.removeTicketID(ticket.getId());
                if (ticket.getVenue()!=null){
                    CreateID.removeVenueID(ticket.getVenue().getID());
                }
            }
        }
        sortCollection();
        return count;
    }
    /**
     * Удаляет один билет с указанным типом.
     * <p>
     * Находит первый билет с заданным типом, удаляет его и освобождает ID.
     * После удаления выполняет сортировку.
     * </p>
     *
     * @param type тип билета для удаления
     * @return {@code true} если билет найден и удалён, иначе {@code false}
     */
    public boolean removeByType(TicketType type){
        Iterator<Ticket> iterator = collection.iterator();
        while (iterator.hasNext()){
            Ticket ticket = iterator.next();
            if (type == ticket.getType()){
                iterator.remove();
                CreateID.removeTicketID(ticket.getId());
                if (ticket.getVenue()!=null){
                    CreateID.removeVenueID(ticket.getVenue().getID());
                }
                sortCollection();
                return true;
            }
        }
        return false;
    }
    /**
     * Удаляет все билеты, которые превышают заданный.
     * <p>
     * Сравнение происходит через {@link Ticket#compareTo(Ticket)}.
     * Удаляются все билеты, для которых {@code ticket.compareTo(newTicket) < 0}.
     * После удаления выполняет сортировку.
     * </p>
     *
     * @param newTicket эталонный билет для сравнения
     * @return количество удалённых билетов
     */
    public int removeAllGreater(Ticket newTicket){
        Iterator<Ticket> iterator = collection.iterator();
        int count = 0;
        while (iterator.hasNext()){
            Ticket ticket = iterator.next();
            if (ticket.compareTo(newTicket)<0){
                count = count +1;
                iterator.remove();
                CreateID.removeTicketID(ticket.getId());
                if (ticket.getVenue()!=null){
                    CreateID.removeVenueID(ticket.getVenue().getID());
                }
            } else{
                break;
            }
        }
        sortCollection();
        return count;
    }
    /**
     * Находит билет с минимальным местом проведения (Venue).
     * <p>
     * Сравнение происходит по ID места проведения. Билеты без venue
     * считаются минимальными (null обрабатывается через {@link Comparator#nullsFirst}).
     * </p>
     *
     * @return билет с минимальным venue или {@code null}, если коллекция пуста
     */
    public Ticket getMinByVenue() {
        return collection.stream().min(Comparator.comparing(Ticket::getVenue,
                Comparator.nullsFirst(Comparator.naturalOrder()))).orElse(null);
    }
}