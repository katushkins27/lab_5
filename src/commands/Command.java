package commands;

import collection.TicketCollection;
import java.util.Arrays;
import java.util.Objects;

/**
 * Абстрактный базовый класс для всех команд приложения.
 * <p>
 * Определяет общую структуру и поведение для всех команд, работающих с коллекцией билетов.
 * Каждая конкретная команда должна наследовать этот класс и реализовать его абстрактные методы.
 * </p>
 *
 * <p>Основные возможности:</p>
 * <ul>
 *   <li>Установка контекста выполнения (коллекция и аргументы)</li>
 *   <li>Выполнение команды через метод {@link #execute()}</li>
 *   <li>Получение информации о команде (имя, описание, использование)</li>
 *   <li>Валидация аргументов</li>
 * </ul>
 *
 * @see TicketCollection
 */
public abstract class Command {
    /** Коллекция билетов, с которой работает команда */
    protected TicketCollection collection;
    /** Аргумент команды (может быть null для команд без аргументов) */
    protected String arg;

    /**
     * Устанавливает контекст выполнения команды.
     * <p>
     * Вызывается перед выполнением команды для передачи необходимых зависимостей.
     * </p>
     *
     * @param collection коллекция билетов, с которой будет работать команда
     * @param arg        аргумент команды (может быть null)
     */
    public void setContext(TicketCollection collection, String arg) {
        this.collection = collection;
        this.arg = arg != null ? arg : null;
    }

    /**
     * Абстрактный метод, который должны реализовать все конкретные команды.
     * Содержит основную логику работы команды.
     */
    public abstract void execute();

    /**
     * Возвращает описание команды.
     * <p>
     * Абстрактный метод для получения текстового описания функциональности команды.
     * </p>
     *
     * @return строковое описание команды
     */
    public abstract String getDescription();

    /**
     * Возвращает имя команды.
     * <p>
     * Абстрактный метод для получения имени команды, по которому она вызывается.
     * </p>
     *
     * @return строковое имя команды
     */
    public abstract String getName();

    /**
     * Проверяет валидность аргументов команды.
     *
     * @return {@code true} если аргументы валидны, иначе {@code false}
     */
    public boolean valArgs() {
        return true;
    }

    /**
     * Возвращает информацию об использовании команды.
     *
     * @return строка с синтаксисом использования команды
     */
    public String getUsage() {
        return getName();
    }

    /**
     * Сравнивает эту команду с другим объектом.
     * <p>
     * Две команды считаются равными, если они имеют одинаковое имя и аргумент.
     * </p>
     *
     * @param o объект для сравнения
     * @return {@code true} если объекты равны, иначе {@code false}
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Command that = (Command) o;
        return Objects.equals(getName(), that.getName()) &&
                Objects.equals(arg, that.arg);
    }

    /**
     * Возвращает хеш-код команды.
     *
     * @return хеш-код объекта
     */
    @Override
    public int hashCode() {
        return Objects.hash(getName(), arg);
    }

    /**
     * Возвращает строковое представление команды.
     * <p>
     * Формат: "ИмяКласса{name='имя', arg='аргумент'}"
     * </p>
     *
     * @return строковое представление команды
     */
    @Override
    public String toString() {
        return getClass().getSimpleName() + "{name='" + getName() +
                "', arg='" + arg + "'}";
    }
}