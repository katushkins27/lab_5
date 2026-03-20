package data;

/**
 * Класс, представляющий координаты местоположения.
 * <p>
 * Содержит координаты X (целое число) и Y (целое число, не может быть null).
 * </p>
 *
 * <p>Требования к полям:</p>
 * <ul>
 *   <li><b>x</b> - целое число (int), может быть любым значением</li>
 *   <li><b>y</b> - целое число (Long), не может быть null</li>
 * </ul>
 *
 * @see Ticket
 * @see Venue
 */
public class Coordinates {
    /** Координата X. Целое число, может быть любым значением. */
    private int x;
    /**
     * Координата Y. Целое число, не может быть null.
     */
    private Long y;
    /**
     * Конструктор с параметрами для создания полностью инициализированного объекта Coordinates.
     *
     * @param x координата X (может быть любым целым числом)
     * @param y координата Y (не может быть null)
     * @throws IllegalArgumentException если y равен null
     */
    public Coordinates(int x, Long y) {
        setX(x);
        setY(y);
    }

    /**
     * Конструктор по умолчанию, все поля необходимо установить через сеттеры.
     */
    public Coordinates() {

    }

    /**
     * Устанавливает координату X.
     *
     * @param x координата X (может быть любым целым числом)
     */
    public void setX(int x) {
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
     * Возвращает координату X.
     *
     * @return координата X
     */
    public int getX() {
        return x;
    }

    /**
     * Возвращает координату Y.
     *
     * @return координата Y (никогда не null)
     */
    public Long getY() {
        return y;
    }

    /**
     * Возвращает строковое представление объекта Coordinates.
     * <p>
     * Формат строки: "(x, y)"
     * </p>
     *
     * @return строковое представление координат в формате (x, y)
     */
    @Override
    public String toString() {
        return String.format("(%d, %d)", x, y);
    }
}