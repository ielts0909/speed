package com.speed.service.client.utils

import org.slf4j.LoggerFactory

/**
 *
 * User: gais.ch
 * Date: 15-4-18
 * Time: 下午5:15
 *
 */
object RecordLog {

  lazy val logger = LoggerFactory.getLogger("RECORDLOG")

  def debug(msg: String, throwable: Throwable): Unit = {
    if (logger.isDebugEnabled())
      logger.debug(msg, throwable)
  }

  def error(msg: String, throwable: Throwable): Unit = {
    if (logger.isErrorEnabled)
      logger.error(msg, throwable)
  }

}
