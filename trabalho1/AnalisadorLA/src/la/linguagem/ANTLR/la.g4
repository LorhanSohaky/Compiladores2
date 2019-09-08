grammar la;

@lexer::members {
   public static String grupo="740951 587087 586730 619884";

   void erroLexico(String mensagem) {
      throw new ParseCancellationException(mensagem);
   }
}

programa: declaracoes 'algoritmo' corpo 'fim_algoritmo';
declaracoes: declaracao_local_ou_global*;
declaracao_local_ou_global: declaracao_local | declaracao_global;
declaracao_local: 'declare' variavel #declaracaoLocalVariavel
				| 'constante' IDENT ':' tipo_basico '=' valor_constante #declaracaoLocalConstante
				| 'tipo'  IDENT ':' tipo #declaracaoLocalTipo;
variavel: identificadores+=identificador (',' identificadores+=identificador)* ':' tipo;
identificador: identificador1=IDENT ('.' IDENT)* dimensao;
dimensao:('['expressao_aritmetica']')*;
tipo: registro | tipo_estendido;
tipo_basico: 'literal' | 'inteiro' | 'real' | 'logico';
tipo_basico_identificador: tipo_basico | IDENT;
tipo_estendido: '^' ? tipo_basico_identificador;
valor_constante: CADEIA | NUM_INT | NUM_REAL | 'verdadeiro' | 'falso';
registro: 'registro' variavel* 'fim_registro';
declaracao_global: 'procedimento' IDENT '(' parametros ? ')' declaracao_local* cmd*
				   'fim_procedimento' # declaracao_global_procedimento
				 | 'funcao' IDENT '(' parametros ? ')' ':' tipo_estendido declaracao_local*
				   cmd* 'fim_funcao' # declaracao_global_funcao	;
parametro: 'var' ? identificador (',' identificador)* ':' tipo_estendido;
parametros: parametro (',' parametro)*;
corpo: declaracao_local* cmd*;
cmd: cmdLeia | cmdEscreva | cmdSe | cmdCaso | cmdPara | cmdEnquanto
   | cmdFaca | cmdAtribuicao | cmdChamada | cmdRetorne;
cmdLeia: 'leia' '(' '^' ? identificadores+=identificador (',' '^' ? identificadores+=identificador)* ')';
cmdEscreva: 'escreva' '(' expressao (',' expressao)* ')';
cmdSe: 'se' expressao 'entao' cmd_se+=cmd* (operador_se='senao' cmd_senao+=cmd*)? 'fim_se';
cmdCaso: 'caso' expressao_aritmetica 'seja' selecao ('senao' cmd*)? 'fim_caso';
cmdPara: 'para' IDENT '<-' expressao_aritmetica 'ate' expressao_aritmetica 'faca' cmd* 'fim_para';
cmdEnquanto: 'enquanto' expressao 'faca' cmd* 'fim_enquanto';
cmdFaca: 'faca' cmd* 'ate' expressao;
cmdAtribuicao: '^' ? identificador '<-' expressao;
cmdChamada: IDENT '(' expressao (',' expressao)* ')';
cmdRetorne: 'retorne' expressao;
selecao: item_selecao*;
item_selecao: constantes ':' cmd*;
constantes: numero_intervalo (',' numero_intervalo)*;
numero_intervalo: operador_unario ? NUM_INT ('..' operador_unario ? NUM_INT) ?;
operador_unario: '-';
expressao_aritmetica: termo (operador_nivel_1 termo)*;
termo: fator (operador_nivel_2 fator)*;
fator: parcela (operador_nivel_3 parcela)*;
operador_nivel_1: '+' | '-';
operador_nivel_2: '*' | '/';
operador_nivel_3: '%';
parcela: operador_unario ? parcela_unaria | parcela_nao_unaria;
parcela_unaria: '^' ? identificador
			  | IDENT '(' expressao (',' expressao)* ')'
			  | NUM_INT
			  | NUM_REAL
			  | '(' expressao ')';
parcela_nao_unaria: '&' identificador | CADEIA;
expressao_relacional: expressao_aritmetica (operador_relacional expressao_aritmetica)?;
operador_relacional: '=' | '<>' | '>=' | '<=' | '>' | '<';
expressao: termo_logico (operador_logico_nivel_1 termo_logico)*;
termo_logico: fator_logico (operador_logico_nivel_2 fator_logico)*;
fator_logico: 'nao' ? parcela_logica;
parcela_logica: ('verdadeiro' | 'falso')
				| expressao_relacional;
operador_logico_nivel_1: 'ou';
operador_logico_nivel_2: 'e';

IDENT: ('a'..'z' | 'A'..'Z' | '_') ('a'..'z' | 'A'..'Z' | '0'..'9' | '_')*;
CADEIA: '"' ~('\n' | '\r' | '\'')* '\'' | '"' ~('\n' | '\r' | '"')* '"';
NUM_INT: ('0'..'9')+;
NUM_REAL: NUM_INT '.' ('0'..'9')+;

COMENTARIO:	'{' ~('\n' | '\r' )* '}' {skip();};
WS	: (' ' | '\t' | '\r' | '\n') {skip();};

COMENTARIO_NAO_FECHADO: '{' ~('}'|'\n'|'\r')* '\n' { { erroLexico("Linha "+getLine()+": comentario nao fechado"); }; };
ERRO: . { erroLexico("Linha "+getLine()+": "+getText()+" - simbolo nao identificado"); };