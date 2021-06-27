package com.regitiny.catiny.service;

import com.regitiny.catiny.GeneratedByJHipster;

@GeneratedByJHipster
public class UsernameAlreadyUsedException extends RuntimeException {

    private static final long serialVersionUID = 1L;

    public UsernameAlreadyUsedException() {
        super("Login name already used!");
    }
}
