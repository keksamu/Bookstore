package com.example.bookstore;


import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import org.springframework.security.test.context.support.WithMockUser;



@SpringBootTest(classes = BookstoreApplication.class)
@AutoConfigureMockMvc
public class BookSmokeTests {
    @Autowired
    private MockMvc mockMvc;

    @Test
    void testLoginEndpointIsAccessible() throws Exception {
        mockMvc.perform(get("/login"))
               .andExpect(status().isOk());
    }

    @Test
    @WithMockUser(username = "user", roles = {"USER"})
    void testBookListPageIsAccessible() throws Exception {
        mockMvc.perform(get("/booklist"))
               .andExpect(status().isOk());
    }

    @Test
    @WithMockUser(username = "user", roles = {"USER"})
    void testAddBookPageIsAccessible() throws Exception {
        mockMvc.perform(get("/addbook"))
               .andExpect(status().isOk());
    }

    @Test
    void testDeleteBookEndpointRequiresAuth() throws Exception {
        mockMvc.perform(get("/delete/1"))
               .andExpect(status().is3xxRedirection());
    }

    @Test
    void testEditBookEndpointRequiresAuth() throws Exception {
        mockMvc.perform(get("/edit/1"))
               .andExpect(status().is3xxRedirection());
    }
}
