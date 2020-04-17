package server.business;

public interface Validator<T> {
    void validate(T t);
}
