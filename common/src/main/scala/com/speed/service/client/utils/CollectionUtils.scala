package com.speed.service.client.utils

import java.util.Collection

/**
 *
 * User: gais.ch
 * Date: 15-4-23
 * Time: 下午10:02
 *
 */
object CollectionUtils {

  def isEmpty(collection: Collection): Boolean = {
    if (null != collection && collection.size() > 0)
      true
    else
      false
  }

}
