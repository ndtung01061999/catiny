package com.regitiny.catiny.service;

import com.regitiny.catiny.GeneratedByJHipster;

@GeneratedByJHipster
public class EmailAlreadyUsedException extends RuntimeException {

    private static final long serialVersionUID = 1L;

    public EmailAlreadyUsedException() {
        super("Email is already in use!");
    }
}
