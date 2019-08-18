#!/bin/bash

if [ -d saida_testes ]; then
    rm -rd saida_testes
fi

./generateGrammar.sh

./generateJar.sh

java -jar libs/CorretorTrabalho1.jar "java -jar meu_compilador.jar" gcc saida_testes casosDeTesteT1 "740951 587087 586730 619884" tudo

