package com.systango.viperboilerplate.common;

/**
 * Created by Mohit Rajput on 13/3/19.
 * Maps one entity of one layer to another entity of different layer
 */
public interface Mapper<E, T> {
    public T mapFrom(E from);
}
