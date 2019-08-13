#!/bin/bash

rm -rd temp

./generateGrammar.sh

# TODO generateJar

java -jar AnalisadorLA/libs/CorretorTrabalho1.jar "java -jar meu_compilador.jar" gcc temp casosDeTesteT1 "740951 587087 586730 619884" sintatico

