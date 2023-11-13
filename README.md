# FileConverter
## Описание проекта
`FileConverter` - программа для конвертации данных между XML и JSON форматами
## Использование
Запуск происходит при помощи командной строки. Входными данными служат 2 аргумента:
1. Файл, находящийся в каталоге resources 
2. Название файла, который будет сохранён в каталоге resources
## ПО для сборки и запуска
Рекомендуемая версия Intellij Idea - 2023.2.3
## Структура проекта
- `src`: Основная папка проекта
  - `main`: Папка в которой находится основной код проекта
    - `java`: Классы необходимые для работы с xml и json файлами 
    - `resources`: Примеры данных xml и json
  - `test`: Тесты для программы
## Пример использования
- Для конвертации `data.xml` в `dataConvert.json` необходимо:
```
java -jar FileConverter.jar data.xml dataConvert.json
```
- Для конвертации `data.json` в `dataConvert.xml` необходимо:
```
java -jar FileConverter.jar data.json dataConvert.xml
```
