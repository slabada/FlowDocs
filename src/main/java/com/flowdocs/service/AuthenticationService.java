package com.flowdocs.service;

import com.flowdocs.model.LoginModel;
import com.flowdocs.model.RegistrationModel;

public interface AuthenticationService {

    /**
     * Метод для регистрации нового пользователя
     *
     * @param reg Объект хронящий всю информацию для регистрации
     * @return JWT токен
     */
    String register(RegistrationModel reg);

    /**
     * Метод для авторизации пользователя
     *
     * @param login Объект который хранит всю информацию для авторизации
     * @return JWT токен
     */
    String login(LoginModel login);

}
