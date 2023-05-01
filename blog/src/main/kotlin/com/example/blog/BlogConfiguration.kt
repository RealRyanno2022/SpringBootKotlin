@Configuration
class BlogConfiguratoin {


    @Bean
    fun databaseInitializer(userRepository: UserRepository, articleRepository: ArticleRepository) = ApplicationRunner {
        articleRepository.save(Article(
            title = "Lorem",
            headline = "Lorem",
            content = "dolor sit amet",
            author = johnDoe
        ))
        articleRepository.save(Article(
            title="Ipsum",
            headline="Ipsum",
            content="dolor sit amet",
            author = johnDoe
        ))




    }
}