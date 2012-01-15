
package com.bob.sql.xml.dynamic;

import java.io.StringReader;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import ognl.ExpressionSyntaxException;
import ognl.Node;
import ognl.Ognl;
import ognl.OgnlException;
import ognl.OgnlParser;
import ognl.ParseException;
import ognl.TokenMgrError;

/**
 * 
 * Caches OGNL parsed expressions.
 * Have a look at http://code.google.com/p/mybatis/issues/detail?id=342
 *
 */
public class OgnlCache {

  private static final Map<String, ognl.Node> expressionCache = new ConcurrentHashMap<String, ognl.Node>();

  public static Object getValue(String expression, Object root) throws OgnlException {
    return Ognl.getValue(parseExpression(expression), root);
  }

  private static Object parseExpression(String expression) throws OgnlException {
    try {
      Node node = expressionCache.get(expression);
      if (node == null) {
        node = new OgnlParser(new StringReader(expression)).topLevelExpression();
        expressionCache.put(expression, node);
      }
      return node;
    } catch (ParseException e) {
      throw new ExpressionSyntaxException(expression, e);
    } catch (TokenMgrError e) {
      throw new ExpressionSyntaxException(expression, e);
    }
  }

}
