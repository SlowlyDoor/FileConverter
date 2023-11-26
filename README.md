# FileConverter
## Описание проекта
`FileConverter` - программа для конвертации данных между XML и JSON форматами
## ПО для сборки и запуска
Для успешной сборки и запуска проекта требуется наличие следующего программного обеспечения:
- `Java`: Рекомендуемая версия 21.0.1. Java является основным языком программирования для проекта.
- `Maven`: Рекомендуема версия 3.9.5. Maven обеспечивает средства для автоматизации сборки проекта.

IntelliJ IDEA (рекомендуемая версия 2023.2.3) предоставляет удобное интегрированное средство разработки и облегчает процессы сборки и запуска, обеспечивая визуальные средства для управления проектом и интеграцию с Java и Maven.
## Инструкция по сборке
1. Открытие Project Structure:
    - Откройте IntelliJ IDEA и проект
2. Открытие Artifacts:
    - В меню выберите `File` -> `Project Structure`
    - В открывшемся окне выберите `Artifacts` в левой части
3. Добавление Artifact:
    - Нажмите на кнопку `+` (Add) и выберите `JAR` -> `From modules with dependencies`
    - Выберите основной класс (Main Class)
    - Также выберите пункт `Copy to the output directory and link via manifest`
    - Под выбранным пунктом нажмите на иконку папки
    - Выберите папку `FileConverter\META-INF`
    - Нажмите `OK`
4. Сборка проекта:
    - Нажмите на кнопку "Build" в верхней панели IntelliJ IDEA
    - Выберите `Build Artifacts` и выберите созданный вами артефакт
    - Выберите `Build` для создания JAR файла
5. Папка с JAR файлом:
    - После успешной сборки JAR файл будет размещен в папке out\artifacts\артефакт_название_jar\
6. Пересборка проекта (если необходимо):
    - Если в проекте внесены изменения, и вы хотите обновить JAR файл, выберите `Rebuild` вместо `Build` на шаге 4
## Структура проекта
- `src`: Основная папка проекта
  - `main`: Папка в которой находится основной код проекта
    - `java`: Классы необходимые для работы с xml и json файлами 
  - `test`: Тесты для программы
    - `resources`: Примеры данных xml и json
## Использование
Запуск происходит при помощи командной строки. Входными данными служат 2 аргумента:
1. Полный путь файла с расширением 
2. Полный путь куда создать конвертированный файл и его название с расширением 
## Работа программы
1. Требуется скачать JDK версии 21 (x64 Compressed Archive), который можно скачать с:

    [https://www.oracle.com/java/technologies/downloads/#jdk21-windows](https://www.oracle.com/java/technologies/downloads/#jdk21-windows)

3. **Клонирование репозитория**
   ```bash
    git clone https://github.com/SlowlyDoor/FileConverter.git
    cd FileConverter
   ```
## Пример использования
- Для конвертации `data.xml` в `dataConvert.json` необходимо:
```bash
java -jar  out\artifacts\FileConverter_jar\FileConverter.jar \your-path\to-file\data.xml \your-path\to-file\dataConvert.json
```
- Для конвертации `data.json` в `dataConvert.xml` необходимо:
```bash
java -jar  out\artifacts\FileConverter_jar\FileConverter.jar \your-path\to-file\data.json \your-path\to-file\dataConvert.xml
```
