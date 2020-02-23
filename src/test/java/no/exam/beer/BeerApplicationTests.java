package no.exam.beer;

import com.google.gson.Gson;
import no.exam.beer.entity.Beer;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.Matchers.equalTo;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class BeerApplicationTests {

	@Autowired
	private MockMvc mvc;


	@Test
	public void getHello() throws Exception {
		mvc.perform(MockMvcRequestBuilders.get("/").accept(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk())
				.andExpect(content().string(equalTo("Greetings from Spring Boot!")));
	}

	@Test
	public void createAndGet() throws Exception {
		Beer beer = new Beer();
		beer.setName("test beer");
		beer.setType("test type");

		Gson gson = new Gson();
		String json = gson.toJson(beer);

		List list = new ArrayList<>();

		MvcResult getEmptyList = mvc.perform(MockMvcRequestBuilders.get("/beers/").accept(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk())
				.andReturn();
		String emptyContent = getEmptyList.getResponse().getContentAsString();

		gson.fromJson(emptyContent, List.class).forEach(p -> list.add(p));
		int size = list.size();

		mvc.perform(MockMvcRequestBuilders.post("/beers").accept(MediaType.APPLICATION_JSON).contentType(MediaType.APPLICATION_JSON).content(json))
				.andExpect(status().isOk());

		MvcResult getNotEmptyList = mvc.perform(MockMvcRequestBuilders.get("/beers/").accept(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk())
				.andReturn();
		String content = getNotEmptyList.getResponse().getContentAsString();

		List newList = new ArrayList<>();
		gson.fromJson(content, List.class).forEach(p -> newList.add(p));
		assertEquals(newList.size(), size+1, "1 entry in database");
	}

	@Test
	public void createAndDelete() throws Exception {
		Beer beer = new Beer();
		beer.setName("test delete beer");
		beer.setType("test delete type");

		Gson gson = new Gson();
		String json = gson.toJson(beer);

		List list = new ArrayList<>();

		MvcResult getEmptyList = mvc.perform(MockMvcRequestBuilders.get("/beers/").accept(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk())
				.andReturn();
		String emptyContent = getEmptyList.getResponse().getContentAsString();

		gson.fromJson(emptyContent, List.class).forEach(p -> list.add(p));
		int sizeBefore = list.size();

		MvcResult resultId = mvc.perform(MockMvcRequestBuilders.post("/beers").accept(MediaType.APPLICATION_JSON).contentType(MediaType.APPLICATION_JSON).content(json))
				.andExpect(status().isOk())
				.andReturn();
		String contentId = resultId.getResponse().getContentAsString();

		assertEquals(contentId, "5");

		List listAfter = new ArrayList<>();

		MvcResult resultBeer = mvc.perform(MockMvcRequestBuilders.get("/beers/").accept(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk())
				.andReturn();
		String content = resultBeer.getResponse().getContentAsString();

		gson.fromJson(content, List.class).forEach(p -> listAfter.add(p));
		int sizeAfter = listAfter.size();
		assertEquals(sizeAfter, sizeBefore+1, "1 entry in database");
	}
}
