package ru.job4j.forum;

import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import ru.job4j.forum.model.Post;
import ru.job4j.forum.service.PostService;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest(classes = Main.class)
@AutoConfigureMockMvc
public class PostControlTest {
    @Autowired
    private MockMvc mockMvc;
    @MockBean
    private PostService posts;

    @Test
    @WithMockUser
    public void shouldReturnDefaultMessageGet() throws Exception {
        Post post = Post.of("name", "desc");

        // Настраиваем mock-объект, чтобы он возвращал созданный нами объект post
        when(posts.get(1)).thenReturn(post);

        // Отправляем GET-запрос на /edit с параметром id=1
        MvcResult result = mockMvc.perform(get("/edit")
                .param("id", "1"))
                .andReturn();

        // Извлекаем объект Post из модели
        Post res = (Post) result.getModelAndView().getModel().get("post");

        // Проверяем, что описание соответствует ожидаемому значению
        assertThat(res.getDescription(), is("desc"));
//        Post post = Post.of("name", "desc");
//        Post save = posts.save(post);
//        MvcResult result = mockMvc.perform(get("/edit")
//                .param("id", "1"))
//                .andReturn();
//        Post res = (Post) result.getModelAndView().getModel().get("post");
//        assertThat(res.getDescription(), is("desc"));
    }

    @Test
    @WithMockUser
    public void shouldReturnDefaultMessageSave() throws Exception {
        this.mockMvc.perform(post("/save")
                .param("name","Куплю ладу-грант. Дорого."))
                .andDo(print())
                .andExpect(status().is3xxRedirection());
        ArgumentCaptor<Post> argument = ArgumentCaptor.forClass(Post.class);
        verify(posts).save(argument.capture());
        assertThat(argument.getValue().getName(), is("Куплю ладу-грант. Дорого."));
    }

    @Test
    @WithMockUser
    public void shouldReturnDefaultMessageEdit() throws Exception {
        this.mockMvc.perform(post("/save")
                .param("name", "Куплю машину.")
                .param("description", "Куплю ладу-грант."))
                .andDo(print())
                .andExpect(status().is3xxRedirection());
        // Затем получаем сохраненный пост
        ArgumentCaptor<Post> argument = ArgumentCaptor.forClass(Post.class);
        verify(posts).save(argument.capture());
        Post savedPost = argument.getValue();
        // Вызываем метод edit с параметром id
        this.mockMvc.perform(get("/edit")
                .param("id", String.valueOf(savedPost.getId())))
                .andDo(print())
                .andExpect(status().isOk());
        // Получаем модель и проверяем, что атрибут "post" существует
        MvcResult result = mockMvc.perform(get("/edit")
                .param("id", String.valueOf(savedPost.getId())))
                .andReturn();
        Post post = (Post) result.getModelAndView().getModel().get("post");
        assertNotNull(post);
        assertThat(post.getDescription(), is("Куплю ладу-грант."));
    }

    @Test
    @WithMockUser
    public void shouldReturnDefaultMessageCreate() throws Exception {
        MvcResult result = mockMvc.perform(get("/edit")
                .param("id", "0"))
                .andReturn();
        Post post = (Post) result.getModelAndView().getModel().get("post");
        assertThat(post.getDescription(), is(""));
    }
}
