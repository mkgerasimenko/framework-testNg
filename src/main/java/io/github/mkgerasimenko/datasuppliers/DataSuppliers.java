package io.github.mkgerasimenko.datasuppliers;

import io.github.mkgerasimenko.customannotation.Source;
import io.github.sskorol.core.DataSupplier;
import one.util.streamex.StreamEx;

import java.lang.reflect.Method;
import java.util.Arrays;

import static io.github.mkgerasimenko.listeners.ReadersListener.readerOf;
import static io.github.mkgerasimenko.utils.ReflectUtils.getObjectByArgs;
import static java.util.Optional.ofNullable;

/**
 * A simple model class for data processing.
 */
public class DataSuppliers {

    @SuppressWarnings("unchecked")
    @DataSupplier(flatMap = true)
    public <T> StreamEx<T> getObject(final Method method) {
        return ofNullable(method.getDeclaredAnnotation(Data.class))
                .map(data -> StreamEx.of(readerOf(data.source())
                        .readFrom(data.source(), (Class<T>) data.entity())))
                .orElseGet(StreamEx::empty);
    }

    @SuppressWarnings("unchecked")
    @DataSupplier(transpose = true)
    public <T> T[] getDataCollection(final Method method) {
        return (T[]) ofNullable(method.getAnnotationsByType(Data.class))
                .map(data -> StreamEx.of(data)
                        .flatMap(dataIn -> Arrays.stream(readerOf(dataIn.source())
                                .readFrom(dataIn.source(), (Class<T>) dataIn.entity()))).toArray())
                .orElseThrow(() -> new NoClassDefFoundError("No Data class found"));
    }

    @SuppressWarnings("unchecked")
    @DataSupplier(flatMap = true)
    public <T> T getDataSet(final Method method) {
        return (T) ofNullable(method.getDeclaredAnnotation(Data.class).entity().getDeclaredFields())
                .map(fields -> StreamEx.of(fields)
                        .flatMap(field -> Arrays.stream(readerOf(field.getType().getAnnotation(Source.class).source())
                                .readFrom(field.getType().getAnnotation(Source.class).source(),
                                        field.getType()))).toArray())
                .map(arr -> getObjectByArgs(method.getDeclaredAnnotation(Data.class).entity(), arr))
                .orElseGet(StreamEx::empty);
    }
}
