package net.nejiwwkr.project_industrial.util.exception;

import net.nejiwwkr.project_industrial.util.annotation.Essential;

import java.util.Arrays;

public final class EssentialMethodNotInvokedException extends RuntimeException{
    private final Class<?> clazz;

    public EssentialMethodNotInvokedException (Class<?> clazz) {
        super();
        this.clazz = clazz;
    }

    @Override
    public String toString() {
        return "Essential method" + Arrays.stream(clazz.getDeclaredMethods()).filter(x -> x.getAnnotation(Essential.class) == null) + "ont invoked.";
    }
}
