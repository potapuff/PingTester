# PingTester
Educational app, part of Sumy State University Technical Solution Support course.

Приложение может быть использовано в курсе Technical Solution Support для тестирования активных проверок.
Для регистрации своего endpoint отправьте POST запрос на http://127.0.0.1:7000 с содержимым:

    {
        url: 'endpoint-path',
        code: ожидаемый код ответа,
        message: 'Любое слообщение'
    } 


#Tesing

## Unit-test

`Pom.xml`:

        <dependency>
            <groupId>org.junit.jupiter</groupId>
            <artifactId>junit-jupiter-api</artifactId>
            <version>5.7.0</version>
            <scope>test</scope>
        </dependency>

JUnit - фремворк для тестирования.
Примеры тестов `tss.sumdu.test.unit` . Основная идея - тестировать класы максимально независимо друг от друга.
Запустите тесты отдельно и увидите, что coverage каждого из максимально сосредоточен в одном классе.
Для этого все связаные сущности заменяются заглушками. см. `tss.sumdu.test.unit.ServiceHolderTest.java`

`Pom.xml`:

        <dependency>
            <groupId>org.mockito</groupId>
            <artifactId>mockito-junit-jupiter</artifactId>
            <version>3.6.0</version>
            <scope>test</scope>
        </dependency>
        <dependency>
            <groupId>net.bytebuddy</groupId>
            <artifactId>byte-buddy</artifactId>
            <version>1.10.15</version>
        </dependency>

Для работы необхолимо добавить файл `src/test/resources/mockito-extensions/org.mockito.plugins.MockMaker`  с содержимым

    mock-maker-inline

## Functional (Integration) whitebox testing

Идея - мы тестируем только контроллеры, а не веб сервер. Это самы быстрой по времени српособ. Пример 
`tss.sumdu.test.integration.ServiceControllerFunctionalTest`
 
## Functional black-box testing

Идея - сервре приложения реализует много разніх фич, например авторизацию/аутентификацию. Их важно учесть при
интеграционном тестировании. Потому можем создать прилодение и слать ему запросы. Мы не можем залезь внуть приложения и
можем контролировать только ответы и списи во внешние службы (базу данных).

`Pom.xml`:

        <dependency>
            <groupId>com.konghq</groupId>
            <artifactId>unirest-java</artifactId>
            <version>3.4.00</version>
            <scope>test</scope>
        </dependency>

        
Пример - `tss.sumdu.test.integration.ServiceControllerWebServerTest`


## UI testing (as variant of integration testing)

Можно тестировать как система реагирует на действия пользователя (UI-тест). При этом в тестирование задейстованы браузер,
front-end, сеть, веб-сервер, приложение, база данных. Компонентов слишком много, потому трудно протестировать
дествительно все. Но это может использовано как начало для End-to-End тестирования.

`Pom.xml`:

        <dependency>
            <groupId>org.seleniumhq.selenium</groupId>
            <artifactId>selenium-chrome-driver</artifactId>
            <version>3.141.59</version>
            <scope>test</scope>
        </dependency>

Обратите внимание, что с End-to-End UI-тестов можно (и нужно) протестировать кросс-браузерность. (Слава ООП).  

`Pom.xml`:

        <dependency>
            <groupId>io.github.bonigarcia</groupId>
            <artifactId>webdrivermanager</artifactId>
            <version>3.6.2</version>
            <scope>test</scope>
        </dependency>

`webdrivermanage` - библиотека упрощающая установку и обновление web-drivers.

 Пример -  `tss.sumdu.test.integration.ServiceControllerUIFunctionalTest`
