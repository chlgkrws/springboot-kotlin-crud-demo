package com.example.kotlin.member

import org.springframework.stereotype.Service

@Service
class MemberService(private val memberRepository: MemberRepository) {

    fun findAll(): List<MemberDto>? {
        val members = this.memberRepository.findAll()

        return members.map { member ->
            MemberDto(
                id = member.id,
                name = member.name,
                age = member.age
            )
        }
    }

    fun addMember(member: MemberDto): String? {
        val memberDocument = MemberDocument(
            name = member.name,
            age = member.age
        )

        val savedMember = this.memberRepository.save(memberDocument)

        return savedMember.id
    }

    fun updateMember(member: MemberDto): MemberDto {
        val memberDocument = member.id?.let {
            this.memberRepository.findById(it)
                .orElseThrow { NoSuchElementException("not found member ${member.id}") }
        } ?: return member

        memberDocument.name = member.name
        memberDocument.age = member.age

        val updatedMember = this.memberRepository.save(memberDocument)

        return MemberDto(
            id = updatedMember.id,
            name = updatedMember.name,
            age = updatedMember.age
        )
    }

    fun deleteMember(id: String) {
        this.memberRepository.deleteById(id)
    }
}