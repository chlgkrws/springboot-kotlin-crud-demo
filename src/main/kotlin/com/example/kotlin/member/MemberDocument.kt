package com.example.kotlin.member

import org.springframework.data.annotation.Id
import org.springframework.data.mongodb.core.mapping.Document

@Document(collection = "members")
data class MemberDocument(
    @Id val id: String? = null,
    var name: String,
    var age: Int
)