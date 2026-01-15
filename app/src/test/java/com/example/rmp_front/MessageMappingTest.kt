package com.example.rmp_front

import com.example.rmp_front.data.dto.MessageDto
import com.example.rmp_front.data.dto.toDomain
import com.example.rmp_front.data.models.Message
import com.example.rmp_front.data.models.toDto
import org.junit.Assert.assertEquals
import org.junit.Test

class MessageMappingTest {

    @Test
    fun messageDto_toDomain_maps_all_fields_correctly() {
        val dto = MessageDto(
            id = "m1",
            chatId = "c1",
            text = "Hello",
            timestamp = "2026-01-15T00:00:00Z",
            isFromMe = true
        )

        val domain = dto.toDomain()

        assertEquals("m1", domain.id)
        assertEquals("c1", domain.chatId)
        assertEquals("Hello", domain.text)
        assertEquals("2026-01-15T00:00:00Z", domain.timestamp)
        assertEquals(true, domain.isFromMe)

        assertEquals(null, domain.replyToId)
        assertEquals(false, domain.isRead)
    }

    @Test
    fun message_toDto_maps_fields_and_ignores_domain_only_fields() {
        val domain = Message(
            id = "m2",
            chatId = "c9",
            text = "Ping",
            timestamp = "1234567890",
            replyToId = "m1",
            isFromMe = false,
            isRead = true
        )

        val dto = domain.toDto()

        assertEquals("m2", dto.id)
        assertEquals("c9", dto.chatId)
        assertEquals("Ping", dto.text)
        assertEquals("1234567890", dto.timestamp)
        assertEquals(false, dto.isFromMe)

    }
}
