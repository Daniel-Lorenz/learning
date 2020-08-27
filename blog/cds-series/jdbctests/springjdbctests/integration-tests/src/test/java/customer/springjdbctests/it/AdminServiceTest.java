package customer.springjdbctests.it;

import static org.hamcrest.CoreMatchers.containsString;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.annotation.DirtiesContext.ClassMode;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import customer.springjdbctests.Application;

@SpringBootTest(classes = Application.class)
@AutoConfigureMockMvc
@DirtiesContext(classMode = ClassMode.BEFORE_CLASS)
public class AdminServiceTest {

	@Autowired
	private MockMvc mockMvc;

	@RepeatedTest(10000)
	public void testReadBooksAdmin() throws Exception {
		mockMvc.perform(MockMvcRequestBuilders.get("/odata/v4/AdminService/Books")).andExpect(status().isOk())
				.andExpect(jsonPath("$.value[0].title").value(containsString("Wuthering Heights")))
				.andExpect(jsonPath("$.value[0].stock").value(100))
				.andExpect(jsonPath("$.value[1].title").value(containsString("Jane Eyre (discounted)")))
				.andExpect(jsonPath("$.value[1].stock").value(500));
	}
}
