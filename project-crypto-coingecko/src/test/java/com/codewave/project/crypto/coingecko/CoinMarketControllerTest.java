// package com.codewave.project.crypto.coingecko;

// import static org.hamcrest.Matchers.hasItems;
// import static org.hamcrest.Matchers.hasSize;
// import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
// import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
// import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
// import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
// import static org.mockito.Mockito.*;

// import java.time.LocalDateTime;
// import java.util.Arrays;

// import org.junit.jupiter.api.Test;

// import org.modelmapper.ModelMapper;
// import org.springframework.beans.factory.annotation.Autowired;
// import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
// import org.springframework.boot.test.mock.mockito.MockBean;
// import org.springframework.http.MediaType;
// import org.springframework.test.web.servlet.MockMvc;

// import com.codewave.project.crypto.coingecko.model.CoinMarketResp;
// import com.codewave.project.crypto.coingecko.model.dto.CoinMarketRespDto;
// import com.codewave.project.crypto.coingecko.service.CoinMarketService;

// @WebMvcTest
// public class CoinMarketControllerTest {

//   @Autowired
//   MockMvc mockMvc;

//   @MockBean
//   CoinMarketService coinMarketService;

//   @MockBean
//   ModelMapper modelMapper;

//   @Test
//   void givenMockedService_whenMockMvcController_thenCorrect() throws Exception {
//     CoinMarketResp mockedData1 = new CoinMarketResp("abc",
//         "btc",
//         "Bitcoin",
//         "https://assets.coingecko.com/coins/images/1/large/bitcoin.png?1547033579",
//         27101,
//         525610677063L,
//         1,
//         569232714803L,
//         11762823382L,
//         27179,
//         26716,
//         280.29,
//         1.04504,
//         5609609392L,
//         1.07877,
//         19390706,
//         21000000,
//         21000000,
//         69045,
//         -60.7469,
//         LocalDateTime.of(2023, 6, 2, 8, 29, 31),
//         69045,
//         -60.7469,
//         LocalDateTime.of(2023, 6, 2, 8, 29, 31),
//         null,
//         LocalDateTime.of(2023, 6, 2, 8, 29, 31));
//     CoinMarketResp mockedData2 = new CoinMarketResp("def",
//         "eth",
//         "Ethereum",
//         "https://assets.coingecko.com/coins/images/279/large/ethereum.png?1595348880",
//         27101,
//         525610677063L,
//         1,
//         569232714803L,
//         11762823382L,
//         27179,
//         26716,
//         280.29,
//         1.04504,
//         5609609392L,
//         1.07877,
//         19390706,
//         21000000,
//         21000000,
//         69045,
//         -60.7469,
//         LocalDateTime.of(2023, 6, 2, 8, 29, 31),
//         69045,
//         -60.7469,
//         LocalDateTime.of(2023, 6, 2, 8, 29, 31),
//         null,
//         LocalDateTime.of(2023, 6, 2, 8, 29, 31));
//     // Mock the result of service layer
//     when(coinMarketService.getCoinMarketWithRedis()) //
//         .thenReturn(Arrays.asList(new CoinMarketResp[] { mockedData1, mockedData2 }));
//     when(modelMapper.map(mockedData1, CoinMarketRespDto.class)) //
//         .thenReturn(new ModelMapper().map(mockedData1, CoinMarketRespDto.class));
//     when(modelMapper.map(mockedData2, CoinMarketRespDto.class)) //
//         .thenReturn(new ModelMapper().map(mockedData2, CoinMarketRespDto.class));

//     // Unit Test of controller layer
//     mockMvc
//         .perform(get("/crypto/v1/coingecko/coin/market")
//             .accept(MediaType.APPLICATION_JSON))
//         .andExpect(status().isOk())
//         .andExpect(jsonPath("$.*", hasSize(2)))
//         .andExpect(jsonPath("$..id", hasItems("abc", "def")))
//         .andDo(print());

//   }
// }
