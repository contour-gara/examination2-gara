package org.contourgara.examination2gara

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

/**
 * Examination2GaraApplication は、本情報を提供する API です。
 */
@SpringBootApplication
class Examination2GaraApplication

/**
 * このメソッドを実行し、アプリケーションを起動します。
 *
 * @param args コマンドライン引数。
 */
fun main(args: Array<String>) {
  runApplication<Examination2GaraApplication>(*args)
}
