package org.contourgara.examination2gara.infrastructure.exception

class QueryExecutionFailException(val id: Long, val title: String, val author: String, val publisher: String, val price: Int): RuntimeException() {
}
