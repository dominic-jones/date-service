package com.dv.date

import com.dv.date.bootstrap.DateBootstrap
import com.dv.date.config.Iso8601DateTimeDeserializer
import com.dv.date.config.Iso8601JodaModule
import com.dv.date.config.JacksonConfig
import com.dv.date.config.Resources
import com.dv.date.impl.*
import org.jboss.arquillian.container.test.api.Deployment
import org.jboss.arquillian.container.test.api.RunAsClient
import org.jboss.arquillian.junit.Arquillian
import org.jboss.arquillian.test.api.ArquillianResource
import org.jboss.resteasy.client.jaxrs.ResteasyClientBuilder
import org.jboss.shrinkwrap.api.Archive
import org.jboss.shrinkwrap.api.spec.WebArchive
import org.joda.time.DateTime
import org.junit.Test
import org.junit.runner.RunWith

import static javax.ws.rs.client.Entity.entity
import static javax.ws.rs.core.MediaType.APPLICATION_JSON_TYPE
import static javax.ws.rs.core.UriBuilder.fromUri
import static org.jboss.shrinkwrap.api.ShrinkWrap.create
import static org.jboss.shrinkwrap.api.asset.EmptyAsset.INSTANCE
import static org.jboss.shrinkwrap.resolver.api.maven.Maven.resolver
import static org.joda.time.DateTimeZone.UTC
import static org.joda.time.DateTimeZone.forID

/**
 * Sample integration test using groovy and Arquillian. The JAXRS service is loaded and run n a WAR on an embedded
 * Glassfish instance. This test runs as a client from outside of the container,
 * using JAXRS Client libraries to perform an HTTP GET to the api url.
 */
@RunWith(Arquillian)
@RunAsClient
public class DateServiceIT {

    @Deployment
    static Archive<WebArchive> deployment() {

        def dependencies = resolver()
                .loadPomFromFile('pom.xml')
                .importCompileAndRuntimeDependencies()
                .resolve()
                .withTransitivity()
                .asFile()

        def archive = create(WebArchive, 'date-test.war')
                .addPackages(true, 'com.dv.date')
                .addAsLibraries(dependencies)
                .addAsWebInfResource('persistence.xml', 'classes/META-INF/persistence.xml')
                .addAsWebInfResource('glassfish-resources.xml', 'glassfish-resources.xml')
                .addAsWebInfResource(INSTANCE, 'beans.xml')
                .addAsWebInfResource('web.xml')

        archive
    }

    @ArquillianResource
    URL deploymentUrl

    def sourceTime = new DateTime(UTC)
            .withDate(2010, 10, 20)
            .withTime(0, 0, 0, 0)
    def builder = new ResteasyClientBuilder()
    def tokyo = 'Asia/Tokyo'

    @Test
    void 'Given source time, should return the same time in UTC'() {
        def url = fromUri(deploymentUrl.toURI())
                .path('rest')
                .path('dates')
                .path(sourceTime.toString())
                .build()

        def response = builder.build()
                .target(url)
                .request()
                .get()
                .readEntity(DatesModel)

        assert sourceTime.toLocalDateTime() == response.dates.UTC.toLocalDateTime()
    }

    @Test
    void 'Given source time, should return the same time in London zone'() {
        def url = fromUri(deploymentUrl.toURI())
                .path('rest')
                .path('dates')
                .path(sourceTime.toString())
                .build()

        def response = builder
                .build()
                .target(url)
                .request()
                .get()
                .readEntity(DatesModel)

        assert sourceTime.withZone(forID('Europe/London')).toLocalDateTime() == response.dates.'Europe/London'.toLocalDateTime()
    }

    @Test
    void 'Given a new location, should add a new timezone'() {
        def url = fromUri(deploymentUrl.toURI())
                .path('rest')
                .path('dates')
                .build()

        builder.build()
                .target(url)
                .request()
                .post(entity(tokyo, APPLICATION_JSON_TYPE))

        def getUrl = fromUri(deploymentUrl.toURI())
                .path('rest')
                .path('dates')
                .path(sourceTime.toString())
                .build()
        def response = builder.build()
                .target(getUrl)
                .request()
                .get(DatesModel)
        assert sourceTime.withZone(forID(tokyo)).toLocalDateTime() == response.dates.'Asia/Tokyo'.toLocalDateTime()
    }

}
