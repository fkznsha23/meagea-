package project;

import entity.Animal;
import entity.Promotion;
import org.aspectj.lang.annotation.Before;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import project.dto.AnimalDto;


import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class AnimalControllerTest {
    @Autowired
    TestRestTemplate testRestTemplate = new TestRestTemplate();

    @BeforeEach
    public void setUp(){
        testRestTemplate.delete("/meagea/promotion");
        testRestTemplate.delete("/meagea/animal");
    }

    @Test
    public void 유기동물_추가_테스트() {
        MultiValueMap<String, Object> map = new LinkedMultiValueMap<>();
        map.add("name", "바보쥐");
        map.add("age", 4);
        map.add("gender", "암컷");
        map.add("weight", 3.5);
        map.add("neuter", true);
        map.add("kind", "친칠라");
        map.add("detail", "믹스");
        map.add("place", "동네");
        map.add("healthState", 2);
        map.add("activity", 1);
        map.add("sociality", 2);
        map.add("friendly", 1);

        String url = "/meagea/animal";
        ResponseEntity<AnimalDto> animalRe = testRestTemplate.postForEntity(url, map, AnimalDto.class);
        assertThat(animalRe.getStatusCode()).isEqualTo(HttpStatus.OK);
        AnimalDto animal2 = animalRe.getBody();
        assertThat(animal2.getName()).isEqualTo("바보쥐");
    }

    @Test
    public void 유기동물_조회_테스트(){
        MultiValueMap<String, Object> map = new LinkedMultiValueMap<>();
        map.add("name", "바보쥐");
        map.add("age", 5);
        map.add("gender", "암컷");
        map.add("weight", 3.5);
        map.add("neuter", true);
        map.add("kind", "친칠라");
        map.add("detail", "믹스");
        map.add("place", "동네");
        map.add("healthState", 2);
        map.add("activity", 1);
        map.add("sociality", 2);
        map.add("friendly", 1);

        ResponseEntity<AnimalDto> animalRe = testRestTemplate.postForEntity("/meagea/animal", map, AnimalDto.class);

        String url = "/meagea/animal/" + animalRe.getBody().getNo();
        AnimalDto animal = testRestTemplate.getForEntity(url, AnimalDto.class).getBody();

        assertThat(animal.getName()).isEqualTo(animalRe.getBody().getName());
    }
}
