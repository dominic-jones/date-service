package com.dv.date.config;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonToken;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.deser.std.StdScalarDeserializer;
import com.fasterxml.jackson.databind.jsontype.TypeDeserializer;
import org.joda.time.DateTime;
import org.joda.time.DateTimeZone;
import org.joda.time.format.ISODateTimeFormat;

import java.io.IOException;

/**
 * Adapted from com.fasterxml.jackson.datatype.joda.deser.DateTimeDeserializer.
 */
public class Iso8601DateTimeDeserializer
        extends StdScalarDeserializer<DateTime> {

    public Iso8601DateTimeDeserializer() {

        super(DateTime.class);
    }

    @Override
    public DateTime deserialize(JsonParser jp,
                                DeserializationContext ctxt)
            throws IOException {

        JsonToken t = jp.getCurrentToken();
        if (t == JsonToken.VALUE_NUMBER_INT) {
            return new DateTime(jp.getLongValue(), DateTimeZone.forTimeZone(ctxt.getTimeZone()));
        }
        if (t == JsonToken.VALUE_STRING) {
            String str = jp.getText().trim();
            if (str.length() == 0) {
                return null;
            }
            DateTime dateTime = DateTime.parse(str);
            return dateTime;
        }
        throw ctxt.mappingException(getValueClass());
    }

    @Override
    public Object deserializeWithType(JsonParser jp,
                                      DeserializationContext ctxt,
                                      TypeDeserializer typeDeserializer) throws IOException {

        return typeDeserializer.deserializeTypedFromAny(jp, ctxt);
    }
}
