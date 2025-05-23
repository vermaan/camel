/*
 * Licensed to the Apache Software Foundation (ASF) under one or more
 * contributor license agreements.  See the NOTICE file distributed with
 * this work for additional information regarding copyright ownership.
 * The ASF licenses this file to You under the Apache License, Version 2.0
 * (the "License"); you may not use this file except in compliance with
 * the License.  You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.apache.camel.telemetry.decorators;

import org.apache.camel.Endpoint;
import org.apache.camel.Exchange;
import org.apache.camel.telemetry.Span;

public class KafkaSpanDecorator extends AbstractMessagingSpanDecorator {

    public static final String KAFKA_PARTITION_TAG = "kafka.partition";
    public static final String KAFKA_PARTITION_KEY_TAG = "kafka.partition.key";
    public static final String KAFKA_KEY_TAG = "kafka.key";
    public static final String KAFKA_OFFSET_TAG = "kafka.offset";

    /**
     * Constants copied from {@link org.apache.camel.component.kafka.KafkaConstants}
     */
    protected static final String PARTITION_KEY = "kafka.PARTITION_KEY";
    protected static final String PARTITION = "kafka.PARTITION";
    protected static final String KEY = "kafka.KEY";
    protected static final String OVERRIDE_TOPIC = "kafka.OVERRIDE_TOPIC";
    protected static final String OFFSET = "kafka.OFFSET";

    @Override
    public String getComponent() {
        return "kafka";
    }

    @Override
    public String getComponentClassName() {
        return "org.apache.camel.component.kafka.KafkaComponent";
    }

    @Override
    public String getDestination(Exchange exchange, Endpoint endpoint) {
        String topic = exchange.getIn().getHeader(OVERRIDE_TOPIC, String.class);
        if (topic == null) {
            topic = stripSchemeAndOptions(endpoint);
        }
        return topic;
    }

    @Override
    public void beforeTracingEvent(Span span, Exchange exchange, Endpoint endpoint) {
        super.beforeTracingEvent(span, exchange, endpoint);

        String partition = getValue(exchange, PARTITION, Integer.class);
        if (partition != null) {
            span.setTag(KAFKA_PARTITION_TAG, partition);
        }

        String partitionKey = exchange.getIn().getHeader(PARTITION_KEY, String.class);
        if (partitionKey != null) {
            span.setTag(KAFKA_PARTITION_KEY_TAG, partitionKey);
        }

        String key = exchange.getIn().getHeader(KEY, String.class);
        if (key != null) {
            span.setTag(KAFKA_KEY_TAG, key);
        }

        String offset = getValue(exchange, OFFSET, String.class);
        if (offset != null) {
            span.setTag(KAFKA_OFFSET_TAG, offset);
        }
    }

    /**
     * Extracts header value from the exchange for given header
     *
     * @param exchange the {@link Exchange}
     * @param header   the header name
     * @param type     the class type of the exchange header
     */
    private <T> String getValue(final Exchange exchange, final String header, Class<T> type) {
        T partition = exchange.getIn().getHeader(header, type);
        return partition != null ? String.valueOf(partition) : exchange.getIn().getHeader(header, String.class);
    }

}
