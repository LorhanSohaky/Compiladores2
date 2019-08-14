#!/bin/bash

JAVA_FILES="$(find AnalisadorLA/src/ -name "*.java" -type f)"


javac -cp "libs/antlr-4.7.2-complete.jar" $JAVA_FILES

jar cfm meu_compilador.jar manifest -C AnalisadorLA/bin/ .

