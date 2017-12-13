package io.github.mkgerasimenko.datasuppliers;

import io.github.sskorol.core.DataSupplier;
import one.util.streamex.StreamEx;

import java.lang.reflect.Method;
import java.util.Arrays;

import static io.github.mkgerasimenko.listeners.ReadersListener.getImplByDataSource;
import static java.util.Optional.ofNullable;

/**
 * A simple model class for data processing.
 */
public class DataSuppliers {

    @SuppressWarnings("unchecked")
    @DataSupplier(transpose = true)
    public <T> T[] getDataCollection(final Method method) {
        return (T[]) ofNullable(method.getAnnotationsByType(Data.class))
                .map(data -> StreamEx.of(data)
                        .flatMap(dataIn -> Arrays.stream(getImplByDataSource(dataIn.source())
                                .readFrom(dataIn.source(), (Class<T>) dataIn.entity()))).toArray())
                .orElseThrow(() -> new NoClassDefFoundError("No Data class found"));
    }

    @SuppressWarnings("unchecked")
    @DataSupplier(flatMap = true)
    public <T> StreamEx<T> getData(final Method method) {
        return ofNullable(method.getDeclaredAnnotation(Data.class))
                .map(data -> StreamEx.of(getImplByDataSource(data.source())
                        .readFrom(data.source(), (Class<T>) data.entity())))
                .orElseThrow(() -> new NoClassDefFoundError("No Data class found"));
    }
}
