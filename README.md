# PingTester

Educational app, part of Sumy State University Technical Solution Support course / Quality assurance + Test Automation
course

Запустіть додаток UrlTesterApp

Для реєстрації свого endpoint надішліть POST запит на http://127.0.0.1:7000 із змістом:

    {
        url: 'endpoint-path',
        code: ожидаемый код ответа,
        message: 'Любое слообщение'
    } 

# Tesing

## Unit-test

Згідно до завдання фреймворк для тестування - NGTest. Корисне
керівництво  [IntelliJ IDEA Beginner Tutorial | How to use TestNG](https://youtu.be/PmpA8PtnXAk)

`Pom.xml`:

        <!-- https://mvnrepository.com/artifact/org.testng/testng -->
        <dependency>
            <groupId>org.testng</groupId>
            <artifactId>testng</artifactId>
            <version>[7.6.1,)</version>
            <scope>test</scope>
        </dependency>

Приклади тестів `tss.sumdu.test.unit` . Основна ідея – тестувати класи максимально незалежно один від одного.
Запустіть тести окремо та побачите, що coverage кожного з максимально зосереджений в одному класі.
Для цього всі пов'язані сутності замінюються заглушками. див. `tss.sumdu.test.unit.ServiceHolderTest.java`

`Pom.xml`:

        <dependency>
            <groupId>org.mockito</groupId>
            <artifactId>mockito-testng</artifactId>
            <version>[0.4.26,)</version>
            <scope>test</scope>
        </dependency>
        <!-- https://mvnrepository.com/artifact/net.bytebuddy/byte-buddy-dep -->
        <dependency>
            <groupId>net.bytebuddy</groupId>
            <artifactId>byte-buddy-dep</artifactId>
            <version>[1.12.18,)</version>
        </dependency>


Для роботи необхідно додати файл `src/test/resources/mockito-extensions/org.mockito.plugins.MockMaker`  із змістом

    mock-maker-inline

Для полегшання напичання тестів, що виконуються багато разів варто розідлити данні, які використовуються для тесту та та
логіку тесту.
Для цього використовуються прараметризовані тести та Data-Provider. Зверныть увагу на код
ParamTestWithDataProvider1.java
в [tutorialspoint](https://www.tutorialspoint.com/testng/testng_parameterized_test.htm) та
клас `tss.sumdu.test.unit.HelpersTest`.

## Functional (Integration) white-box testing

Ідея – ми тестуємо лише контролери, а не веб-сервер. Це найшвидший за часом српособ. Приклад
`tss.sumdu.test.integration.ServiceControllerFunctionalTest`

Найгрша частина при такому підході - генерувати Mock для вхідних параметрів. Для String Framework є відповідні
генератори, але в нас не String.

## Functional black-box testing

Ідея - сервері програми реалізує багато різних фіч, наприклад авторизацію/автентифікацію. Їх важливо врахувати при
інтеграційне тестування. Тому можемо створити приладдя і надсилати йому запити. Ми не можемо залізти додатки і
можемо контролювати лише відповіді та списки у зовнішні служби (базу даних). Зверніть увагу, що при цьому та всіх інших
підходах, що описані нижче - треба запускати сервер. Ткраще робити це програмно у Before-методах, наприклад

    @BeforeTest
    static void initServer() {
        app = new UrlTesterApp();
        app.start(UrlTesterApp.PORT);
    }

Для роботи підходу треба будь-яка бібліотека, що віміє працювати із HTTP. Наприклад, `unirest` - зручна бібліотека для
формування, запуску та отримання результатів запитів.

`Pom.xml`:

        <!-- https://mvnrepository.com/artifact/com.mashape.unirest/unirest-java -->
        <dependency>
            <groupId>com.konghq</groupId>
            <artifactId>unirest-java</artifactId>
            <version>[3.13.11,)</version>
        </dependency>

Приклад коду- `tss.sumdu.test.integration.ServiceControllerWebServerTest`

## UI testing (as variant of integration testing)

Можна тестувати, як система реагує на дії користувача (UI-тест). При цьому в тестування задіяні браузер,
front-end, мережа, веб-сервер, програма, база даних. Компонентів дуже багато, тому важко протестувати
справді все. Такі підходи доціно використовувати для End-to-End тестування. Здебільшого, для управління браузером
використовують Selenium Web Driver. Для полегшання роботи із інсталяцією всіх компонент, приклад використовує
непогану обгортку над Selenium - Selenide

`Pom.xml`:

        <!-- UI testing -->
        <dependency>
            <groupId>com.codeborne</groupId>
            <artifactId>selenide</artifactId>
            <version>[5.25.0,)</version>
            <scope>test</scope>
        </dependency>

Приклад коду-  `tss.sumdu.test.ui.ServiceControllerUIFunctionalTest`

