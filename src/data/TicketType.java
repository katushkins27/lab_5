package data;
/**
 * Перечисление возможных типов билетов.
 * <p>
 * Определяет категории билетов, доступные в системе:
 * <ul>
 *   <li>{@link #VIP} - билеты класса VIP</li>
 *   <li>{@link #USUAL} - обычные билеты</li>
 *   <li>{@link #BUDGETARY} - бюджетные билеты</li>
 *   <li>{@link #CHEAP} - дешёвые билеты</li>
 * </ul>
 *
 * @see Ticket
 */
public enum TicketType {
    /**
     * VIP билеты повышенной комфортности
     */
    VIP("VIP"),

    /**
     * Обычные билеты без особых привилегий.
     */
    USUAL("USUAL"),

    /**
     * Бюджетные билеты по сниженной цене
     */
    BUDGETARY("BUDGETARY"),
    /**
     * Дешёвые билеты.
     */
    CHEAP("CHEAP");

    /** Текстовое описание типа билета */
    private final String description;

    /**
     * Конструктор перечисления TicketType.
     *
     * @param description текстовое описание типа билета
     */
    TicketType(String description) {
        this.description = description;
    }

    /**
     * Возвращает строку со всеми доступными типами билетов.
     *
     * @return строка со списком всех типов билетов, разделённых запятыми
     */
    public static String AllDescriptions() {
        StringBuilder str = new StringBuilder();
        for (TicketType type : TicketType.values()) {
            str.append(type.name()).append(", ");
        }
        // Удаляем последние два символа (запятую и пробел)
        return str.substring(0, str.length() - 2);
    }
}