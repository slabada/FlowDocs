package com.flowdocs.model;

public record RegistrationModel(
        String firstName,
        String lastName,
        String email,
        String password,
        String role) {
}
