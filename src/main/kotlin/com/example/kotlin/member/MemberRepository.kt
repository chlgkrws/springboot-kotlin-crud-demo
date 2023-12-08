package com.example.kotlin.member

import org.springframework.data.mongodb.repository.MongoRepository

interface MemberRepository : MongoRepository<MemberDocument, String> {

}