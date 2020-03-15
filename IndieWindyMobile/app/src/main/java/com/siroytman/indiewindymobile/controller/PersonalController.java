package com.siroytman.indiewindymobile.controller;

import com.siroytman.indiewindymobile.api.ApiController;

public class PersonalController {
    private static final String TAG = "PersonalController";
    private ApiController apiController;
    private static PersonalController instance;

    private PersonalController() {
        apiController = ApiController.getInstance();
    }

    public static synchronized PersonalController getInstance() {
        if (instance == null) {
            instance = new PersonalController();
        }
        return instance;
    }


}
