public class Task4_HexStringToInt {

    /**
     * Перетворює String, що представляє собою число у шістнадцятковій
     * системі числення, у значення int
     *
     * @param s рядок у шістнадцятковій системі числення
     * @return значення int
     * @throws NullPointerException якщо s == null
     * @throws IllegalArgumentException якщо s порожній або містить некоректні символи
     */
    public static int hexStringToInt(String s) {
        // Перевірка на null
        if (s == null) {
            throw new NullPointerException("Аргумент не може бути null");
        }

        // Перевірка на порожній рядок
        if (s.isEmpty()) {
            throw new IllegalArgumentException("Рядок не може бути порожнім");
        }

        // Видаляємо пробіли та переводимо у верхній регістр
        s = s.trim().toUpperCase();

        // Перевірка на порожній рядок після trim
        if (s.isEmpty()) {
            throw new IllegalArgumentException("Рядок не може складатися тільки з пробілів");
        }

        // Перевірка коректності символів
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            if (!((c >= '0' && c <= '9') || (c >= 'A' && c <= 'F'))) {
                throw new IllegalArgumentException(
                        "Некоректний символ '" + c + "' у шістнадцятковому числі"
                );
            }
        }

        // Перетворення шістнадцяткового рядка в int
        int result = 0;
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            int digit;

            if (c >= '0' && c <= '9') {
                digit = c - '0';
            } else {
                digit = c - 'A' + 10;
            }

            // Перевірка на переповнення
            if (result > (Integer.MAX_VALUE - digit) / 16) {
                throw new IllegalArgumentException("Переповнення: число занадто велике для int");
            }

            result = result * 16 + digit;
        }

        return result;
    }

    /**
     * Точка входу в програму
     */
    public static void main(String[] args) {
        System.out.println("=== Тестування hexStringToInt ===\n");

        // Тест 1: Приклад з завдання
        testConversion("CAFE", true);

        // Тест 2: Мале число
        testConversion("A", true);

        // Тест 3: Нуль
        testConversion("0", true);

        // Тест 4: Велике число
        testConversion("7FFFFFFF", true);

        // Тест 5: Малі літери (мають працювати після toUpperCase)
        testConversion("cafe", true);

        // Тест 6: Змішаний регістр
        testConversion("CaFe", true);

        // Тест 7: Число з пробілами (мають видалятися)
        testConversion("  FF  ", true);

        System.out.println("\n=== Тести з некоректними даними ===\n");

        // Тест 8: null
        testConversion(null, false);

        // Тест 9: Порожній рядок
        testConversion("", false);

        // Тест 10: Тільки пробіли
        testConversion("   ", false);

        // Тест 11: Некоректні символи
        testConversion("CAFEG", false);

        // Тест 12: Некоректні символи
        testConversion("12.34", false);

        // Тест 13: Переповнення
        testConversion("FFFFFFFFF", false);
    }

    /**
     * Допоміжний метод для тестування
     */
    private static void testConversion(String input, boolean shouldSucceed) {
        try {
            int result = hexStringToInt(input);
            if (shouldSucceed) {
                System.out.println("✓ hexStringToInt(\"" + input + "\") = " + result +
                        " (0x" + Integer.toHexString(result).toUpperCase() + ")");
            } else {
                System.out.println("✗ hexStringToInt(\"" + input + "\") = " + result +
                        " (очікувалася помилка!)");
            }
        } catch (Exception e) {
            if (!shouldSucceed) {
                System.out.println("✓ hexStringToInt(\"" + input + "\") -> " +
                        e.getClass().getSimpleName() + ": " + e.getMessage());
            } else {
                System.out.println("✗ hexStringToInt(\"" + input + "\") -> Неочікувана помилка: " +
                        e.getMessage());
            }
        }
    }
}