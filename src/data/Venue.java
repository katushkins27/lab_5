package data;
import java.lang.Comparable;
/**
 * Класс, представляющий место проведения мероприятия (Venue).
 * <p>
 * Содержит информацию о месте проведения: уникальный идентификатор, название,
 * вместимость и адрес. Реализует интерфейс {@link Comparable} для сравнения
 * объектов.
 * </p>
 *
 * @see Address
 * @see Comparable
 */
public class Venue implements Comparable<Venue>{
    /** Уникальный идентификатор места проведения, генерируется автоматически. Должен быть строго больше 0. */
    private long id;
    /** Название места проведения. Не может быть null или пустым. */
    private String name;
    /** Вместимость места проведения. Должна быть строго больше 0. */
    private int capacity;
    /** Адрес места проведения. Не может быть null. */
    private Address address;

    /**
     * Конструктор с параметрами для создания полностью инициализированного объекта Venue.
     *
     * @param id       уникальный идентификатор (должен быть > 0)
     * @param name     название места (не может быть null или пустым)
     * @param capacity вместимость (должна быть > 0)
     * @param address  адрес (не может быть null)
     * @throws IllegalArgumentException если любой из параметров не проходит валидацию
     */
    public Venue(long id, String name, int capacity, Address address){
        setID(id);
        setName(name);
        setCapacity(capacity);
        setAddress(address);
    }
    /**
     * Конструктор по умолчанию.
     * (Все поля необходимо установить через сеттеры)
     */
    public Venue(){

    }

    /**
     * Возвращает уникальный идентификатор места проведения.
     *
     * @return идентификатор места проведения
     */
    public long getID() {
        return id;
    }
    /**
     * Возвращает название места проведения.
     *
     * @return название места проведения
     */
    public String getName() {
        return name;
    }
    /**
     * Возвращает вместимость места проведения.
     *
     * @return вместимость места проведения
     */
    public int getCapacity() {
        return capacity;
    }
    /**
     * Возвращает адрес места проведения.
     *
     * @return объект {@link Address} с адресом места проведения
     */
    public Address getAddress() {
        return address;
    }

    /**
     * Устанавливает уникальный идентификатор места проведения.
     *
     * @param id идентификатор (должен быть строго больше 0)
     * @throws IllegalArgumentException если id меньше или равно 0
     */
    public void setID(long id){
        if (id <= 0) {
            throw new IllegalArgumentException("id должно быть строго больше 0");
        }
        this.id = id;
    }
    /**
     * Устанавливает название места проведения.
     *
     * @param name название места (не может быть null или пустым)
     * @throws IllegalArgumentException если name равен null или пустой строке
     */
    public void setName(String name) {
        if (name == null || name.isBlank()) {
            throw new IllegalArgumentException("Имя не может быть null или пустым");
        }
        this.name = name;
    }
    /**
     * Устанавливает вместимость места проведения.
     *
     * @param capacity вместимость (должна быть строго больше 0)
     * @throws IllegalArgumentException если capacity меньше или равно 0
     */
    public void setCapacity(int capacity){
        if (capacity<=0){
            throw new IllegalArgumentException("Вместимость не может быть меньше 0");
        }
        this.capacity = capacity;
    }
    /**
     * Устанавливает адрес места проведения.
     *
     * @param address адрес (не может быть null)
     * @throws IllegalArgumentException если address равен null
     */
    public void setAddress(Address address){
        if (address == null){
            throw new IllegalArgumentException("Адрес не может быть null");
        }
        this.address = address;
    }
    /**
     * Сравнивает текущий объект Venue с другим объектом Venue.
     *
     * @param other другой объект Venue для сравнения
     * @return отрицательное число, если текущий объект меньше,
     *         положительное число, если текущий объект больше,
     *         0, если объекты равны
     */
    @Override
    public int compareTo(Venue other) {
        int capacityCompare = Integer.compare(this.capacity, other.capacity);
        if (capacityCompare != 0) return capacityCompare;
        return this.name.compareTo(other.name);
    }
    /**
     * Возвращает строковое представление объекта Venue.
     * <p>
     * Формат строки: "id, name, capacity, address"
     *</p>
     * @return строковое представление объекта
     */
    @Override
    public String toString(){
        return String.format("%d, %s, %d, %s", id, name, capacity, address);
    }
}
