package data;

/**
 * Класс, представляющий адрес.
 * <p>
 * Содержит информацию об адресе: название улицы, почтовый индекс и населенный пункт (локацию).
 * Используется в составе {@link Venue} для указания точного места проведения мероприятия.
 * </p>
 *
 * <p>Требования к полям:</p>
 * <ul>
 *   <li><b>street</b> - не может быть null, длина не должна превышать 61 символ</li>
 *   <li><b>zipCode</b> - может быть null</li>
 *   <li><b>town</b> - может быть null</li>
 * </ul>
 *
 * @see Venue
 * @see Location
 */
public class Address {
    /**
     * Название улицы.
     * Поле не может быть null, длина не должна превышать 61 символ.
     */
    private String street;
    /**
     * Почтовый индекс.
     * Поле может быть null.
     */
    private String zipCode;
    /**
     * Населенный пункт (локация).
     * Поле может быть null.
     */
    private Location town;

    /**
     * Конструктор с параметрами для создания полностью инициализированного объекта Address.
     *
     * @param street  название улицы (не может быть null, длина ≤ 61)
     * @param zipCode почтовый индекс (может быть null)
     * @param town    населенный пункт (может быть null)
     * @throws IllegalArgumentException если street равен null или превышает 61 символ
     */
    public Address(String street, String zipCode, Location town) {
        setStreet(street);
        setZipCode(zipCode);
        setTown(town);
    }

    /**
     * Конструктор по умолчанию. Все поля необходимо установить через сеттеры.
     */
    public Address() {
    }
    /**
     * Возвращает название улицы.
     *
     * @return название улицы (никогда не null)
     */
    public String getStreet() {
        return street;
    }

    /**
     * Возвращает почтовый индекс.
     *
     * @return почтовый индекс или null, если индекс не указан
     */
    public String getZipCode() {
        return zipCode;
    }

    /**
     * Возвращает населенный пункт (локацию).
     *
     * @return объект {@link Location} или null, если локация не указана
     */
    public Location getTown() {
        return town;
    }

    /**
     * Устанавливает название улицы.
     *
     * @param street название улицы (не может быть null, длина ≤ 61)
     * @throws IllegalArgumentException если street равен null или длина превышает 61 символ
     */
    public void setStreet(String street) {
        if (street == null || street.length() > 61) {
            throw new IllegalArgumentException("Улица не может быть null или иметь длину больше 61");
        }
        this.street = street;
    }

    /**
     * Устанавливает почтовый индекс.
     *
     * @param zipCode почтовый индекс (может быть null)
     */
    public void setZipCode(String zipCode) {
        this.zipCode = zipCode;
    }

    /**
     * Устанавливает населенный пункт (локацию).
     *
     * @param town населенный пункт (может быть null)
     */
    public void setTown(Location town) {
        this.town = town;
    }

    /**
     * Возвращает строковое представление объекта Address.
     * <p>
     * Формат строки: "street, zipCode, town"
     * </p>
     * @return строковое представление адреса
     */
    @Override
    public String toString() {
        return String.format("%s, %s, %s", street, zipCode, town);
    }
}