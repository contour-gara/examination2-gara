package org.contourgara.examination2gara.domain

/**
 * Book は、本情報のドメインオブジェクトです。
 *
 * @param id 本の ID。
 * @param title 本のタイトル。
 * @param author 本の著者。
 * @param publisher 本の出版社。
 * @param price 本の値段。
 */
data class Book(val id: Int, val title: String, val author: String, val publisher: String, val price: Int)
