package video.api.uploader.api.models;

public class Nullable<T> {
    private T value;

    public Nullable() {
    }

    public Nullable(T value) {
        this.value = value;
    }

    public static <T> Nullable<T> of(T value) {
        return new Nullable<>(value);
    }

    public T getValue() {
        return value;
    }
}
