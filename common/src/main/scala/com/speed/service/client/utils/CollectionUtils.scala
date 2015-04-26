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

  def isEmpty[T](collection: Collection[T]): Boolean = {
    if (null != collection && collection.size() > 0)
      true
    else
      false
  }

  def asArray[T](collection: Collection[T]): Array[T] = {
    if (isEmpty(collection)) Nil
    val total: Int = collection.size()
    val array = new Array[T](total)
    var i = 0
    for (t <- collection) {
      array(i) = t
      i = i + 1
    }
    array
  }
}
