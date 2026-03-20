package data;

import java.time.LocalDateTime;
import data.*;
import java.lang.Comparable;
/**
 * Класс, представляющий билет на мероприятие.
 * <p>
 * Содержит полную информацию о билете: уникальный идентификатор, название,
 * координаты места, дату создания, цену, тип билета и информацию о месте проведения.
 * Реализует интерфейс {@link Comparable} для сравнения билетов по названию,
 * цене и типу.
 * </p>
 *
 * <p>Требования к полям:</p>
 * <ul>
 *   <li><b>id</b> - должен быть больше 0, уникален, генерируется автоматически</li>
 *   <li><b>name</b> - не может быть null или пустой строкой</li>
 *   <li><b>coordinates</b> - не может быть null</li>
 *   <li><b>creationDate</b> - не может быть null, генерируется автоматически</li>
 *   <li><b>price</b> - может быть null, если указан - должен быть больше 0</li>
 *   <li><b>type</b> - не может быть null</li>
 *   <li><b>venue</b> - может быть null</li>
 * </ul>
 *
 * @see Coordinates
 * @see TicketType
 * @see Venue
 * @see Comparable
 */
public class Ticket implements Comparable<Ticket> {
    /**
     * Уникальный идентификатор билета.
     * Значение поля должно быть больше 0, уникальным и генерироваться автоматически.
     */
    private int id;
    /**
     * Название билета.
     * Поле не может быть null, строка не может быть пустой.
     */
    private String name;
    /**
     * Координаты места.
     * Поле не может быть null.
     */
    private Coordinates coordinates;
    /**
     * Дата и время создания билета.
     * Поле не может быть null, значение генерируется автоматически.
     */
    private java.time.LocalDateTime creationDate;
    /**
     * Цена билета.
     * Поле может быть null. Если значение указано, оно должно быть больше 0.
     */
    private Long price;
    /**
     * Тип билета.
     * Поле не может быть null.
     */
    private TicketType type;
    /**
     * Место проведения мероприятия.
     * Поле может быть null.
     */
    private Venue venue;

    /**
     * Конструктор с параметрами для создания полностью инициализированного объекта Ticket.
     *
     * @param id           уникальный идентификатор (должен быть > 0)
     * @param name         название билета (не может быть null или пустым)
     * @param coordinates  координаты (не может быть null)
     * @param creationDate дата создания (не может быть null)
     * @param price        цена (может быть null, если не null - должна быть > 0)
     * @param type         тип билета (не может быть null)
     * @param venue        место проведения (может быть null)
     * @throws IllegalArgumentException если любой из обязательных параметров не проходит валидацию
     */
    public Ticket(int id, String name, Coordinates coordinates, LocalDateTime creationDate,
                  Long price, TicketType type, Venue venue) {
        setID(id);
        setName(name);
        setCoordinates(coordinates);
        setCreationDate(creationDate);
        setPrice(price);
        setType(type);
        setVenue(venue);
    }

    /**
     * Конструктор по умолчанию,поля необходимо установить через сеттеры.
     */
    public Ticket() {

    }
    /**
     * Устанавливает уникальный идентификатор билета.
     *
     * @param id идентификатор (должен быть строго больше 0)
     * @throws IllegalArgumentException если id меньше или равен 0
     */
    public void setID(int id) {
        if (id <= 0) {
            throw new IllegalArgumentException("ID должен быть строго больше 0");
        }
        this.id = id;
    }
    /**
     * Устанавливает название билета.
     *
     * @param name название билета (не может быть null)
     * @throws IllegalArgumentException если name равен null
     */
    public void setName(String name) {
        if (name == null) {
            throw new IllegalArgumentException("Имя не может быть null");
        }
        this.name = name;
    }
    /**
     * Устанавливает координаты места.
     *
     * @param coordinates координаты (не может быть null)
     * @throws IllegalArgumentException если coordinates равен null
     */
    public void setCoordinates(Coordinates coordinates) {
        if (coordinates == null) {
            throw new IllegalArgumentException("Координаты мест не могут быть null");
        }
        this.coordinates = coordinates;
    }
    /**
     * Устанавливает дату создания билета.
     *
     * @param creationDate дата создания (не может быть null)
     * @throws IllegalArgumentException если creationDate равен null
     */
    public void setCreationDate(LocalDateTime creationDate) {
        if (creationDate == null) {
            throw new IllegalArgumentException("Дата создания не может быть null");
        }
        this.creationDate = creationDate;
    }
    /**
     * Устанавливает цену билета.
     *
     * @param price цена (может быть null, если не null - должна быть больше 0)
     * @throws IllegalArgumentException если price не null и меньше или равно 0
     */
    public void setPrice(Long price) {
        if (price != null && price <= 0) {
            throw new IllegalArgumentException("Стоимость должна быть выше 0");
        }
        this.price = price;
    }

    /**
     * Устанавливает тип билета.
     *
     * @param type тип билета (не может быть null)
     * @throws IllegalArgumentException если type равен null
     */
    public void setType(TicketType type) {
        if (type == null) {
            throw new IllegalArgumentException("Тип не может быть null");
        }
        this.type = type;
    }

    /**
     * Устанавливает место проведения мероприятия.
     *
     * @param venue место проведения (может быть null)
     */
    public void setVenue(Venue venue) {
        this.venue = venue;
    }

    /**
     * Возвращает уникальный идентификатор билета.
     *
     * @return идентификатор билета
     */
    public int getId() {
        return id;
    }

    /**
     * Возвращает название билета.
     *
     * @return название билета
     */
    public String getName() {
        return name;
    }

    /**
     * Возвращает координаты места.
     *
     * @return объект {@link Coordinates} с координатами
     */
    public Coordinates getCoordinates() {
        return coordinates;
    }

    /**
     * Возвращает дату создания билета.
     *
     * @return дата и время создания
     */
    public LocalDateTime getCreationDate() {
        return creationDate;
    }

    /**
     * Возвращает цену билета.
     *
     * @return цена билета или null, если цена не установлена
     */
    public Long getPrice() {
        return price;
    }

    /**
     * Возвращает тип билета.
     *
     * @return тип билета {@link TicketType}
     */
    public TicketType getType() {
        return type;
    }

    /**
     * Возвращает место проведения мероприятия.
     *
     * @return объект {@link Venue} или null, если место не указано
     */
    public Venue getVenue() {
        return venue;
    }

    /**
     * Сравнивает текущий объект Ticket с другим объектом Ticket.
     * <p>
     * Сравнение происходит по следующему правилу:
     * <ol>
     *   <li>Сначала сравниваются названия (name) в лексикографическом порядке</li>
     *   <li>Если названия равны, сравнивается цена (price):
     *       <ul>
     *         <li>Билеты с null ценой считаются меньше билетов с указанной ценой</li>
     *         <li>Если оба null - считаются равными по цене</li>
     *       </ul>
     *   </li>
     *   <li>Если цены равны, сравниваются типы билетов (type)</li>
     * </ol>
     *
     * @param other другой объект Ticket для сравнения
     * @return отрицательное число, если текущий объект меньше,
     *         положительное число, если текущий объект больше,
     *         0, если объекты равны
     */
    @Override
    public int compareTo(Ticket other) {
        int nameCompare = this.name.compareTo(other.name);
        if (nameCompare != 0) return nameCompare;

        if (this.price == null && other.price == null) return 0;
        if (this.price == null) return -1;
        if (other.price == null) return 1;

        int priceCompare = Long.compare(this.price, other.price);
        if (priceCompare != 0) return priceCompare;
        return this.type.compareTo(other.type);
    }

    /**
     * Возвращает строковое представление объекта Ticket.
     * <p>
     * Формат строки включает все поля класса в формате:
     * Ticket{id=..., name='...', coordinates=..., creationDate=..., price=..., type=..., venue=...}
     * </p>
     *
     * @return строковое представление объекта
     */
    @Override
    public String toString() {
        return String.format("Ticket{id=%d, name='%s', coordinates=%s, creationDate=%s, " +
                        "price=%s, type=%s, venue=%s}", id, name, coordinates, creationDate,
                price, type, venue);
    }
}