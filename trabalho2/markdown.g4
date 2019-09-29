heading : heading_level_M1 WS phrase | phrase BREAK_LINE  heading_level_M2;
heading_level_M1 : heading_level1_M1 | heading_level2_M1 | heading_level3_M1 | heading_level4_M1 | heading_level5_M1 | heading_level6_M1;
heading_level_M2 : heading_level1_M2 | heading_level2_M2;

heading_level1_M1 : '#';
heading_level2_M1 : '##';
heading_level3_M1 : '###';
heading_level4_M1 : '####';
heading_level5_M1 : '#####';
heading_level6_M1 : '######';

heading_level1_M2 : '='+;
heading_level2_M2 : '-'+;

WS	: (' ' | '\t' | '\r' | '\n') {skip();};
BREAK_LINE : return (COMO EXPLICITAR A TECLA RETURN?);

paragraph : text BREAK_LINE text;
new_line : '  ' BREAK_LINE;
emphasis : bold | italic | bold_italic;

text : 
phrase :

bold : '**' ~'*' text ~'*' '**' | '__' ~'_' text ~'_' '__' ;
italic : '*' ~'*' text ~'*' '*' | '_' ~'_' text ~'_'  '_' ;
bold_italic : '***' text '***' | '___' text '____' ;