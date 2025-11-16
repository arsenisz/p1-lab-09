public class Task11_WordsToLowerCase {

    /**
     * Замінює усі слова у реченні тими самими словами, записаними маленькими літерами.
     * Слова складаються лише з літер (a-z, A-Z).
     * Числа (0-9) та комбінації літер і цифр залишаються без змін.
     *
     * @param sentence речення для обробки
     * @return речення з словами у нижньому регістрі
     * @throws NullPointerException якщо sentence == null
     * @throws IllegalArgumentException якщо sentence порожній
     */
    public static String wordsToLowerCase(String sentence) {
        // Перевірка на null
        if (sentence == null) {
            throw new NullPointerException("Аргумент не може бути null");
        }

        // Перевірка на порожній рядок
        if (sentence.isEmpty()) {
            throw new IllegalArgumentException("Речення не може бути порожнім");
        }

        // Розбиваємо речення на окремі символи для обробки
        char[] chars = sentence.toCharArray();
        StringBuilder result = new StringBuilder();
        StringBuilder currentToken = new StringBuilder();

        for (int i = 0; i < chars.length; i++) {
            char c = chars[i];

            // Якщо символ - буква або цифра, додаємо до поточного токену
            if (Character.isLetterOrDigit(c)) {
                currentToken.append(c);
            } else {
                // Зустріли роздільник - обробляємо накопичений токен
                if (currentToken.length() > 0) {
                    result.append(processToken(currentToken.toString()));
                    currentToken.setLength(0); // очищаємо буфер
                }
                result.append(c); // додаємо роздільник
            }
        }

        // Обробляємо останній токен, якщо він є
        if (currentToken.length() > 0) {
            result.append(processToken(currentToken.toString()));
        }

        return result.toString();
    }

    /**
     * Обробляє окремий токен (слово, число або комбінацію)
     * Якщо токен - слово (тільки літери), переводить у нижній регістр
     * Інакше залишає без змін
     */
    private static String processToken(String token) {
        // Перевіряємо, чи токен складається тільки з літер
        boolean onlyLetters = true;
        boolean hasDigits = false;

        for (int i = 0; i < token.length(); i++) {
            char c = token.charAt(i);
            if (Character.isDigit(c)) {
                hasDigits = true;
                onlyLetters = false;
            } else if (!Character.isLetter(c)) {
                onlyLetters = false;
            }
        }

        // Якщо тільки літери - це слово, переводимо у нижній регістр
        if (onlyLetters) {
            return token.toLowerCase();
        }

        // Інакше (число або комбінація) - залишаємо як є
        return token;
    }

    /**
     * Точка входу в програму
     */
    public static void main(String[] args) {
        System.out.println("=== Тестування wordsToLowerCase ===\n");

        // Тест 1: Приклад з завдання
        testConversion(
                "The user with the nickname koala757677 this month wrote 3 times more comments than the user with the nickname croco181dile920 4 months ago",
                true
        );

        System.out.println();

        // Тест 2: Речення з різними типами токенів
        testConversion("Hello World 123 Test456 ABC", true);

        // Тест 3: Речення тільки з великими літерами
        testConversion("ALL WORDS ARE UPPERCASE", true);

        // Тест 4: Речення з числами
        testConversion("User123 wrote 5 comments today", true);

        // Тест 5: Речення з розділовими знаками
        testConversion("Hello, World! How are you?", true);

        // Тест 6: Речення з множинними пробілами
        testConversion("Multiple   spaces   between   words", true);

        // Тест 7: Речення лише з малими літерами
        testConversion("already lowercase text", true);

        // Тест 8: Змішане речення
        testConversion("ABC123def 456GHI jkl789MNO 999", true);

        System.out.println("\n=== Тести з некоректними даними ===\n");

        // Тест 9: null
        testConversion(null, false);

        // Тест 10: Порожній рядок
        testConversion("", false);
    }

    /**
     * Допоміжний метод для тестування
     */
    private static void testConversion(String input, boolean shouldSucceed) {
        try {
            String result = wordsToLowerCase(input);
            if (shouldSucceed) {
                System.out.println("Вхід:   \"" + input + "\"");
                System.out.println("Вихід:  \"" + result + "\"");
                System.out.println("Статус: ✓ Успішно\n");
            } else {
                System.out.println("✗ wordsToLowerCase(\"" + input + "\") = \"" + result +
                        "\" (очікувалася помилка!)");
            }
        } catch (Exception e) {
            if (!shouldSucceed) {
                System.out.println("✓ wordsToLowerCase(" + (input == null ? "null" : "\"" + input + "\"") +
                        ") -> " + e.getClass().getSimpleName() + ": " + e.getMessage());
            } else {
                System.out.println("✗ wordsToLowerCase(\"" + input + "\") -> Неочікувана помилка: " +
                        e.getMessage());
            }
        }
    }
}