package com.kaimiya.gaurav.fidoointerviewtask.model

data class Pagination(
    val limit: Int,
    val links: Links,
    val page: Int,
    val pages: Int,
    val total: Int
)