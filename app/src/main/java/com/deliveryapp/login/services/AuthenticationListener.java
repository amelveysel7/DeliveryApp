package com.deliveryapp.login.services;

public interface AuthenticationListener<T> {
    void onSuccess(T result);
    void onFailure(String message);
}
