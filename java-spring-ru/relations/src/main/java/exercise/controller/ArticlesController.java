package exercise.controller;

import exercise.dto.ArticleDto;
import exercise.model.Article;
import exercise.model.Category;
import exercise.repository.ArticleRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PatchMapping;


@RestController
@RequestMapping("/articles")
public class ArticlesController {

    @Autowired
    private ArticleRepository articleRepository;

    @GetMapping(path = "")
    public Iterable<Article> getArticles() {
        return this.articleRepository.findAll();
    }

    @DeleteMapping(path = "/{id}")
    public void deleteArticle(@PathVariable long id) {
        this.articleRepository.deleteById(id);
    }

    // BEGIN
    @PostMapping(path = "")
    public void createArticle(@RequestBody ArticleDto article) {

        Article newArticle = new Article();

        newArticle.setName(article.getName());
        newArticle.setBody(article.getBody());

        Category category = new Category();
        category.setId(article.getCategory().getId());
        newArticle.setCategory(category);

        this.articleRepository.save(newArticle);
    }

    @PatchMapping(path = "/{id}")
    public void updateArticle(@RequestBody ArticleDto article, @PathVariable long id) {
        Article newArticle = new Article();
        newArticle.setId(id);
        newArticle.setName(article.getName());
        newArticle.setBody(article.getBody());

        Category category = new Category();
        category.setId(article.getCategory().getId());
        newArticle.setCategory(category);

        this.articleRepository.save(newArticle);
    }

    @GetMapping(path = "/{id}")
    public Article getArticles(@PathVariable long id) {
        return this.articleRepository.findById(id);
    }
    // END
}
