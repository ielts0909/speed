package com.speed.service.client.utils

/**
 *
 * User: gais.ch
 * Date: 15-4-24
 * Time: 上午9:06
 *
 */
object StringUtils {

  def isBlank(str: String): Boolean = {
    if (null == str || "".equals(str))
      true
    else
      false
  }

}
