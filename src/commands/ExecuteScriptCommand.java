package commands;

import util.InputReader;
import java.io.File;
import java.io.IOException;
import java.util.Scanner;

/**
 * Команда выполнения скрипта из файла.
 * <p>
 * Реализует функциональность команды "execute_script". Считывает команды из указанного файла
 * и выполняет их последовательно. Поддерживает рекурсивный вызов скриптов с защитой от зацикливания.
 * </p>
 *
 * <p>Особенности работы:</p>
 * <ul>
 *   <li>Требует аргумент - имя файла со скриптом</li>
 *   <li>Отслеживает рекурсивные вызовы для предотвращения бесконечной рекурсии</li>
 *   <li>Использует {@link InputReader#readNextCommand()} для построчного чтения команд</li>
 *   <li>Выводит информацию о выполняемых командах в формате "[выполняется] команда аргумент"</li>
 *   <li>После выполнения скрипта возвращает {@link InputReader} в интерактивный режим</li>
 * </ul>
 *
 * @see Command
 * @see ManagerOfCommands
 * @see InputReader
 */
public class ExecuteScriptCommand extends Command {

    /** Менеджер команд для выполнения скриптовых команд */
    private ManagerOfCommands manager;

    /**
     * Конструктор команды выполнения скрипта.
     *
     * @param manager менеджер команд, который будет использоваться для выполнения команд из скрипта
     */
    public ExecuteScriptCommand(ManagerOfCommands manager) {
        this.manager = manager;
    }

    /**
     * Проверяет наличие аргумента (имени файла скрипта).
     *
     * @return {@code true} если аргумент не пустой, иначе {@code false}
     */
    @Override
    public boolean valArgs() {
        return !arg.isEmpty();
    }

    /**
     * Возвращает информацию об использовании команды.
     *
     * @return строка с синтаксисом: "execute_script file_name"
     */
    @Override
    public String getUsage() {
        return "execute_script file_name";
    }

    /**
     * Выполняет команду чтения и выполнения скрипта.
     * <p>
     * Процесс выполнения:
     * </p>
     * <ol>
     *   <li>Проверяет, не выполняется ли уже этот скрипт (защита от рекурсии)</li>
     *   <li>Добавляет имя файла в стек выполняемых скриптов</li>
     *   <li>Открывает файл и переключает {@link InputReader} в режим скрипта</li>
     *   <li>Построчно читает команды через {@link InputReader#readNextCommand()}</li>
     *   <li>Для каждой команды выводит отладочную информацию "[выполняется] команда аргумент"</li>
     *   <li>Выполняет команду через {@link ManagerOfCommands#executeCommand}</li>
     *   <li>Подсчитывает количество выполненных команд</li>
     *   <li>При ошибке чтения файла выводит сообщение об ошибке</li>
     *   <li>В блоке finally возвращает {@link InputReader} в интерактивный режим и убирает файл из стека</li>
     * </ol>
     *
     * <p>Защита от рекурсии:</p>
     * <p>Если в стеке уже есть имя текущего файла (рекурсивный вызов), выполнение прерывается
     * с сообщением "В скрипте рекурсия".</p>
     */
    @Override
    public void execute() {
        String scriptFile = arg;

        if (manager.getScriptFile().contains(scriptFile)) {
            System.out.println("В скрипте рекурсия");
            return;
        }
        manager.getScriptFile().add(scriptFile);

        try (Scanner fileScanner = new Scanner(new File(scriptFile))) {
            System.out.println("Выполнение скрипта: " + scriptFile);

            InputReader.setScriptRegime(fileScanner);

            int commandCount = 0;

            String commandLine;
            while ((commandLine = InputReader.readNextCommand()) != null) {
                String[] parts = commandLine.trim().split("\\s+", 2);
                String commandName = parts[0];
                String arg = parts.length > 1 ? parts[1] : "";

                System.out.println("  [выполняется] " + commandName + " " + arg);

                boolean shouldContinue = manager.executeCommand(commandName, arg);
                commandCount++;

                if (!shouldContinue) break;
            }

            System.out.println("Скрипт выполнен. Обработано команд: " + commandCount);

        } catch (IOException e) {
            System.out.println("Ошибка чтения: " + e.getMessage());
        } finally {
            InputReader.setInteractiveRegime();
            manager.getScriptFile().remove(scriptFile);
        }
    }

    /**
     * Возвращает описание команды.
     *
     * @return строковое описание функциональности команды
     */
    @Override
    public String getDescription() {
        return "Считывает и исполняет скрипт из указанного файла";
    }

    /**
     * Возвращает имя команды.
     *
     * @return строковое имя команды для вызова ("execute_script")
     */
    @Override
    public String getName() {
        return "execute_script";
    }
}