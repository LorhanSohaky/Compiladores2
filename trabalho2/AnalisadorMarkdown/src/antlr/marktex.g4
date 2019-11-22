grammar marktex;

document: configs content;

configs: '+++++' type title author+ date header_includes? '+++++';
type: 'type' ':' document_type; 
document_type: ('article' | 'monography');
title: 'title' ':' STRING;
author: 'author' ':' STRING;
date: 'date' ':' DATE;
header_includes: 'header-includes' ':' STRING;

content: BODY;

STRING: '"' ~('\n' | '\r' | '"')* '"' ;

DATE: [0-9][0-9] '/' [0-9][0-9] '/' [0-9][0-9][0-9][0-9];

COMENTARIO:	'{{' ~('\n' | '\r' )* '}}' -> skip;
WS	: (' ' | '\t' | '\r' | '\n' )+ -> skip;

BODY: '*****' .+? EOF;