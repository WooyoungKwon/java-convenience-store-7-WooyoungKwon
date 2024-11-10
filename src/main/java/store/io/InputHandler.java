package store.io;

import static store.io.ConstErrorMessage.INVALID_INPUT;
import static store.io.ConstMessage.INPUT_ORDER;

import camp.nextstep.edu.missionutils.Console;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

public class InputHandler {
    public static boolean wantToAdd(String name, int getNumber) {
        OutputHandler.printGuideMessage(ConstMessage.CAN_MORE_FREE_ITEM(name, getNumber));
        String answer = Console.readLine();
        OutputHandler.printLinebreak();
        if (answer.equalsIgnoreCase("y")) {
            return true;
        } else if (answer.equalsIgnoreCase("n")) {
            return false;
        }
        throw new IllegalArgumentException(INVALID_INPUT);
    }

    public static boolean yesOrNo(String message) {
        OutputHandler.printGuideMessage(message);
        String answer = Console.readLine();
        OutputHandler.printLinebreak();
        if (answer.equalsIgnoreCase("y")) {
            return true;
        } else if (answer.equalsIgnoreCase("n")) {
            return false;
        }
        throw new IllegalArgumentException(INVALID_INPUT);
    }

    public static List<String> inputOrder() {
        OutputHandler.printGuideMessage(INPUT_ORDER);
        String orders = Console.readLine();
        OutputHandler.printLinebreak();
        return List.of(orders.split(","));
    }

    public List<String> readMdFile(String fileName) {
        List<String> result;
        try (InputStream inputStream = InputHandler.class.getClassLoader().getResourceAsStream(fileName);
             BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream, StandardCharsets.UTF_8))) {
            result = inputLines(reader);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return result;
    }

    private List<String> inputLines(BufferedReader reader) throws IOException {
        List<String> result = new ArrayList<>();
        reader.readLine();
        String line;
        while ((line = reader.readLine()) != null) {
            result.add(line);
        }
        return result;
    }
}
