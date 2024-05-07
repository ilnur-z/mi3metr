package ru.zia.mi3metr.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Контроллер основной страницы.
 */
@Controller
@RequestMapping("/")
public class MainController {

    /**
     * Возвращает главную страницы.
     *
     * @return имя шаблона
     */
    @GetMapping
    public String welcome() {
        return "index";
    }

}
