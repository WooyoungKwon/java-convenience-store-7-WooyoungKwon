package store.io;

import static store.io.ConstErrorMessage.INVALID_INPUT;

import camp.nextstep.edu.missionutils.Console;
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

public class InputHandler {
    public static boolean wantToAdd(String name, int getNumber) {
        System.out.println("현재 " + name + "은(는) " + getNumber
                + "개를 무료로 더 받을 수 있습니다. 추가하시겠습니까? (Y/N)");
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

    public static String inputOrder() {
        System.out.println("구매하실 상품명과 수량을 입력해 주세요. (예: [사이다-2],[감자칩-1])");
        return Console.readLine();
    }

    public List<String> readMdFile(String fileName) {
        List<String> result = new ArrayList<>();
        try (InputStream inputStream = InputHandler.class.getClassLoader().getResourceAsStream(fileName);
             BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream, StandardCharsets.UTF_8))) {

            reader.readLine();
            String line;

            while ((line = reader.readLine()) != null) {
                result.add(line);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }
}
