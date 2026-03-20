package data;

/**
 * Класс, представляющий местоположение (локацию).
 * <p>
 * Содержит координаты X, Y, Z и название локации.
 * Используется для указания точного местоположения в адресе.
 * </p>
 *
 * <p>Требования к полям:</p>
 * <ul>
 *   <li><b>name</b> - может быть null, если не null - длина не должна превышать 777 символов</li>
 *   <li><b>x</b> - может быть любым значением типа double</li>
 *   <li><b>y</b> - не может быть null</li>
 *   <li><b>z</b> - не может быть null</li>
 * </ul>
 *
 * @see Address
 */
public class Location {
    /** Координата X местоположения. Может быть любым значением типа double. */
    private double x;
    /** Координата Y местоположения. Не может быть null. */
    private Long y;
    /** Координата Z местоположения. Не может быть null. */
    private Float z;
    /** Название локации (город, район и т.д.).
     * Может быть null. Если не null, длина не должна превышать 777 символов. */
    private String name;

    /**
     * Конструктор с параметрами для создания полностью инициализированного объекта Location.
     *
     * @param name название локации (может быть null, если не null - длина ≤ 777)
     * @param x    координата X
     * @param y    координата Y (не может быть null)
     * @param z    координата Z (не может быть null)
     * @throws IllegalArgumentException если y или z равны null, или name превышает 777 символов
     */
    public Location(String name, double x, Long y, Float z) {
        setName(name);
        setX(x);
        setY(y);
        setZ(z);
    }

    /**
     * Конструктор по умолчанию, все поля необходимо установить через сеттеры.
     */
    public Location() {

    }
    /**
     * Возвращает координату X.
     *
     * @return координата X
     */
    public double getX() {
        return x;
    }
    /**
     * Возвращает координату Y.
     *
     * @return координата Y (не может быть null)
     */
    public Long getY() {
        return y;
    }

    /**
     * Возвращает координату Z.
     *
     * @return координата Z (не может быть null)
     */
    public Float getZ() {
        return z;
    }

    /**
     * Возвращает название локации.
     *
     * @return название локации или null, если название не установлено
     */
    public String getName() {
        return name;
    }

    /**
     * Устанавливает координату X.
     *
     * @param x координата X (может быть любым значением)
     */
    public void setX(double x) {
        this.x = x;
    }

    /**
     * Устанавливает координату Y.
     *
     * @param y координата Y (не может быть null)
     * @throws IllegalArgumentException если y равен null
     */
    public void setY(Long y) {
        if (y == null) {
            throw new IllegalArgumentException("Аргумент Y не может быть null");
        }
        this.y = y;
    }

    /**
     * Устанавливает координату Z.
     *
     * @param z координата Z (не может быть null)
     * @throws IllegalArgumentException если z равен null
     */
    public void setZ(Float z) {
        if (z == null) {
            throw new IllegalArgumentException("Аргумент Z не может быть null");
        }
        this.z = z;
    }

    /**
     * Устанавливает название локации.
     *
     * @param name название локации (может быть null,
     *             если не null - длина не должна превышать 777 символов)
     * @throws IllegalArgumentException если длина name превышает 777 символов
     */
    public void setName(String name) {
        if (name != null && name.length() > 777) {
            throw new IllegalArgumentException("Длина имени не может превышать 777");
        }
        this.name = name;
    }

    /**
     * Возвращает строковое представление объекта Location.
     * <p>
     * Формат строки: "name, x, y, z"
     * </p>
     * @return строковое представление объекта
     */
    @Override
    public String toString() {
        return String.format("%s, %f, %d, %f", name, x, y, z);
    }
}