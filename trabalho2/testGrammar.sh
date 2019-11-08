#!/bin/bash

./generateGrammar.sh

./generateJar.sh

if [ ! -d saidaProduzida ]
then
  mkdir -p saidaProduzida
fi
quantidade_de_testes=$(find casosDeTeste/entrada/*.md | wc -l)

acertos=0
erros=0

for i in $(seq 0 $(expr $quantidade_de_testes - 1) ); do
    java -jar meu_compilador.jar casosDeTeste/entrada/$i.md saidaProduzida/$i.tex
    diff casosDeTeste/saida/$i.tex saidaProduzida/$i.tex -q
    if [ $? -eq 1 ]
    then
        erros=$(expr $erros + 1)
    else
        acertos=$(expr $acertos + 1)
    fi
done

media=$(echo 'scale=2;' $acertos / $quantidade_de_testes | bc)

echo '----! Resultado !----'
echo 'Acertos:' $acertos
echo 'Erros:' $erros
echo 'Media:' $media
