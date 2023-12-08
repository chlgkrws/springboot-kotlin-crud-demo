package com.example.kotlin.member

data class MemberDto(
        var id: String ? = null,
        val name: String,
        val age: Int,
)