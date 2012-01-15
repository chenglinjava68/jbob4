
package com.bob.sql.xml.dynamic;

import ognl.OgnlException;

import com.bob.sql.xml.parsing.BuilderException;
import com.bob.sql.xml.parsing.GenericTokenParser;
import com.bob.sql.xml.parsing.TokenHandler;
import com.bob.sql.xml.type.SimpleTypeRegistry;

public class TextSqlNode implements SqlNode {
	private String text;

	public TextSqlNode(String text) {
		this.text = text;
	}

	public boolean apply(DynamicContext context) {
		GenericTokenParser parser = new GenericTokenParser("${", "}", new BindingTokenParser(context));
		context.appendSql(parser.parse(text));
		return true;
	}

	private static class BindingTokenParser implements TokenHandler {

		private DynamicContext context;

		public BindingTokenParser(DynamicContext context) {
			this.context = context;
		}

		public String handleToken(String content) {
			try {
				Object parameter = context.getBindings().get("_parameter");
				if (parameter == null) {
					context.getBindings().put("value", null);
				} else if (SimpleTypeRegistry.isSimpleType(parameter.getClass())) {
					context.getBindings().put("value", parameter);
				}
				Object value = OgnlCache.getValue(content, context.getBindings());
				return String.valueOf(value);
			} catch (OgnlException e) {
				throw new BuilderException("Error evaluating expression '" + content + "'. Cause: " + e, e);
			}
		}
	}

}