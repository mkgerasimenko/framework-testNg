package io.github.mkgerasimenko.datasuppliers;

import io.github.sskorol.core.DataSupplier;
import one.util.streamex.StreamEx;

import java.lang.reflect.Method;
import java.util.Optional;

import static io.github.mkgerasimenko.listeners.ReadersListener.getImplByDataSource;
import static java.util.Optional.ofNullable;

/**
 * A simple model class for data processing.
 */
public class DataSuppliers {

    @SuppressWarnings("unchecked")
    @DataSupplier(transpose = true)
    public <T> T[] getDataCollection(final Method method) {
        final Optional<Data[]> annotationsByType = ofNullable(method.getAnnotationsByType(Data.class));
        if (!annotationsByType.isPresent()) {
            throw new NoClassDefFoundError("Data class not found");
        }
        return (T[]) StreamEx.of(annotationsByType)
                .flatMap(StreamEx::of)
                .flatMap(data -> StreamEx.of(getImplByDataSource(data.source())
                        .readFrom(data.source(), (Class<T>) data.entity())))
                .toArray();
    }

    @DataSupplier(flatMap = true)
    public <T> StreamEx<T> getData(final Method method) {
        return getTypeByProvidedInfo(method);
    }

    @SuppressWarnings("unchecked")
    private <T> StreamEx<T> getTypeByProvidedInfo(final Method method) {
        return ofNullable(method.getDeclaredAnnotation(Data.class))
                .map(data -> StreamEx.of(getImplByDataSource(data.source())
                        .readFrom(data.source(), (Class<T>) data.entity())))
                .orElseThrow(() -> new NoClassDefFoundError("No Data class found"));
    }
}
