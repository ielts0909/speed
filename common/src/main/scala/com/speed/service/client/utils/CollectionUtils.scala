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

  /**
   * is empty of collect
   *
   * @param collection
   * @tparam T
   * @return
   */
  def isEmpty[T](collection: Collection[T]): Boolean = {
    if (null != collection && collection.size() > 0)
      true
    else
      false
  }
}
