package org.ikhripunov.pancake.configuration;

import org.dozer.CustomConverter;
import org.dozer.MappingException;

import java.util.UUID;

public class StringToUUIDConverter implements CustomConverter {

    public Object convert(Object destination, Object source,
                          Class destClass, Class sourceClass) {
        if (source == null) {
            return null;
        }
        if (source instanceof String) {
            return UUID.fromString((String)source);
        } else if (source instanceof UUID) {
            return ((UUID) source).toString();
        } else {
            throw new MappingException("Converter StringToUUIDConverter "
                    + "used incorrectly. Arguments passed in were:"
                    + destination + " and " + source);
        }
    }
}
