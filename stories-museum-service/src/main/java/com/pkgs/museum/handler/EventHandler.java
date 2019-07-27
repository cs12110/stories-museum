package com.pkgs.museum.handler;

import java.util.Map;

/**
 * <p>
 *
 * @author cs12110 create at 2019-07-27 00:13
 * <p>
 * @since 1.0.0
 */
public interface EventHandler {

    Object dealWith(Map<String, String> eventMap);
}
