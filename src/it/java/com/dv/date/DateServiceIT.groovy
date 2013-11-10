package com.dv.date

import com.dv.bootstrap.DateBootstrap
import com.dv.config.Resources
import org.jboss.arquillian.container.test.api.Deployment
import org.jboss.arquillian.container.test.api.RunAsClient
import org.jboss.arquillian.junit.Arquillian
import org.jboss.arquillian.test.api.ArquillianResource
import org.jboss.resteasy.client.jaxrs.ResteasyClientBuilder
import org.jboss.shrinkwrap.api.Archive
import org.jboss.shrinkwrap.api.spec.WebArchive
import org.joda.time.DateTime
import org.joda.time.DateTimeZone
import org.junit.Test
import org.junit.runner.RunWith

import static javax.ws.rs.core.UriBuilder.fromUri
import static org.jboss.shrinkwrap.api.ShrinkWrap.create
import static org.jboss.shrinkwrap.api.asset.EmptyAsset.INSTANCE
import static org.jboss.shrinkwrap.resolver.api.maven.Maven.resolver
import static org.joda.time.DateTimeZone.UTC
import static org.joda.time.DateTimeZone.forID
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
                .addClass(DateService)
                .addClass(DateBootstrap)
                .addClass(FindLocationsQuery)
                .addClass(Location)
                .addClass(Resources)
                .addAsLibraries(dependencies)
                .addAsWebInfResource('persistence.xml', 'classes/META-INF/persistence.xml')
                .addAsWebInfResource('glassfish-resources.xml', 'glassfish-resources.xml')
                .addAsWebInfResource(INSTANCE, 'beans.xml')
                .addAsWebInfResource('web.xml')

        archive
    }

    @ArquillianResource
    URL deploymentUrl

    @Test
    void 'Given source time, should return the same time in UTC'() {
        def sourceTime = new DateTime(UTC)
                .withDate(2010, 10, 20)
                .withTime(0, 0, 0, 0)
        def url = fromUri(deploymentUrl.toURI())
                .path('rest')
                .path('dates')
                .path(sourceTime.toString())
                .build()

        def response = new ResteasyClientBuilder()
                .build()
                .target(url)
                .request()
                .get()
                .readEntity(DatesModel)

        assert sourceTime == response.dates.UTC
    }

    @Test
    void 'Given source time, should return the same time in London zone'() {
        def sourceTime = new DateTime(UTC)
                .withDate(2010, 10, 20)
                .withTime(0, 0, 0, 0)
        def url = fromUri(deploymentUrl.toURI())
                .path('rest')
                .path('dates')
                .path(sourceTime.toString())
                .build()

        def response = new ResteasyClientBuilder()
                .build()
                .target(url)
                .request()
                .get()
                .readEntity(DatesModel)

        assert sourceTime.withZone(forID('Europe/London')) == response.dates.'Europe/London'
    }
}
