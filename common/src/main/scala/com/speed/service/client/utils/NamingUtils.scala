package com.speed.service.client.utils

/**
 *
 * User: gais.ch
 * Date: 15-4-30
 * Time: 下午1:45
 *
 */
object NamingUtils {

  /**
   * unique service name
   * @param name
   * @param version
   * @return
   */
  def uniqueServiceName(name: String, version: String): String = {
    name + "_" + version
  }

}
