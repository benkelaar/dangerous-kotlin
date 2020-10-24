package com.bol.dangerous

import com.bol.dangerous.extensions.OrderRequest
import com.bol.dangerous.extensions.SAMSUNG_URL
import com.bol.dangerous.extensions.StockReceived
import com.bol.dangerous.extensions.StockService
import com.fasterxml.jackson.databind.ObjectMapper
import org.hamcrest.Matchers.equalTo
import org.junit.jupiter.api.Test
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.http.MediaType.APPLICATION_JSON
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.*

@SpringBootTest
@AutoConfigureMockMvc
class DangerousKotlinApplicationTests(
    private val mockMvc: MockMvc,
    private val objectMapper: ObjectMapper,
    private val stockService: StockService,
) {
    @Test
    fun `given new Order, expect order created`() {
        stockService.receiveStock(StockReceived(4, SAMSUNG_URL))

        val orderRequest = OrderRequest(2, SALON_TABLE_URL)

        mockMvc.perform(postJson("/v1/orders", orderRequest))
            .andExpect(status().isOk)
            .andExpect(jsonPath("$.amount", equalTo(2)))
    }

    @Test
    fun `given new Stock, expect stock present`() {
        val stockReceived = StockReceived(5, SAMSUNG_URL)

        mockMvc.perform(postJson("/v1/stock", stockReceived))
            .andExpect(status().isOk)
            .andExpect(content().json("""{"amount":5}"""))
    }

    private fun <T : Any> postJson(path: String, content: T) = post(path)
        .contentType(APPLICATION_JSON)
        .content(objectMapper.writeValueAsString(content))
}

private const val SALON_TABLE_URL =
    "https://www.bol.com/nl/p/mira-home-salontafel-ronde-tafel-vintage-industrieel-metalen-frame-hout-bruin-zwart-88x88x47/9200000130390539/?bltgh=ocZ1jzBnrKWsuQ10Ui9OAw.1_36.37.ProductImage"