package com.example.kotlin.member

import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PatchMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/members")
class MemberRestController(private val memberService: MemberService) {

    /**
     * 멤버 리스트 조회
     */
    @GetMapping
    fun list(): ResponseEntity<List<MemberDto>> {
        val members = this.memberService.findAll()

        return ResponseEntity.ok(members)
    }

    /**
     * 멤버 추가하기
     */
    @PostMapping
    fun save(@RequestBody member: MemberDto): ResponseEntity<String> {
        val memberId = this.memberService.addMember(member)

        return ResponseEntity.status(201).body(memberId)
    }

    /**
     * 멤버 수정하기
     */
    @PatchMapping("/{id}")
    fun update(@RequestBody member: MemberDto, @PathVariable id: String): ResponseEntity<MemberDto> {
        member.id = id
        val member = this.memberService.updateMember(member)

        return ResponseEntity.ok(member)
    }

    /**
     * 멤버 삭제하기
     */
    @DeleteMapping("/{id}")
    fun delete(@PathVariable id: String) {
        this.memberService.deleteMember(id)
    }
}