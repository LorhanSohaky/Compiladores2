#!/bin/bash

java -jar libs/antlr-4.7.2-complete.jar -package la.linguagem.ANTLR AnalisadorLA/src/la/linguagem/ANTLR/la.g4 -visitor
