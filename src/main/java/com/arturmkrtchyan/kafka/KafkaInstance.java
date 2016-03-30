/*
 * Copyright 2015 Artur Mkrtchyan
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.arturmkrtchyan.kafka;

import java.nio.file.Path;

public class KafkaInstance {

    private final Path path;

    private static boolean onWindows = false;
    private static String scriptFileSuffix;

    private static final String kafkaBinaryPath;

    static {
        onWindows = System.getProperty("os.name").startsWith("Windows");
        scriptFileSuffix = onWindows ? "bat" : "sh";
        kafkaBinaryPath = onWindows ? "bin/windows/" : "bin/";
    }

    private KafkaInstance(final Path instancePath) {
        this.path = instancePath;
    }

    protected static KafkaInstance fromPath(final Path instancePath) {
        return new KafkaInstance(instancePath);
    }

    public Path getPath() {
        return path;
    }

    public Path getStartupScript() {
        return getBinaryPath("kafka-server-start");
    }

    public Path getShutdownScript() {
        return getBinaryPath("kafka-server-stop");
    }

    public Path getZookeeperStartupScript() {
        return getBinaryPath("zookeeper-server-start");
    }

    public Path getZookeeperShutdownScript() {
        return getBinaryPath("zookeeper-server-stop");
    }

    public Path getConfig() {
        return getPath().resolve("config/server.properties");
    }

    public Path getZookeeperConfig() {
        return getPath().resolve("config/zookeeper.properties");
    }

    private Path getBinaryPath(String scriptName) {
        return getPath().resolve(kafkaBinaryPath + scriptName"." + scriptFileSuffix);
    }

}
