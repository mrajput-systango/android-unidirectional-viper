package com.systango.viperboilerplate.common;

/**
 * Created by Mohit Rajput on 13/3/19.
 * TODO: Insert javadoc information here
 */
public abstract class Mapper<E, T> {
    public abstract T mapFrom(E from);
}
