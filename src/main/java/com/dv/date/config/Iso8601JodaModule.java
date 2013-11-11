package com.dv.date.config;

import com.fasterxml.jackson.databind.module.SimpleModule;
import com.fasterxml.jackson.datatype.joda.PackageVersion;
import com.fasterxml.jackson.datatype.joda.deser.DateTimeDeserializer;
import com.fasterxml.jackson.datatype.joda.ser.DateTimeSerializer;
import org.joda.time.DateTime;

public class Iso8601JodaModule extends SimpleModule {

    public Iso8601JodaModule() {

        super(PackageVersion.VERSION);
        addDeserializer(DateTime.class, new Iso8601DateTimeDeserializer());
        addSerializer(DateTime.class, new DateTimeSerializer());
    }
}
