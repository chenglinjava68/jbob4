
package com.bob.sql.xml.builder;

import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

import org.xml.sax.EntityResolver;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

import com.bob.sql.xml.io.Resources;

/*
 * Offline entity resolver for the iBATIS DTDs
 */
public class XMLMapperEntityResolver implements EntityResolver {

  private static final Map<String, String> doctypeMap = new HashMap<String, String>();

  private static final String MYSQL_CONFIG_DOCTYPE = "-//mysql.org//DTD Config//EN".toUpperCase(Locale.ENGLISH);
  private static final String MYSQL_CONFIG_URL = "http://mysql.org/dtd/mysql-config.dtd".toUpperCase(Locale.ENGLISH);

  private static final String MYSQL_MAPPER_DOCTYPE = "-//mysql.org//DTD Mapper//EN".toUpperCase(Locale.ENGLISH);
  private static final String MYSQL_MAPPER_URL = "http://mysql.org/dtd/mysql-mapper.dtd".toUpperCase(Locale.ENGLISH);

  private static final String MYSQL_CONFIG_DTD = "com/bob/xml/sql/builder/mysql-config.dtd";
  private static final String MYSQL_MAPPER_DTD = "com/bob/xml/sql/builder/mysql-mapper.dtd";

  static {

    doctypeMap.put(MYSQL_CONFIG_URL, MYSQL_CONFIG_DTD);
    doctypeMap.put(MYSQL_CONFIG_DOCTYPE, MYSQL_CONFIG_DTD);

    doctypeMap.put(MYSQL_MAPPER_URL, MYSQL_MAPPER_DTD);
    doctypeMap.put(MYSQL_MAPPER_DOCTYPE, MYSQL_MAPPER_DTD);
  }

  /*
   * Converts a public DTD into a local one
   *
   * @param publicId Unused but required by EntityResolver interface
   * @param systemId The DTD that is being requested
   * @return The InputSource for the DTD
   * @throws org.xml.sax.SAXException If anything goes wrong
   */
  public InputSource resolveEntity(String publicId, String systemId)
      throws SAXException {

    if (publicId != null) publicId = publicId.toUpperCase(Locale.ENGLISH);
    if (systemId != null) systemId = systemId.toUpperCase(Locale.ENGLISH);

    InputSource source = null;
    try {
      String path = doctypeMap.get(publicId);
      source = getInputSource(path, source);
      if (source == null) {
        path = doctypeMap.get(systemId);
        source = getInputSource(path, source);
      }
    } catch (Exception e) {
      throw new SAXException(e.toString());
    }
    return source;
  }

  private InputSource getInputSource(String path, InputSource source) {
    if (path != null) {
      InputStream in;
      try {
        in = Resources.getResourceAsStream(path);
        source = new InputSource(in);
      } catch (IOException e) {
        // ignore, null is ok
      }
    }
    return source;
  }

}