// Generated from AnalisadorMarkdown/src/antlr/marktex.g4 by ANTLR 4.7.2
package antlr;
import org.antlr.v4.runtime.tree.ParseTreeVisitor;

/**
 * This interface defines a complete generic visitor for a parse tree produced
 * by {@link marktexParser}.
 *
 * @param <T> The return type of the visit operation. Use {@link Void} for
 * operations with no return type.
 */
public interface marktexVisitor<T> extends ParseTreeVisitor<T> {
	/**
	 * Visit a parse tree produced by {@link marktexParser#document}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitDocument(marktexParser.DocumentContext ctx);
	/**
	 * Visit a parse tree produced by {@link marktexParser#configs}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitConfigs(marktexParser.ConfigsContext ctx);
	/**
	 * Visit a parse tree produced by {@link marktexParser#type}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitType(marktexParser.TypeContext ctx);
	/**
	 * Visit a parse tree produced by {@link marktexParser#title}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitTitle(marktexParser.TitleContext ctx);
	/**
	 * Visit a parse tree produced by {@link marktexParser#author}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitAuthor(marktexParser.AuthorContext ctx);
	/**
	 * Visit a parse tree produced by {@link marktexParser#date}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitDate(marktexParser.DateContext ctx);
	/**
	 * Visit a parse tree produced by {@link marktexParser#header_includes}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitHeader_includes(marktexParser.Header_includesContext ctx);
	/**
	 * Visit a parse tree produced by {@link marktexParser#content}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitContent(marktexParser.ContentContext ctx);
}