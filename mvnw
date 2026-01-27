#!/bin/sh
# ----------------------------------------------------------------------------
# Licensed to the Apache Software Foundation (ASF) under one
# or more contributor license agreements.  See the NOTICE file
# distributed with this work for additional information
# regarding copyright ownership.  The ASF licenses this file
# to you under the Apache License, Version 2.0 (the
# "License"); you may not use this file except in compliance
# with the License.  You may obtain a copy of the License at
#
#    http://www.apache.org/licenses/LICENSE-2.0
#
# Unless required by applicable law or agreed to in writing,
# software distributed under the License is distributed on an
# "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
# KIND, either express or implied.  See the License for the
# specific language governing permissions and limitations
# under the License.
# ----------------------------------------------------------------------------

if [ -z "$MAVEN_PROJECTBASEDIR" ]; then
  MAVEN_PROJECTBASEDIR=`pwd`
fi
MAVEN_PROJECTBASEDIR=`cd "$MAVEN_PROJECTBASEDIR"; pwd`

if [ -z "$MAVEN_HOME" ]; then
  MAVEN_HOME="$MAVEN_PROJECTBASEDIR/.mvn/wrapper"
fi

if [ ! -d "$MAVEN_HOME" ]; then
  mkdir -p "$MAVEN_HOME"
fi

MAVEN_WRAPPER_JAR="$MAVEN_HOME/maven-wrapper.jar"
MAVEN_WRAPPER_PROPERTIES="$MAVEN_HOME/maven-wrapper.properties"

if [ ! -f "$MAVEN_WRAPPER_JAR" ] || [ ! -f "$MAVEN_WRAPPER_PROPERTIES" ]; then
  echo "Downloading maven wrapper..."
  curl -fsSL -o "$MAVEN_WRAPPER_JAR" "https://repo.maven.apache.org/maven2/org/apache/maven/wrapper/maven-wrapper/3.2.0/maven-wrapper-3.2.0.jar"
fi

MAVEN_VERSION=$(grep distributionUrl "$MAVEN_WRAPPER_PROPERTIES" | sed 's/.*apache-maven-\([^-]*\)-.*/\1/')

if [ ! -d "$MAVEN_HOME/apache-maven-$MAVEN_VERSION" ]; then
  echo "Downloading Maven $MAVEN_VERSION..."
  curl -fsSL -o "$MAVEN_HOME/maven-$MAVEN_VERSION.zip" "https://repo.maven.apache.org/maven2/org/apache/maven/apache-maven/$MAVEN_VERSION/apache-maven-$MAVEN_VERSION-bin.zip"
  unzip -q "$MAVEN_HOME/maven-$MAVEN_VERSION.zip" -d "$MAVEN_HOME"
  rm "$MAVEN_HOME/maven-$MAVEN_VERSION.zip"
fi

MAVEN_HOME="$MAVEN_HOME/apache-maven-$MAVEN_VERSION"

exec "$MAVEN_HOME/bin/mvn" "$@"
