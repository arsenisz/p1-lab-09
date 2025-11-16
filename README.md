# p1-lab-09
Об'єкти типу String
# Відповіді на контрольні питання
## Лабораторна робота №9 - String

---

## Питання 1: Що таке immutable об'єкт? Для чого об'єкти класу String зробили імутабельними? Чому клас String задекларований як final?

### Що таке immutable об'єкт?

**Immutable (незмінний) об'єкт** — це об'єкт, стан якого не може бути змінений після створення. Усі поля такого об'єкта є `final`, і жоден метод не може змінити їх значення. Будь-яка операція, яка здається зміною об'єкта, насправді створює новий об'єкт.

### Для чого об'єкти класу String зробили імутабельними?

**1. Безпека (Security)**
- Рядки часто використовуються для зберігання чутливих даних: паролів, URL-адрес, шляхів до файлів, параметрів підключення до бази даних
- Імутабельність гарантує, що ці дані не можуть бути змінені після створення, що запобігає несанкціонованій модифікації

**2. Багатопоточність (Thread Safety)**
- Імутабельні об'єкти є thread-safe без додаткової синхронізації
- Декілька потоків можуть безпечно використовувати один і той самий об'єкт String без ризику конфліктів

**3. String Pool (пул рядків)**
- Java використовує пул рядків для оптимізації використання пам'яті
- Однакові літеральні рядки зберігаються в одному екземплярі
- Якби String був мутабельним, зміна одного рядка вплинула б на всі посилання на цей рядок у пулі

**4. Хешування (Hashing)**
- String часто використовується як ключ у `HashMap`, `HashSet` та інших колекціях
- Імутабельність гарантує, що хеш-код не зміниться після створення об'єкта
- Це критично для правильної роботи хеш-таблиць

**5. Кешування хеш-коду**
- Оскільки рядок не змінюється, його хеш-код можна обчислити один раз і кешувати
- Це значно покращує продуктивність при роботі з колекціями

**6. Оптимізація продуктивності**
- JVM може виконувати різні оптимізації для імутабельних об'єктів
- Можливість повторного використання рядків економить пам'ять

### Чому клас String задекларований як final?

Клас String оголошено як `final`, щоб:

**1. Запобігти створенню підкласів**
- Ніхто не може створити підклас String і перевизначити його методи
- Це гарантує, що поведінка String залишається передбачуваною

**2. Забезпечити безпеку**
- Якби можна було створити підклас, він міг би змінити поведінку та порушити імутабельність
- Наприклад, підклас міг би додати методи, що змінюють внутрішній стан

**3. Гарантувати надійність**
- Весь код, що працює зі String, може покладатися на його незмінну природу
- Не потрібно перевіряти, чи це справжній String, чи його підклас

**4. Оптимізація**
- Компілятор і JVM можуть виконувати оптимізації, знаючи точну поведінку класу

---

## Питання 2: Що таке регулярні вирази? Наведіть приклади регулярних виразів.

### Що таке регулярні вирази?

**Регулярні вирази (Regular Expressions, RegEx)** — це послідовності символів, що визначають шаблон пошуку в тексті. Вони є потужним інструментом для:
- Пошуку підрядків у тексті
- Валідації формату даних
- Заміни частин тексту
- Розбиття рядків на частини

### Основні метасимволи

| Символ | Опис | Приклад |
|--------|------|---------|
| `.` | Будь-який символ | `a.c` → "abc", "a5c" |
| `\d` | Цифра (0-9) | `\d+` → "123" |
| `\D` | Не цифра | `\D+` → "abc" |
| `\w` | Слово (a-z, A-Z, 0-9, _) | `\w+` → "Hello_123" |
| `\W` | Не слово | `\W+` → "!@#" |
| `\s` | Пробільний символ | `\s+` → "   " |
| `\S` | Не пробільний символ | `\S+` → "Hello" |
| `^` | Початок рядка | `^Hello` → "Hello..." |
| `$` | Кінець рядка | `World$` → "...World" |
| `*` | 0 або більше повторень | `a*` → "", "a", "aa" |
| `+` | 1 або більше повторень | `a+` → "a", "aa" |
| `?` | 0 або 1 повторення | `a?` → "", "a" |
| `{n}` | Рівно n повторень | `\d{3}` → "123" |
| `{n,}` | n або більше | `\d{3,}` → "123", "1234" |
| `{n,m}` | Від n до m повторень | `\d{2,4}` → "12", "123" |
| `[abc]` | Один з символів | `[abc]` → "a", "b", "c" |
| `[^abc]` | Не один з символів | `[^abc]` → "d", "e" |
| `[a-z]` | Діапазон символів | `[a-z]` → "a"..."z" |
| `(abc)` | Група | `(abc)+` → "abcabc" |
| `|` | АБО | `cat|dog` → "cat", "dog" |

### Практичні приклади

#### Прості шаблони
```java
// Одне або більше малих літер
String pattern1 = "[a-z]+";
"hello".matches(pattern1);  // true

// Слово з великої літери
String pattern2 = "[A-Z][a-z]*";
"Hello".matches(pattern2);  // true

// Одна або більше цифр
String pattern3 = "\\d+";
"12345".matches(pattern3);  // true

// Слово (літери, цифри, підкреслення)
String pattern4 = "\\w+";
"User_123".matches(pattern4);  // true
```

#### Валідація даних

```java
// Email
String emailPattern = "[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}";
"user@example.com".matches(emailPattern);  // true

// Телефон (формат +380XXXXXXXXX)
String phonePattern = "\\+380\\d{9}";
"+380501234567".matches(phonePattern);  // true

// Дата (формат DD.MM.YYYY)
String datePattern = "\\d{2}\\.\\d{2}\\.\\d{4}";
"15.11.2025".matches(datePattern);  // true

// IP-адреса (спрощений варіант)
String ipPattern = "\\d{1,3}\\.\\d{1,3}\\.\\d{1,3}\\.\\d{1,3}";
"192.168.1.1".matches(ipPattern);  // true

// Hex колір
String colorPattern = "#[0-9A-Fa-f]{6}";
"#FF5733".matches(colorPattern);  // true

// Пароль (мінімум 8 символів, літери та цифри)
String passwordPattern = "^(?=.*[A-Za-z])(?=.*\\d)[A-Za-z\\d]{8,}$";
"Pass1234".matches(passwordPattern);  // true
```

#### Складні шаблони

```java
// Тільки літери (без цифр)
String lettersOnly = "^[a-zA-Z]+$";
"Hello".matches(lettersOnly);  // true
"Hello123".matches(lettersOnly);  // false

// Комбінація літер та цифр
String alphanumeric = "[a-zA-Z]+\\d+";
"User123".matches(alphanumeric);  // true

// URL
String urlPattern = "^(https?://)?(www\\.)?[a-zA-Z0-9.-]+\\.[a-z]{2,}(/.*)?$";
"https://www.example.com/path".matches(urlPattern);  // true

// Українське ім'я
String ukrainianName = "^[А-ЩЬЮЯҐЄІЇ][а-щьюяґєії']+$";
"Олександр".matches(ukrainianName);  // true
```

### Використання в Java

```java
// Перевірка відповідності шаблону
String text = "User123";
boolean matches = text.matches("[a-zA-Z]+\\d+");  // true

// Розбиття рядка
String sentence = "Hello, World! How are you?";
String[] words = sentence.split("\\W+");  // ["Hello", "World", "How", "are", "you"]

// Заміна
String text2 = "abc123def456";
String result = text2.replaceAll("\\d", "X");  // "abcXXXdefXXX"

// Видалення всіх цифр
String cleaned = "abc123def".replaceAll("\\d", "");  // "abcdef"

// Використання Pattern та Matcher
import java.util.regex.Pattern;
import java.util.regex.Matcher;

Pattern pattern = Pattern.compile("\\d+");
Matcher matcher = pattern.matcher("User123 wrote 45 comments");

while (matcher.find()) {
    System.out.println("Знайдено: " + matcher.group());
}
// Виведе: "123", "45"
```

---

## Питання 3: В чому полягає різниця між оператором «==» та методом «equals()»?

### Оператор `==`

**Що порівнює:**
- Для примітивних типів: порівнює **значення**
- Для об'єктів: порівнює **посилання** (адреси в пам'яті)

**Ключова особливість:** перевіряє, чи дві змінні вказують на один і той самий об'єкт в пам'яті.

### Метод `equals()`

**Що порівнює:**
- Порівнює **вміст** (значення) об'єктів
- Може бути перевизначений у класі для специфічної логіки порівняння

**Ключова особливість:** для String порівнює послідовність символів, навіть якщо це різні об'єкти.

### Детальні приклади

#### Приклад 1: Літерали (String Pool)
```java
String s1 = "Hello";
String s2 = "Hello";

System.out.println(s1 == s2);        // true
System.out.println(s1.equals(s2));   // true
```
**Пояснення:** Обидва посилання вказують на один об'єкт у String Pool, тому і `==` і `equals()` повертають `true`.

#### Приклад 2: Створення через new
```java
String s3 = new String("Hello");
String s4 = new String("Hello");

System.out.println(s3 == s4);        // false
System.out.println(s3.equals(s4));   // true
```
**Пояснення:** Створено два різні об'єкти в heap, тому `==` повертає `false`, але вміст однаковий, тому `equals()` повертає `true`.

#### Приклад 3: Змішаний випадок
```java
String s5 = "World";
String s6 = new String("World");

System.out.println(s5 == s6);        // false
System.out.println(s5.equals(s6));   // true
```
**Пояснення:** `s5` з String Pool, `s6` створений явно — різні об'єкти, але однаковий вміст.

#### Приклад 4: Конкатенація
```java
String s7 = "Hello";
String s8 = "Hel" + "lo";  // Компілятор оптимізує
String s9 = new String("Hel") + "lo";  // Створення в runtime

System.out.println(s7 == s8);        // true (оптимізація компілятора)
System.out.println(s7 == s9);        // false
System.out.println(s7.equals(s8));   // true
System.out.println(s7.equals(s9));   // true
```

#### Приклад 5: Примітивні типи
```java
int a = 5;
int b = 5;
System.out.println(a == b);          // true (порівняння значень)

Integer c = 127;
Integer d = 127;
System.out.println(c == d);          // true (Integer кешує -128..127)

Integer e = 128;
Integer f = 128;
System.out.println(e == f);          // false (поза кешем)
System.out.println(e.equals(f));     // true
```

#### Приклад 6: null
```java
String s10 = null;
String s11 = null;
String s12 = "Hello";

System.out.println(s10 == s11);      // true (обидва null)
System.out.println(s10 == s12);      // false

// s10.equals(s12);                   // NullPointerException!
System.out.println(s12.equals(s10)); // false (безпечно)
```

### Візуалізація

```
String Pool (частина heap):
┌─────────────────┐
│   "Hello"       │ ← s1, s2 (літерали)
│   "World"       │ ← s5
└─────────────────┘

Heap:
┌─────────────────┐
│ new String      │ ← s3
│ "Hello"         │
├─────────────────┤
│ new String      │ ← s4
│ "Hello"         │
├─────────────────┤
│ new String      │ ← s6
│ "World"         │
└─────────────────┘
```

### Таблиця порівняння

| Критерій | `==` | `equals()` |
|----------|------|------------|
| Примітивні типи | Порівнює значення | Недоступний |
| Об'єкти | Порівнює посилання | Порівнює вміст |
| String Pool | true для однакових літералів | true для однакового тексту |
| Швидкість | Дуже швидко | Повільніше |
| null безпека | Безпечно | NullPointerException якщо викликається на null |

### Коли використовувати що?

**Використовуйте `==`:**
- Для примітивних типів: `int`, `double`, `boolean` тощо
- Для перевірки на `null`: `if (obj == null)`
- Для перевірки identity (чи це той самий об'єкт)

**Використовуйте `equals()`:**
- Для порівняння String (завжди!)
- Для порівняння об'єктів за вмістом
- Коли потрібно порівняти значення, а не посилання

### Правило безпечного порівняння

```java
// Небезпечно (може бути NullPointerException)
if (userInput.equals("admin")) { }

// Безпечно (літерал завжди не null)
if ("admin".equals(userInput)) { }

// Найбезпечніше (Java 7+)
if (Objects.equals(str1, str2)) { }
```

---

## Питання 4: Для чого потрібні класи StringBuilder та StringBuffer?

### Проблема класу String

Оскільки String є **immutable** (незмінним), кожна операція, яка здається зміною рядка, насправді створює новий об'єкт.

#### Приклад проблеми:
```java
String result = "";
for (int i = 0; i < 1000; i++) {
    result += i;  // Створюється 1000 нових об'єктів!
}
```

**Що відбувається:**
1. Створюється новий String для кожної ітерації
2. Старі об'єкти стають "сміттям" для Garbage Collector
3. Витрачається багато пам'яті та процесорного часу
4. Продуктивність катастрофічно падає

### Рішення: StringBuilder та StringBuffer

Ці класи створені для ефективної роботи зі змінюваними рядками.

#### Приклад рішення:
```java
StringBuilder sb = new StringBuilder();
for (int i = 0; i < 1000; i++) {
    sb.append(i);  // Модифікується ОДИН об'єкт
}
String result = sb.toString();
```

### Порівняння StringBuilder та StringBuffer

| Характеристика | StringBuilder | StringBuffer |
|----------------|---------------|--------------|
| **Синхронізація** | Не синхронізований | Синхронізований |
| **Швидкість** | Швидший (~2x) | Повільніший |
| **Thread-safety** | Не thread-safe | Thread-safe |
| **Використання** | Однопоточні додатки | Багатопоточні додатки |
| **Рекомендація** | Використовуйте за замовчуванням | Тільки коли потрібна потокобезпека |
| **З'явився** | Java 5 (2004) | Java 1.0 (1996) |

### Основні методи

```java
StringBuilder sb = new StringBuilder("Hello");

// Додавання в кінець
sb.append(" World");           // "Hello World"
sb.append('!');                // "Hello World!"
sb.append(123);                // "Hello World!123"

// Вставка в позицію
sb.insert(5, ",");             // "Hello, World!123"

// Видалення
sb.delete(5, 7);               // "HelloWorld!123"
sb.deleteCharAt(5);            // "HelloWrld!123"

// Заміна
sb.replace(0, 5, "Hi");        // "HiWrld!123"

// Зміна символу
sb.setCharAt(0, 'h');          // "hiWrld!123"

// Реверс
sb.reverse();                  // "321!dlrWih"

// Отримання довжини
int length = sb.length();      // 10

// Отримання символу
char ch = sb.charAt(0);        // '3'

// Отримання підрядка
String sub = sb.substring(0, 3); // "321"

// Очищення
sb.setLength(0);               // ""
// або
sb.delete(0, sb.length());     // ""

// Конвертація в String
String result = sb.toString();
```

### Продуктивність: порівняння

#### Тест 1: 10,000 конкатенацій
```java
// String (ПОВІЛЬНО!)
long start = System.currentTimeMillis();
String s = "";
for (int i = 0; i < 10000; i++) {
    s += "a";
}
long time1 = System.currentTimeMillis() - start;
// Час: ~2000-3000 мс

// StringBuilder (ШВИДКО!)
start = System.currentTimeMillis();
StringBuilder sb = new StringBuilder();
for (int i = 0; i < 10000; i++) {
    sb.append("a");
}
String result = sb.toString();
long time2 = System.currentTimeMillis() - start;
// Час: ~1-5 мс

// StringBuilder швидший у 500-1000 разів!
```

### Коли використовувати що?

#### Використовуйте String:
```java
// 1. Для незмінних рядків
final String API_KEY = "abc123";

// 2. Для простої конкатенації (компілятор оптимізує)
String greeting = "Hello, " + name + "!";

// 3. Як ключі в Map
Map<String, User> users = new HashMap<>();

// 4. Для літералів
if (status.equals("active")) { }
```

#### Використовуйте StringBuilder:
```java
// 1. У циклах
StringBuilder html = new StringBuilder();
for (Item item : items) {
    html.append("<li>").append(item.getName()).append("</li>");
}

// 2. Для складної конкатенації
StringBuilder query = new StringBuilder()
    .append("SELECT * FROM users ")
    .append("WHERE age > ").append(minAge)
    .append(" AND city = '").append(city).append("'");

// 3. Для динамічного формування тексту
StringBuilder report = new StringBuilder();
report.append("Report for ").append(date).append("\n");
for (Entry entry : entries) {
    report.append(entry).append("\n");
}

// 4. Для модифікації рядків
StringBuilder sb = new StringBuilder(text);
sb.reverse();
sb.insert(5, "***");
```

#### Використовуйте StringBuffer:
```java
// Тільки у багатопоточних додатках
public class Logger {
    private final StringBuffer log = new StringBuffer();
    
    public synchronized void append(String message) {
        log.append(System.currentTimeMillis())
           .append(": ")
           .append(message)
           .append("\n");
    }
    
    public synchronized String getLog() {
        return log.toString();
    }
}
```

### Створення StringBuilder

```java
// Порожній (початкова ємність 16 символів)
StringBuilder sb1 = new StringBuilder();

// З початковою ємністю
StringBuilder sb2 = new StringBuilder(100);

// З початковим значенням
StringBuilder sb3 = new StringBuilder("Hello");

// З іншого CharSequence
StringBuilder sb4 = new StringBuilder(sb3);
```

### Ємність та продуктивність

```java
StringBuilder sb = new StringBuilder();
System.out.println(sb.capacity());  // 16 (за замовчуванням)

sb.append("Hello World!");
System.out.println(sb.capacity());  // 16 (вистачає)

sb.append(" This is a longer text...");
System.out.println(sb.capacity());  // 34 (автоматично розширено)

// Для кращої продуктивності встановіть початкову ємність
StringBuilder optimized = new StringBuilder(1000);
```

**Порада:** Якщо знаєте приблизну довжину результату, встановіть початкову ємність — це збереже час на перерозподілі пам'яті.

### Метод chaining (ланцюжок викликів)

```java
String result = new StringBuilder()
    .append("User: ")
    .append(userName)
    .append(", Age: ")
    .append(age)
    .append(", City: ")
    .append(city)
    .toString();
```

---

## Питання 5: Яким чином простіше всього прибрати пробіли на початку та кінці об'єкту String?

### Основний метод: `trim()`

**Найпростіший спосіб** — використати метод **`trim()`**:

```java
String text = "   Hello World   ";
String cleaned = text.trim();

System.out.println("[" + text + "]");     // "[   Hello World   ]"
System.out.println("[" + cleaned + "]");  // "[Hello World]"
```

**Що робить `trim()`:**
- Видаляє всі пробільні символи на початку та в кінці рядка
- Пробільні символи: символи з кодом <= 32 (пробіл, табуляція, новий рядок тощо)
- **НЕ змінює** оригінальний рядок (String immutable), повертає новий

### Сучасні методи (Java 11+)

Починаючи з Java 11, з'явилися кращі альтернативи:

#### 1. `strip()` — кращий за `trim()`
```java
String text = "   Hello World   ";
String cleaned = text.strip();  // "Hello World"
```

**Різниця між `trim()` та `strip()`:**
- `trim()`: видаляє символи з кодом <= 32
- `strip()`: видаляє всі Unicode пробільні символи
- `strip()` краще працює з міжнародними текстами

```java
String unicode = "\u2000Hello\u2000";  // Em Space (Unicode пробіл)
System.out.println(unicode.trim());     // "\u2000Hello\u2000" (не видалив!)
System.out.println(unicode.strip());    // "Hello" (видалив!)
```

#### 2. `stripLeading()` — тільки початок
```java
String text = "   Hello World   ";
String result = text.stripLeading();
System.out.println("[" + result + "]");  // "[Hello World   ]"
```

#### 3. `stripTrailing()` — тільки кінець
```java
String text = "   Hello World   ";
String result = text.stripTrailing();
System.out.println("[" + result + "]");  // "[   Hello World]"
```

#### 4. `isBlank()` — перевірка на порожність
```java
"".isBlank();        // true
"   ".isBlank();     // true
"  a  ".isBlank();   // false
```

### Порівняльна таблиця

| Метод | Java версія | Що видаляє | Приклад |
|-------|-------------|------------|---------|
| `trim()` | 1.0+ | Символи з кодом <= 32 | `"  Hi  ".trim()` → `"Hi"` |
| `strip()` | 11+ | Всі Unicode пробіли | `"  Hi  ".strip()` → `"Hi"` |
| `stripLeading()` | 11+ | Пробіли на початку | `"  Hi  ".stripLeading()` → `"Hi  "` |
| `stripTrailing()` | 11+ | Пробіли в кінці | `"  Hi  ".stripTrailing()` → `"  Hi"` |

### Практичні приклади використання

#### Очищення введення користувача
```java
Scanner scanner = new Scanner(System.in);
String userInput = scanner.nextLine().trim();

if (userInput.isEmpty()) {
    System.out.println("Ви не ввели нічого!");
}
```

#### Перевірка на порожній рядок після очищення
```java
public static void validateInput(String text) {
    if (text == null) {
        throw new NullPointerException("Текст не може бути null");
    }
    
    if (text.trim().isEmpty()) {
        throw new IllegalArgumentException("Текст не може бути порожнім");
    }
}
```

#### Очищення масиву рядків
```java
String[] inputs = {"  apple  ", " banana ", "  cherry  "};
String[] cleaned = Arrays.stream(inputs)
                         .map(String::trim)
                         .toArray(String[]::new);
// ["apple", "banana", "cherry"]
```

#### Перевірка на blank (Java 11+)
```java
public static boolean isValidInput(String text) {
    return text != null && !text.isBlank();
}

isValidInput("");        // false
isValidInput("   ");     // false
isValidInput("  a  ");   // true
```

### Видалення ВСІХ пробілів (не тільки на початку/кінці)

```java
// Видалити всі пробільні символи
String text = "  Hello   World  ";
String noSpaces = text.replaceAll("\\s+", "");
System.out.println(noSpaces);  // "HelloWorld"

// Замінити множинні пробіли на один
String normalized = text.replaceAll("\\s+", " ").trim();
System.out.println(normalized);  // "Hello World"
```

### Комбінування з іншими методами

```java
// Очистити та перевірити
String input = "  Hello World  ";
String result = input.trim().toLowerCase();
System.out.println(result);  // "hello world"

// Очистити та перевірити довжину
if (input.trim().length() < 5) {
    System.out.println("Занадто короткий рядок!");
}

// Очистити та розбити
String data = "  apple, banana, cherry  ";
String[] items = data.trim().split("\\s*,\\s*");
// ["apple", "banana", "cherry"]
```

### Обробка null значень

```java
// Безпечна обробка з null
public static String safeTrim(String text) {
    return text == null ? null : text.trim();
}

// Або з дефолтним значенням
public static String trimOrDefault(String text, String defaultValue) {
    return text == null ? defaultValue : text.trim();
}

// Java 8+ з Optional
public static Optional<String> trimIfPresent(String text) {
    return Optional.ofNullable(text)
                   .map(String::trim)
                   .filter(s -> !s.isEmpty());
}
```

### Поширені помилки

#### ❌ Неправильно:
```java
// Забули присвоїти результат (String immutable!)
String text = "  Hello  ";
text.trim();  // Нічого не станеться!
System.out.println(text);  // "  Hello  " (не змінилось)
```

#### ✅ Правильно:
```java
String text = "  Hello  ";
text = text.trim();  // Присвоюємо результат
System.out.println(text);  // "Hello"
```

### Рекомендації

**Для Java 8-10:**
- Використовуйте `trim()` — це стандарт і працює завжди

**Для Java 11+:**
- Використовуйте `strip()` — краще працює з Unicode
- Використовуйте `isBlank()` для перевірки порожніх рядків
- Використовуйте `stripLeading()` / `stripTrailing()` для специфічних випадків

**Загальні поради:**
- Завжди обробляйте можливість `null` значень
- Пам'ятайте про immutability String — присвоюйте результат
- Для видалення всіх пробілів використовуйте `replaceAll("\\s+", "")`
- Для нормалізації пробілів використовуйте `replaceAll("\\s+", " ").trim()`

---

## Висновок

Ці п'ять питань охоплюють ключові аспекти роботи з рядками в Java:

1. **Immutability** — фундаментальна концепція, що забезпечує безпеку та надійність
2. **Регулярні вирази** — потужний інструмент для роботи з текстом
3. **== vs equals()** — критична різниця для коректного порівняння
4. **StringBuilder/StringBuffer** — ефективні інструменти для модифікації рядків
5. **Методи очищення** — практичні способи обробки введення

Розуміння цих концепцій є обов'язковим для професійної розробки на Java.
