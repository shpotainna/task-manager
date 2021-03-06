#Task manager
***
Task manager - це додаток, який дозволяє планувати задачі одноразово або на певний проміжок часу. 
Метою проекту було практичне застосування мови Java.

Протягом виконнаня мною було освоєно та імплементовано наступні техніки:

* власні реалізації структур даних: ArrayList та LinkedList;
* ітератори;
* робота з файловою системою;
* графічний інтерфейс з використанням Swing;
* збірка та запуск проекту використовуючи javac, jar, java;
* документація, утиліта javadoc.

Було застосовуно наступні патерни проектування: MVC, Observer, Decorator, Factory method.

Завдання та юніт тести були взяті [тут](./samples/assignment.pdf).

##Збірка і запуск проекту
Для збірки проекту необхідно мати OC Windows з установленою JDK 8.

Для запуску потрібно виконати з консолі команду [run.bat](./run.bat) - вона зкомпілює, 
створить jar архів та запустить додаток.

Для запуску тестів використовується команда [run-test.bat](./run-test.bat), яка відповідно компілює проект, 
збирає jar архів, компілює тестові класи, створює окремий jar архів та запускає тести.

Команда [docs.bat](./docs.bat) генерує документацію.

##Функціонал

###В додатку доступні наступні можливості:
* створення одноразових та повторюваних задач;
* видалення задач;
* збереження даних між запусками у файлову систему;
* сповіщення користувача.

###Інтерфейс
Головне вікно додатку 

![main-window](./samples/main-window.PNG)

Вікно створення задачі. Одноразової

![add-non-repeated-task-window](./samples/add-non-repeated-task-window.PNG)

або ж повторюваної.

![add-repeated-task-window](./samples/add-repeated-task-window.PNG)

Для видалення задачі необхідно її виділити та натиснути _Remove_.

![remove-window](./samples/remove-window.PNG)

Вікно закриття додатку.

![confirmation-window](./samples/confirmation-window.PNG).

Вікно сповіщень.

![confirmation-window](./samples/notification-window.PNG).