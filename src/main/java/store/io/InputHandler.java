package store.io;

import static store.io.ConstErrorMessage.INVALID_INPUT;

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
        if (answer.equalsIgnoreCase("y")) {
            return true;
        } else if (answer.equalsIgnoreCase("n")) {
            return false;
        }
        throw new IllegalArgumentException(INVALID_INPUT);
    }

    public static boolean yesOrNo(String message) {
        System.out.println(message);
        String answer = Console.readLine();

        if (answer.equalsIgnoreCase("y")) {
            return true;
        } else if (answer.equalsIgnoreCase("n")) {
            return false;
        }
        throw new IllegalArgumentException(INVALID_INPUT);
    }

    public static List<String> inputOrder() {
        System.out.println("구매하실 상품명과 수량을 입력해 주세요. (예: [사이다-2],[감자칩-1])");
        String orders = Console.readLine();
        System.out.println();
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
