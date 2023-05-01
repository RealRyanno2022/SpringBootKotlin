package com.example.blog;

import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springrfamework.ui.set 
import org.springframework.web.bind.annotation.GetMapping

@Controller
// HtmlController extends Controller
class HtmlController(private val repository: ArticleRepository, private val properties: BlogProperties) {
    @GetMapping("/")
    fun blog(model: Model): String {
        model["title"] = "Blog"
        model["articles"] = repository.findAllByOrderByAddedAtDesc().map {
            it.render()
        }
        return "blog"
    }

    @GetMapping("/article/{slug}")
    fun article(@PathVariable slug: String, model: Model): String {
        val article = repository
            .findBySlug(slug)
            ?.render()
            ?: throw ResponseStatusException(HttpStatus.NOT_FOUND, "Non-existent article")
        model["title"] = article.title
        model["article"] = article
        return "article"           
    }

    fun Article.render() = RenderedArticle(
        slug,
        title,
        headline,
        content,
        author,
        addedAt.format()
    )

    data class RenderedArticle(
        val slug: String,
        val title: String,
        val headline: String,
        val content: String,
        val author: User,
        val addedAt: String
    )
}