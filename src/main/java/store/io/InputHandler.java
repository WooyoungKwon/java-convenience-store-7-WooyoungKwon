package store.io;

import camp.nextstep.edu.missionutils.Console;

public class InputHandler {
    public boolean yesOrNo(String input) {
        if (input.equalsIgnoreCase("y")) {
            return true;
        } else if (input.equalsIgnoreCase("n")) {
            return false;
        }
        throw new IllegalArgumentException("[ERROR] 잘못된 입력입니다. 다시 입력해 주세요.");
    }
}
