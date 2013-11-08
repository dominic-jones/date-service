package com.dv.date

import org.jboss.arquillian.container.test.api.Deployment
import org.jboss.arquillian.container.test.api.RunAsClient
import org.jboss.arquillian.junit.Arquillian
import org.jboss.arquillian.test.api.ArquillianResource
import org.jboss.resteasy.client.jaxrs.ResteasyClientBuilder
import org.jboss.shrinkwrap.api.Archive
import org.jboss.shrinkwrap.api.spec.WebArchive
import org.junit.Test
import org.junit.runner.RunWith

import static javax.ws.rs.core.UriBuilder.fromUri
import static org.jboss.shrinkwrap.api.ShrinkWrap.create
import static org.jboss.shrinkwrap.api.asset.EmptyAsset.INSTANCE

@RunWith(Arquillian)
@RunAsClient
public class DateServiceIT {

    @Deployment
    static Archive<WebArchive> deployment() {

        def archive = create(WebArchive, 'date-test.war')
                .addClass(DateService)
                .addAsManifestResource(INSTANCE, 'beans.xml')
                .addAsManifestResource('context.xml')
                .addAsWebInfResource('web.xml')

        archive
    }

    @ArquillianResource
    URL deploymentUrl

    @Test
    public void test() {
        def url = fromUri(deploymentUrl.toURI())
                .path('rest')
                .path('dates')
                .build()

        def response = new ResteasyClientBuilder()
                .build()
                .target(url)
                .request()
                .get()
                .readEntity(String)

        assert '["10/10/2010"]'.equals(response)
    }
}
