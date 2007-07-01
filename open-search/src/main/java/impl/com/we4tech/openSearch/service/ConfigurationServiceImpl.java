/* $Id$ */
/*
 ******************************************************************************
 *   Copyright (C) 2006 nhm tanveer hossain khan (hasan) 
 *
 *   This library is free software; you can redistribute it and/or
 *   modify it under the terms of the GNU Lesser General Public
 *   License as published by the Free Software Foundation; either
 *   version 2.1 of the License, or (at your option) any later version.
 *
 *   This library is distributed in the hope that it will be useful,
 *   but WITHOUT ANY WARRANTY; without even the implied warranty of
 *   MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the GNU
 *   Lesser General Public License for more details.
 *
 *   You should have received a copy of the GNU Lesser General Public
 *   License along with this library; if not, write to the Free Software
 *   Foundation, Inc., 59 Temple Place, Suite 330, Boston, MA  02111-1307  USA
 ******************************************************************************
 * $LastChangedBy$
 * $LastChangedDate$
 * $LastChangedRevision$
 ******************************************************************************
*/
package impl.com.we4tech.openSearch.service;

import java.io.InputStream;
import java.io.IOException;
import java.util.*;

import com.we4tech.openSearch.service.AbstractConfigurationService;
import com.we4tech.openSearch.object.Configuration;
import com.we4tech.openSearch.object.OpenSearchIconImage;
import com.we4tech.openSearch.object.OpenSearchQuery;
import com.we4tech.openSearch.object.OpenSearchUrl;

/**
 * Implementation of {@code AbstractConfigurationService}.
 * @author <a href="mailto:hasan@somewherein.net">nhm tanveer hossain khan (hasan)</a>
 */
public class ConfigurationServiceImpl extends AbstractConfigurationService {

  private static final String PROPERTY_CONFIGURATION_FILE = "opensearchConfigFile";
  private static final String DEFAULT_CONFIGURATION_FILE =
                              "open-search.properties";

  private static final String PROPERTY_SHORT_NAME = "shortName";
  private static final String PROPERTY_DESCRIPTION = "description";
  private static final String PROPERTY_URL_PREFIX = "url.";
  private static final String PROPERTY_CONTACT = "contact";
  private static final String PROPERTY_TAGS = "tags";
  private static final String PROPERTY_LONG_NAME = "longName";
  private static final String PROPERTY_IMAGE_PREFIX = "image.";
  private static final String PROPERTY_VALUE_SEPARATOR = ",";
  private static final String PROPERTY_QUERY = "query.";
  private static final String PROPERTY_DEVELOPER = "developer";
  private static final String PROPERTY_ATTRIBUTION = "attribution";
  private static final String PROPERTY_SYNDICATION_RIGHT = "syndicationRight";
  private static final String PROPERTY_ADULT_CONTENT = "adultContent";
  private static final String PROPERTY_LANGUAGE = "language";
  private static final String PROPERTY_INPUT_ENCODING = "inputEncoding";
  private static final String PROPERTY_OUTPUT_ENCODING = "outputEncoding";

  private final String mConfigurationFile;
  private final Map<String, Configuration> mConfigurationMap =
                                     new HashMap<String, Configuration>();

  /**
   * Default constructor, if {@code -DopensearchConfigFile} property is defined
   * during executing the application. it will take this system property.<br>
   * otherwise it will use {@code open-search.properties} file.
   */
  public ConfigurationServiceImpl() {
    String configFileLocation = System.getProperty(PROPERTY_CONFIGURATION_FILE);
    if (configFileLocation != null) {
      mConfigurationFile = configFileLocation;
    } else {
      mConfigurationFile = DEFAULT_CONFIGURATION_FILE;
    }
    LOG.info("Configuration file - " + mConfigurationFile);
  }

  /**
   * Retrieve configuration from user defined properties.
   *
   * @return {@code Configuration} object instance is returned.
   * @param pOpenSearchConfigurationPath {@inheritDoc}
   */
  @Override
  public Configuration getConfiguration(
         final String pOpenSearchConfigurationPath) {
    LOG.debug("Return configuration from - " + pOpenSearchConfigurationPath);
    Configuration configuration =
          mConfigurationMap.get(pOpenSearchConfigurationPath);

    if (configuration == null) {
      // To prevent non intentional invalidate data retrieval problem.
      synchronized (this) {
        try {
          configuration = loadConfigurationFromFile(pOpenSearchConfigurationPath);
          mConfigurationMap.put(pOpenSearchConfigurationPath, configuration);
        } catch (IOException e) {
          throw new RuntimeException("Configuration file not found.", e);
        }
      }
    }
    return configuration;
  }

  /**
   * Lookup {@code mConfigurationFile} into class loader.
   * if found set all properties to {@code mConfigurationMap} object.<br>
   * otherwise throw a {@code RuntimeException}
   */
  private Configuration loadConfigurationFromFile(
          final String pOpenSearchConfigurationPath) throws IOException {
    if (LOG.isDebugEnabled()) {
      LOG.debug("Load configuration from file - " +
                pOpenSearchConfigurationPath);
    }
    try {
      // Look up for file input stream from class loader.
      InputStream configurationFileInputStream = getClass().getClassLoader().
              getResourceAsStream(pOpenSearchConfigurationPath);

      if (configurationFileInputStream == null) {
        configurationFileInputStream =
            ClassLoader.getSystemResourceAsStream(mConfigurationFile);
        if (configurationFileInputStream == null) {
          throw new RuntimeException("Required " + mConfigurationFile +
                                     " file is missing from class loader.");
        }
      }

      // Load properties
      final Properties properties = new Properties();
      properties.load(configurationFileInputStream);

      // Set value from properties.
      return populateConfigurationObjectFromProperties(properties);
    } catch (Exception e) {
      throw new RuntimeException("Configuration service was looking for '"+
                                pOpenSearchConfigurationPath+"', " +
                                "but it seems this configuration file " +
                                "doesn't exist in class path or you didn't " +
                                "set your request uri and resource mapping " +
                                "over web.xml file.");
    }
  }

  /**
   * Find every property and set in the configuration object.
   */
  private Configuration populateConfigurationObjectFromProperties(final Properties pProperties) {
    if (LOG.isDebugEnabled()) {
      LOG.debug("Populate Configuration from - " + pProperties);
    }

    // Create a new instance of Configuration class.
    final Configuration configuration = new Configuration();
    
    final List<OpenSearchUrl> urls = new ArrayList<OpenSearchUrl>();
    final List<OpenSearchIconImage> images =
                                    new ArrayList<OpenSearchIconImage>();

    // Iterate for each property to populate configuration object.
    final boolean debugEnabled = LOG.isDebugEnabled();
    for (final Map.Entry<Object, Object> entry : pProperties.entrySet()) {
      final String key = (String) entry.getKey();
      final String value = (String) entry.getValue();

      if (debugEnabled) {
        LOG.debug("Key - " + key + ", value - " + value);
      }

      if (key.equals(PROPERTY_SHORT_NAME)) {
        configuration.setShortName(value);
      } else if (key.equals(PROPERTY_DESCRIPTION)) {
        configuration.setDescription(value);
      } else if (key.startsWith(PROPERTY_URL_PREFIX)) {
        urls.add(openSearchUrlFrom(value));
      } else if (key.equals(PROPERTY_CONTACT)) {
        configuration.setContact(value);
      } else if (key.equals(PROPERTY_TAGS)) {
        configuration.setTags(value);
      } else if (key.equals(PROPERTY_LONG_NAME)) {
        configuration.setLongName(value);
      } else if (key.startsWith(PROPERTY_IMAGE_PREFIX)) {
        final OpenSearchIconImage image = openSearchIconImageFrom(value);
        images.add(image);
      } else if (key.startsWith(PROPERTY_QUERY)) {
        configuration.setQuery(openSearchQueryFrom(value));
      } else if (key.equals(PROPERTY_DEVELOPER)) {
        configuration.setDeveloper(value);
      } else if (key.equals(PROPERTY_ATTRIBUTION)) {
        configuration.setAttribution(value);
      } else if (key.equals(PROPERTY_SYNDICATION_RIGHT)) {
        configuration.setSyndicationRight(value);
      } else if (key.equals(PROPERTY_ADULT_CONTENT)) {
        configuration.setAdultContent(Boolean.valueOf(value));
      } else if (key.equals(PROPERTY_LANGUAGE)) {
        configuration.setLanguage(value);
      } else if (key.equals(PROPERTY_INPUT_ENCODING)) {
        configuration.setInputEncoding(value);
      } else if (key.equals(PROPERTY_OUTPUT_ENCODING)) {
        configuration.setOutputEncoding(value);
      } else {
        LOG.warn("Invalid property - " + key + ", value - " + value);
      }
    }

    // set list objects
    configuration.setUrls(urls);
    configuration.setImages(images);

    return configuration;
  }

  private OpenSearchUrl openSearchUrlFrom(final String pValue) {
    assert pValue != null;

    final OpenSearchUrl url = new OpenSearchUrl();
    final String[] split = pValue.split(PROPERTY_VALUE_SEPARATOR);
    url.setType(split[0].trim());
    url.setMethod(split[1].trim());
    url.setTemplate(split[2].trim());

    if (split.length > 3) {
      url.setIndexOffset(Integer.valueOf(split[2].trim()));
      if (split.length > 4) {
        url.setPageOffset(Integer.valueOf(split[3].trim()));
      }
    }

    return url;
  }

  private OpenSearchQuery openSearchQueryFrom(final String pValue) {
    assert pValue != null;

    String[] split = pValue.split(PROPERTY_VALUE_SEPARATOR);

    final OpenSearchQuery query = new OpenSearchQuery();
    query.setRole(split[0].trim());
    query.setSearchTerms(split[1].trim());
    if (split.length > 2) {
      query.setTitle(split[2].trim());
    }

    return query;
  }

  private OpenSearchIconImage openSearchIconImageFrom(final String pValue) {
    assert pValue != null;

    final String[] split = pValue.split(PROPERTY_VALUE_SEPARATOR);
    final OpenSearchIconImage image = new OpenSearchIconImage();
    image.setHeight(Integer.valueOf(split[0].trim()));
    image.setWidth(Integer.valueOf(split[1].trim()));
    image.setMimeType(split[2].trim());
    image.setUrl(split[3].trim());

    return image;
  }

  /**
   * Store {@code Configuration} into the storage.
   *
   * @param pConfiguration configuration.
   */
  @Override
  public synchronized void storeConfiguration(
         final Configuration pConfiguration) {
    
  }
}
