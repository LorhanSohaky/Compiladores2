#!/bin/bash

java -jar libs/antlr-4.7.2-complete.jar -package antlr AnalisadorMarkdown/src/antlr/marktex.g4 -visitor
