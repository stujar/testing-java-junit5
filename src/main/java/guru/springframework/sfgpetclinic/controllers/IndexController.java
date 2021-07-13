package guru.springframework.sfgpetclinic.controllers;

import guru.springframework.sfgpetclinic.exceptionhandlers.ValueNotFoundException;

public class IndexController {

    public String index(){
        return "index";
    }

    public String oupsHandler() throws ValueNotFoundException {
        throw new ValueNotFoundException();
    }
}
