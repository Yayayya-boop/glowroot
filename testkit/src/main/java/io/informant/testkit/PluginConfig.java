/**
 * Copyright 2011-2013 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package io.informant.testkit;

import io.informant.testkit.internal.ObjectMappers;

import java.util.Map;

import checkers.nullness.quals.Nullable;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.common.base.Objects;
import com.google.common.collect.Maps;

/**
 * @author Trask Stalnaker
 * @since 0.5
 */
public class PluginConfig {

    private static final ObjectMapper mapper = ObjectMappers.create();

    @JsonProperty
    @Nullable
    private String groupId;
    @JsonProperty
    @Nullable
    private String artifactId;
    private boolean enabled;
    @JsonProperty
    private final Map<String, /*@Nullable*/Object> properties = Maps.newHashMap();
    @Nullable
    private String version;

    @Nullable
    public String getGroupId() {
        return groupId;
    }

    @Nullable
    public String getArtifactId() {
        return artifactId;
    }

    public boolean isEnabled() {
        return enabled;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }

    public boolean hasProperty(String name) {
        return properties.containsKey(name);
    }

    @Nullable
    public Object getProperty(String name) {
        return properties.get(name);
    }

    public void setProperty(String name, @Nullable Object value) {
        properties.put(name, value);
    }

    @Nullable
    public String getVersion() {
        return version;
    }

    void setVersion(@Nullable String version) {
        this.version = version;
    }

    Map<String, /*@Nullable*/Object> getProperties() {
        return properties;
    }

    @Override
    public boolean equals(@Nullable Object obj) {
        if (obj instanceof PluginConfig) {
            PluginConfig that = (PluginConfig) obj;
            // intentionally leaving off version since it represents the prior version hash when
            // sending to the server, and represents the current version hash when receiving from
            // the server
            return Objects.equal(enabled, that.enabled)
                    && Objects.equal(properties, that.properties);
        }
        return false;
    }

    @Override
    public int hashCode() {
        // intentionally leaving off version since it represents the prior version hash when
        // sending to the server, and represents the current version hash when receiving from the
        // server
        return Objects.hashCode(enabled, properties);
    }

    @Override
    public String toString() {
        return Objects.toStringHelper(this)
                .add("enabled", enabled)
                .add("properties", properties)
                .add("version", version)
                .toString();
    }
}
