package net.nejiwwkr.project_industrial.util.exception;

public class ProjectIndustrialModRuntimeException extends RuntimeException{
    private final Object cause;

    public ProjectIndustrialModRuntimeException(String message, Object cause) {
        super(message);
        this.cause = cause;
    }

    @Override
    public String toString() {
        return "problem by" + cause.getClass() + "for the reason";
    }
}
