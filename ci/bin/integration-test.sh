#!/usr/bin/env bash
echo "Integration Test START"
#java -jar ${PKG_BASE_DIR}/HelloWorld/HelloWorld-0.0.1-SNAPSHOT.jar
curl http://localhost:9999/HelloWorldExample/hello
