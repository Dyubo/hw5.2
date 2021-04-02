package ru.netology;

import lombok.val;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.Selectors.withText;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;
import static ru.netology.DataGeneration.Registration.*;

public class AuthTest {
    @BeforeEach
    void setUp(){
        open ("http://localhost:9999/");
    }

    @Test
    void shouldSendFormValid() {
        val validUser=generateUser("active");
        $("[name=login]").setValue(validUser.getLogin());
        $("[name=password]").setValue(validUser.getPassword());
        $("[data-test-id=action-login]").click();
        $(withText("Личный кабинет")).shouldBe(visible);
    }

    @Test
    void shouldSendFormBlockedUser() {
        val blockedUser=generateUser("blocked");
        $("[data-test-id=login] input").setValue(blockedUser.getLogin());
        $("[data-test-id=password] input").setValue(blockedUser.getPassword());
        $("button[data-test-id=action-login]").click();
        $(withText("Пользователь заблокирован")).shouldBe(visible);
    }

    @Test
    void  shouldGetErrorIfWrongLogin() {
        val wrongLoginUser=generateWrongLoginUser("active");
        $("[data-test-id=login] input").setValue(wrongLoginUser.getLogin());
        $("[data-test-id=password] input").setValue(wrongLoginUser.getPassword());
        $("button[data-test-id=action-login]").click();
        $(withText("Неверно указан логин или пароль")).shouldBe(visible);

    }
    @Test
    void shouldGetErrorIfWrongPassword() {
        val wrongPasswordUser=generateWrongPasswordUser("active");
        $("[data-test-id=login] input").setValue(wrongPasswordUser.getLogin());
        $("[data-test-id=password] input").setValue(wrongPasswordUser.getPassword());
        $("button[data-test-id=action-login]").click();
        $(withText("Неверно указан логин или пароль")).shouldBe(visible);

    }
    @Test
    void shouldPassWithActiveUser() {
        val validUser = generateUser("active");
        $("[name=login]").setValue(validUser.getLogin());
        $("[name=password]").setValue(validUser.getPassword());
        $("[data-test-id=action-login]").click();
        $(withText("Личный кабинет")).shouldBe(visible);

}}

