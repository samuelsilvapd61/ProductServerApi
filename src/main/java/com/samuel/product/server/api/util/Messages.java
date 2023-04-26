package com.samuel.product.server.api.util;

import lombok.NoArgsConstructor;
import java.text.MessageFormat;
import java.util.Locale;
import java.util.ResourceBundle;

import static lombok.AccessLevel.*;

@NoArgsConstructor(access = PRIVATE)
public class Messages {

  private static final String BASENAME = "messages";
  
  private static final ResourceBundle resourceBundle =
      ResourceBundle.getBundle(BASENAME, Locale.getDefault());

  public static String get(String key, Object... params) {
    try {
      var message = resourceBundle.getString(key);
      return MessageFormat.format(message, params);
    } catch (Exception e) {
      return key;
    }
  }
  
  public static String getOrNull(String key, Object... params) {
    try {
      var message = resourceBundle.getString(key);
      return MessageFormat.format(message, params);
    } catch (Exception e) {
      return null;
    }
  }
  
}
