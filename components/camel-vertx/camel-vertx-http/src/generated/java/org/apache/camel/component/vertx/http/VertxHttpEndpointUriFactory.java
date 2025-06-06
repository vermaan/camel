/* Generated by camel build tools - do NOT edit this file! */
package org.apache.camel.component.vertx.http;

import javax.annotation.processing.Generated;
import java.net.URISyntaxException;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import org.apache.camel.spi.EndpointUriFactory;

/**
 * Generated by camel build tools - do NOT edit this file!
 */
@Generated("org.apache.camel.maven.packaging.GenerateEndpointUriFactoryMojo")
public class VertxHttpEndpointUriFactory extends org.apache.camel.support.component.EndpointUriFactorySupport implements EndpointUriFactory {

    private static final String BASE = ":httpUri";

    private static final Set<String> PROPERTY_NAMES;
    private static final Set<String> SECRET_PROPERTY_NAMES;
    private static final Set<String> MULTI_VALUE_PREFIXES;
    static {
        Set<String> props = new HashSet<>(27);
        props.add("basicAuthPassword");
        props.add("basicAuthUsername");
        props.add("bearerToken");
        props.add("bridgeEndpoint");
        props.add("connectTimeout");
        props.add("cookieStore");
        props.add("headerFilterStrategy");
        props.add("httpMethod");
        props.add("httpUri");
        props.add("lazyStartProducer");
        props.add("multipartUpload");
        props.add("multipartUploadName");
        props.add("okStatusCodeRange");
        props.add("proxyHost");
        props.add("proxyPassword");
        props.add("proxyPort");
        props.add("proxyType");
        props.add("proxyUsername");
        props.add("responsePayloadAsByteArray");
        props.add("sessionManagement");
        props.add("sslContextParameters");
        props.add("throwExceptionOnFailure");
        props.add("timeout");
        props.add("transferException");
        props.add("useCompression");
        props.add("vertxHttpBinding");
        props.add("webClientOptions");
        PROPERTY_NAMES = Collections.unmodifiableSet(props);
        SECRET_PROPERTY_NAMES = Collections.emptySet();
        MULTI_VALUE_PREFIXES = Collections.emptySet();
    }

    @Override
    public boolean isEnabled(String scheme) {
        return "vertx-http".equals(scheme);
    }

    @Override
    public String buildUri(String scheme, Map<String, Object> properties, boolean encode) throws URISyntaxException {
        String syntax = scheme + BASE;
        String uri = syntax;

        Map<String, Object> copy = new HashMap<>(properties);

        uri = buildPathParameter(syntax, uri, "httpUri", null, true, copy);
        uri = buildQueryParameters(uri, copy, encode);
        return uri;
    }

    @Override
    public Set<String> propertyNames() {
        return PROPERTY_NAMES;
    }

    @Override
    public Set<String> secretPropertyNames() {
        return SECRET_PROPERTY_NAMES;
    }

    @Override
    public Set<String> multiValuePrefixes() {
        return MULTI_VALUE_PREFIXES;
    }

    @Override
    public boolean isLenientProperties() {
        return true;
    }
}

