// Generated from AnalisadorLA/src/la/linguagem/ANTLR/la.g4 by ANTLR 4.7.2
package la.linguagem.ANTLR;
import org.antlr.v4.runtime.tree.ParseTreeVisitor;

/**
 * This interface defines a complete generic visitor for a parse tree produced
 * by {@link laParser}.
 *
 * @param <T> The return type of the visit operation. Use {@link Void} for
 * operations with no return type.
 */
public interface laVisitor<T> extends ParseTreeVisitor<T> {
	/**
	 * Visit a parse tree produced by {@link laParser#programa}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitPrograma(laParser.ProgramaContext ctx);
	/**
	 * Visit a parse tree produced by {@link laParser#declaracoes}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitDeclaracoes(laParser.DeclaracoesContext ctx);
	/**
	 * Visit a parse tree produced by {@link laParser#declaracao_local_ou_global}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitDeclaracao_local_ou_global(laParser.Declaracao_local_ou_globalContext ctx);
	/**
	 * Visit a parse tree produced by the {@code declaracaoLocalVariavel}
	 * labeled alternative in {@link laParser#declaracao_local}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitDeclaracaoLocalVariavel(laParser.DeclaracaoLocalVariavelContext ctx);
	/**
	 * Visit a parse tree produced by the {@code declaracaoLocalConstante}
	 * labeled alternative in {@link laParser#declaracao_local}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitDeclaracaoLocalConstante(laParser.DeclaracaoLocalConstanteContext ctx);
	/**
	 * Visit a parse tree produced by the {@code declaracaoLocalTipo}
	 * labeled alternative in {@link laParser#declaracao_local}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitDeclaracaoLocalTipo(laParser.DeclaracaoLocalTipoContext ctx);
	/**
	 * Visit a parse tree produced by {@link laParser#variavel}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitVariavel(laParser.VariavelContext ctx);
	/**
	 * Visit a parse tree produced by {@link laParser#identificador}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitIdentificador(laParser.IdentificadorContext ctx);
	/**
	 * Visit a parse tree produced by {@link laParser#dimensao}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitDimensao(laParser.DimensaoContext ctx);
	/**
	 * Visit a parse tree produced by {@link laParser#tipo}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitTipo(laParser.TipoContext ctx);
	/**
	 * Visit a parse tree produced by {@link laParser#tipo_basico}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitTipo_basico(laParser.Tipo_basicoContext ctx);
	/**
	 * Visit a parse tree produced by {@link laParser#tipo_basico_identificador}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitTipo_basico_identificador(laParser.Tipo_basico_identificadorContext ctx);
	/**
	 * Visit a parse tree produced by {@link laParser#tipo_estendido}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitTipo_estendido(laParser.Tipo_estendidoContext ctx);
	/**
	 * Visit a parse tree produced by {@link laParser#valor_constante}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitValor_constante(laParser.Valor_constanteContext ctx);
	/**
	 * Visit a parse tree produced by {@link laParser#registro}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitRegistro(laParser.RegistroContext ctx);
	/**
	 * Visit a parse tree produced by the {@code declaracao_global_procedimento}
	 * labeled alternative in {@link laParser#declaracao_global}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitDeclaracao_global_procedimento(laParser.Declaracao_global_procedimentoContext ctx);
	/**
	 * Visit a parse tree produced by the {@code declaracao_global_funcao}
	 * labeled alternative in {@link laParser#declaracao_global}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitDeclaracao_global_funcao(laParser.Declaracao_global_funcaoContext ctx);
	/**
	 * Visit a parse tree produced by {@link laParser#parametro}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitParametro(laParser.ParametroContext ctx);
	/**
	 * Visit a parse tree produced by {@link laParser#parametros}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitParametros(laParser.ParametrosContext ctx);
	/**
	 * Visit a parse tree produced by {@link laParser#corpo}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitCorpo(laParser.CorpoContext ctx);
	/**
	 * Visit a parse tree produced by {@link laParser#cmd}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitCmd(laParser.CmdContext ctx);
	/**
	 * Visit a parse tree produced by {@link laParser#cmdLeia}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitCmdLeia(laParser.CmdLeiaContext ctx);
	/**
	 * Visit a parse tree produced by {@link laParser#cmdEscreva}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitCmdEscreva(laParser.CmdEscrevaContext ctx);
	/**
	 * Visit a parse tree produced by {@link laParser#cmdSe}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitCmdSe(laParser.CmdSeContext ctx);
	/**
	 * Visit a parse tree produced by {@link laParser#cmdCaso}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitCmdCaso(laParser.CmdCasoContext ctx);
	/**
	 * Visit a parse tree produced by {@link laParser#cmdPara}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitCmdPara(laParser.CmdParaContext ctx);
	/**
	 * Visit a parse tree produced by {@link laParser#cmdEnquanto}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitCmdEnquanto(laParser.CmdEnquantoContext ctx);
	/**
	 * Visit a parse tree produced by {@link laParser#cmdFaca}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitCmdFaca(laParser.CmdFacaContext ctx);
	/**
	 * Visit a parse tree produced by {@link laParser#cmdAtribuicao}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitCmdAtribuicao(laParser.CmdAtribuicaoContext ctx);
	/**
	 * Visit a parse tree produced by {@link laParser#cmdChamada}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitCmdChamada(laParser.CmdChamadaContext ctx);
	/**
	 * Visit a parse tree produced by {@link laParser#cmdRetorne}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitCmdRetorne(laParser.CmdRetorneContext ctx);
	/**
	 * Visit a parse tree produced by {@link laParser#selecao}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitSelecao(laParser.SelecaoContext ctx);
	/**
	 * Visit a parse tree produced by {@link laParser#item_selecao}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitItem_selecao(laParser.Item_selecaoContext ctx);
	/**
	 * Visit a parse tree produced by {@link laParser#constantes}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitConstantes(laParser.ConstantesContext ctx);
	/**
	 * Visit a parse tree produced by {@link laParser#numero_intervalo}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitNumero_intervalo(laParser.Numero_intervaloContext ctx);
	/**
	 * Visit a parse tree produced by {@link laParser#operador_unario}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitOperador_unario(laParser.Operador_unarioContext ctx);
	/**
	 * Visit a parse tree produced by {@link laParser#expressao_aritmetica}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitExpressao_aritmetica(laParser.Expressao_aritmeticaContext ctx);
	/**
	 * Visit a parse tree produced by {@link laParser#termo}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitTermo(laParser.TermoContext ctx);
	/**
	 * Visit a parse tree produced by {@link laParser#fator}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitFator(laParser.FatorContext ctx);
	/**
	 * Visit a parse tree produced by {@link laParser#operador_nivel_1}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitOperador_nivel_1(laParser.Operador_nivel_1Context ctx);
	/**
	 * Visit a parse tree produced by {@link laParser#operador_nivel_2}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitOperador_nivel_2(laParser.Operador_nivel_2Context ctx);
	/**
	 * Visit a parse tree produced by {@link laParser#operador_nivel_3}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitOperador_nivel_3(laParser.Operador_nivel_3Context ctx);
	/**
	 * Visit a parse tree produced by {@link laParser#parcela}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitParcela(laParser.ParcelaContext ctx);
	/**
	 * Visit a parse tree produced by {@link laParser#parcela_unaria}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitParcela_unaria(laParser.Parcela_unariaContext ctx);
	/**
	 * Visit a parse tree produced by {@link laParser#parcela_nao_unaria}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitParcela_nao_unaria(laParser.Parcela_nao_unariaContext ctx);
	/**
	 * Visit a parse tree produced by {@link laParser#expressao_relacional}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitExpressao_relacional(laParser.Expressao_relacionalContext ctx);
	/**
	 * Visit a parse tree produced by {@link laParser#operador_relacional}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitOperador_relacional(laParser.Operador_relacionalContext ctx);
	/**
	 * Visit a parse tree produced by {@link laParser#expressao}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitExpressao(laParser.ExpressaoContext ctx);
	/**
	 * Visit a parse tree produced by {@link laParser#termo_logico}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitTermo_logico(laParser.Termo_logicoContext ctx);
	/**
	 * Visit a parse tree produced by {@link laParser#fator_logico}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitFator_logico(laParser.Fator_logicoContext ctx);
	/**
	 * Visit a parse tree produced by {@link laParser#parcela_logica}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitParcela_logica(laParser.Parcela_logicaContext ctx);
	/**
	 * Visit a parse tree produced by {@link laParser#operador_logico_nivel_1}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitOperador_logico_nivel_1(laParser.Operador_logico_nivel_1Context ctx);
	/**
	 * Visit a parse tree produced by {@link laParser#operador_logico_nivel_2}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitOperador_logico_nivel_2(laParser.Operador_logico_nivel_2Context ctx);
}