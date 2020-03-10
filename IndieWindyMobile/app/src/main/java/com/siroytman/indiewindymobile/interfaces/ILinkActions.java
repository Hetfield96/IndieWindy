package com.siroytman.indiewindymobile.interfaces;

public interface ILinkActions<T> {
    public T getItem();
    public void removed();
    public void added();
}
