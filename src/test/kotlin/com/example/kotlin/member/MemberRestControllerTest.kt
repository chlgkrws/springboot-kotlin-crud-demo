package com.example.kotlin.member

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.BeforeAll
import org.junit.jupiter.api.Test
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.MethodSource
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.boot.test.web.client.TestRestTemplate
import org.springframework.boot.test.web.client.getForEntity
import org.springframework.http.*
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory


@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class MemberRestControllerTest(
    @Autowired val restTemplate: TestRestTemplate
) {

    @BeforeAll
    fun setup() {
        restTemplate.restTemplate.requestFactory = HttpComponentsClientHttpRequestFactory()
    }

    companion object {
        @JvmStatic
        fun memberDataProvider() = listOf(
            MemberDto(name = "최학준", age = 29),
            MemberDto(name = "이진호", age = 28)
        )
    }

    @Test
    fun `멤버 리스트 조회하기`() {
        val response = restTemplate.getForEntity<String>("/members")
        assertThat(response.statusCode).isEqualTo(HttpStatus.OK)
        print(response.body)
        assertThat(response.body).isNotEmpty
    }

    @ParameterizedTest
    @MethodSource("memberDataProvider")
    fun `멤버 추가하기`(memberDto: MemberDto) {
        val headers = HttpHeaders().apply {
            contentType = MediaType.APPLICATION_JSON
        }

        val response = restTemplate.postForEntity("/members", HttpEntity(memberDto, headers), String::class.java)

        assertThat(response.statusCode).isEqualTo(HttpStatus.CREATED)
        assertThat(response.body).isNotBlank
    }

    @Test
    fun `멤버 수정하기`() {
        val member = MemberDto(
            id = "65727ddac7db871829f3eccf",
            name = "Updated Name",
            age = 999
        )

        val headers = HttpHeaders().apply {
            contentType = MediaType.APPLICATION_JSON
        }

        val response = restTemplate.patchForObject("/members/${member.id}", HttpEntity(member, headers), MemberDto::class.java)

        assertThat(response.name).isEqualTo(member.name)
        assertThat(response.age).isEqualTo(member.age)
    }

    @Test
    fun `멤버 삭제하기`() {
        val id = "65727ddac7db871829f3ecce"

        val headers = HttpHeaders().apply {
            contentType = MediaType.APPLICATION_JSON
        }

        val response = restTemplate.exchange("/members/${id}", HttpMethod.DELETE, HttpEntity(id, headers), String::class.java)

        assertThat(response.statusCode).isEqualTo(HttpStatus.OK)
    }
}