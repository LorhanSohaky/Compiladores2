// Generated from AnalisadorMarkdown/src/antlr/marktex.g4 by ANTLR 4.7.2
package antlr;
import org.antlr.v4.runtime.tree.ParseTreeListener;

/**
 * This interface defines a complete listener for a parse tree produced by
 * {@link marktexParser}.
 */
public interface marktexListener extends ParseTreeListener {
	/**
	 * Enter a parse tree produced by {@link marktexParser#document}.
	 * @param ctx the parse tree
	 */
	void enterDocument(marktexParser.DocumentContext ctx);
	/**
	 * Exit a parse tree produced by {@link marktexParser#document}.
	 * @param ctx the parse tree
	 */
	void exitDocument(marktexParser.DocumentContext ctx);
	/**
	 * Enter a parse tree produced by {@link marktexParser#configs}.
	 * @param ctx the parse tree
	 */
	void enterConfigs(marktexParser.ConfigsContext ctx);
	/**
	 * Exit a parse tree produced by {@link marktexParser#configs}.
	 * @param ctx the parse tree
	 */
	void exitConfigs(marktexParser.ConfigsContext ctx);
	/**
	 * Enter a parse tree produced by {@link marktexParser#type}.
	 * @param ctx the parse tree
	 */
	void enterType(marktexParser.TypeContext ctx);
	/**
	 * Exit a parse tree produced by {@link marktexParser#type}.
	 * @param ctx the parse tree
	 */
	void exitType(marktexParser.TypeContext ctx);
	/**
	 * Enter a parse tree produced by {@link marktexParser#document_type}.
	 * @param ctx the parse tree
	 */
	void enterDocument_type(marktexParser.Document_typeContext ctx);
	/**
	 * Exit a parse tree produced by {@link marktexParser#document_type}.
	 * @param ctx the parse tree
	 */
	void exitDocument_type(marktexParser.Document_typeContext ctx);
	/**
	 * Enter a parse tree produced by {@link marktexParser#title}.
	 * @param ctx the parse tree
	 */
	void enterTitle(marktexParser.TitleContext ctx);
	/**
	 * Exit a parse tree produced by {@link marktexParser#title}.
	 * @param ctx the parse tree
	 */
	void exitTitle(marktexParser.TitleContext ctx);
	/**
	 * Enter a parse tree produced by {@link marktexParser#author}.
	 * @param ctx the parse tree
	 */
	void enterAuthor(marktexParser.AuthorContext ctx);
	/**
	 * Exit a parse tree produced by {@link marktexParser#author}.
	 * @param ctx the parse tree
	 */
	void exitAuthor(marktexParser.AuthorContext ctx);
	/**
	 * Enter a parse tree produced by {@link marktexParser#date}.
	 * @param ctx the parse tree
	 */
	void enterDate(marktexParser.DateContext ctx);
	/**
	 * Exit a parse tree produced by {@link marktexParser#date}.
	 * @param ctx the parse tree
	 */
	void exitDate(marktexParser.DateContext ctx);
	/**
	 * Enter a parse tree produced by {@link marktexParser#header_includes}.
	 * @param ctx the parse tree
	 */
	void enterHeader_includes(marktexParser.Header_includesContext ctx);
	/**
	 * Exit a parse tree produced by {@link marktexParser#header_includes}.
	 * @param ctx the parse tree
	 */
	void exitHeader_includes(marktexParser.Header_includesContext ctx);
	/**
	 * Enter a parse tree produced by {@link marktexParser#content}.
	 * @param ctx the parse tree
	 */
	void enterContent(marktexParser.ContentContext ctx);
	/**
	 * Exit a parse tree produced by {@link marktexParser#content}.
	 * @param ctx the parse tree
	 */
	void exitContent(marktexParser.ContentContext ctx);
	/**
	 * Enter a parse tree produced by {@link marktexParser#references}.
	 * @param ctx the parse tree
	 */
	void enterReferences(marktexParser.ReferencesContext ctx);
	/**
	 * Exit a parse tree produced by {@link marktexParser#references}.
	 * @param ctx the parse tree
	 */
	void exitReferences(marktexParser.ReferencesContext ctx);
	/**
	 * Enter a parse tree produced by {@link marktexParser#reference}.
	 * @param ctx the parse tree
	 */
	void enterReference(marktexParser.ReferenceContext ctx);
	/**
	 * Exit a parse tree produced by {@link marktexParser#reference}.
	 * @param ctx the parse tree
	 */
	void exitReference(marktexParser.ReferenceContext ctx);
}