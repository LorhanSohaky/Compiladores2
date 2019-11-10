grammar marktex;

document: configs content;

configs: '+++++' type title author date header_includes '+++++\n';
type: 'type' ':' document_type; 
document_type: ('article' | 'monography');
title: 'title' ':' TITLE;
author: 'author' ':' AUTHORS;
date: 'date' ':' DATE;
header_includes: 'header-includes' ':' BODY;

content: BODY;

TITLE: ~('\n' | '\r' | '\'')* '\n';
AUTHOR: ([a-zA-Z]) ([a-zA-Z] | ' ')*;
AUTHORS: AUTHOR (';' AUTHOR)* '\n';
DATE: ([a-zA-Z] | [0-9] | ' ' | '/')* '\n';

COMENTARIO:	'{' ~('\n' | '\r' )* '}' {skip();};
WS	: (' ' | '\t' | '\r' | '\n' )+ {skip();};

BODY: .+?;