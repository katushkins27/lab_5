package commands;

import util.Parser;
import java.io.IOException;

/**
 * Команда сохранения коллекции в файл.
 * <p>
 * Реализует функциональность команды "save". Сохраняет текущее состояние коллекции билетов
 * в CSV файл с использованием {@link Parser}. При сохранении все данные записываются в формате,
 * который впоследствии может быть прочитан парсером при загрузке.
 * </p>
 *
 * <p>Особенности работы:</p>
 * <ul>
 *   <li>Не требует аргументов (имя файла передаётся в конструктор)</li>
 *   <li>Сохраняет коллекцию в CSV формате с заголовком</li>
 *   <li>При ошибках записи выводит сообщение об ошибке</li>
 *   <li>Перезаписывает файл, если он уже существует</li>
 * </ul>
 *
 * @see Command
 * @see Parser
 * @see Parser#saveFileToCSV(java.util.ArrayDeque, String)
 */
public class SaveCommand extends Command {

    /** Имя файла для сохранения коллекции */
    private final String filename;

    /**
     * Конструктор команды сохранения.
     *
     * @param filename имя файла, в который будет сохранена коллекция
     */
    public SaveCommand(String filename) {
        this.filename = filename;
    }

    /**
     * Выполняет команду сохранения коллекции в файл.
     * <p>
     * Процесс выполнения:
     * </p>
     * <ol>
     *   <li>Вызывает {@link Parser#saveFileToCSV} с текущей коллекцией и именем файла</li>
     *   <li>Парсер записывает все билеты в CSV формате</li>
     *   <li>При успешном сохранении выводит сообщение "Коллекция сохранена в файл: [имя_файла]"</li>
     *   <li>При возникновении ошибки ввода-вывода перехватывает {@link IOException}</li>
     *   <li>Выводит сообщение об ошибке: "Ошибка при сохранении файла [детали ошибки]"</li>
     * </ol>
     */
    @Override
    public void execute() {
        try {
            Parser.saveFileToCSV(collection.getCollection(), filename);
            System.out.println("Коллекция сохранена в файл: " + filename);
        } catch (IOException e) {
            System.out.println("Ошибка при сохранении файла " + e.getMessage());
        }
    }

    /**
     * Возвращает описание команды.
     *
     * @return строковое описание функциональности команды
     */
    @Override
    public String getDescription() {
        return "Сохранить коллекцию в файл";
    }

    /**
     * Возвращает имя команды.
     *
     * @return строковое имя команды для вызова ("save")
     */
    @Override
    public String getName() {
        return "save";
    }
}