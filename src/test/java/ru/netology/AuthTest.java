package ru.netology;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.Condition.visible;
import static ru.netology.DataGenerator.Registration.*;
import static com.codeborne.selenide.Selectors.withText;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;

public class AuthTest {
    @BeforeEach
    void setUp() {
        open("http://localhost:9999/");

    }

    @Test
    void shouldSendValidForm() {
        var validUser=generateUser("active");
        $("[data-test-id=login] input").setValue(validUser.getLogin());
        $("[data-test-id=password] input").setValue(validUser.getPassword());
        $("button[data-test-id=action-login]").click();
        $(withText("Личный кабинет")).shouldBe(visible);
    }

    @Test
    void shouldSendBlockedUser() {
        var blockedUser=generateUser("blocked");
        $("[data-test-id=login] input").setValue(blockedUser.getLogin());
        $("[data-test-id=password] input").setValue(blockedUser.getPassword());
        $("button[data-test-id=action-login]").click();
        $(withText("Пользователь заблокирован")).shouldBe(visible);
    }

    @Test
    void  shouldGetErrorIfWrongLogin() {
        var wrongLoginUser=generateWrongLoginUser("active");
        $("[data-test-id=login] input").setValue(wrongLoginUser.getLogin());
        $("[data-test-id=password] input").setValue(wrongLoginUser.getPassword());
        $("button[data-test-id=action-login]").click();
        $(withText("Неверно указан логин или пароль")).shouldBe(visible);

    }
    @Test
    void shouldGetErrorIfWrongPassword() {
        var wrongPasswordUser=generateWrongPasswordUser("active");
        $("[data-test-id=login] input").setValue(wrongPasswordUser.getLogin());
        $("[data-test-id=password] input").setValue(wrongPasswordUser.getPassword());
        $("button[data-test-id=action-login]").click();
        $(withText("Неверно указан логин или пароль")).shouldBe(visible);

    }

}

