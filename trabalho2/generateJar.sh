#!/bin/bash

JAVA_FILES="$(find AnalisadorMarkdown/src/ -name "*.java" -type f)"


javac -d AnalisadorMarkdown/bin -cp "libs/antlr-4.7.2-complete.jar" $JAVA_FILES

jar cfm meu_compilador.jar manifest -C AnalisadorMarkdown/bin/ .

