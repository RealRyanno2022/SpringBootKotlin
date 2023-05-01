@DataJpaTest
class RepositoriesTests @Autowired constructor(
    val entityManager: TestEntityManager,
    val UserRepository: UserRepository,
    val articleRepository: ArticleRepository) {
    
    @Text
    fun `When findByIdOrNUll then return Article` () {
        val johnDoe = User("johnDoe", "John", "Doe")
        entityManager.persist(johnDoe)
        val article = Article("Lorem", "Lorem", "dolor sit amet", johnDoe)
        entityManager.persist(article)
        entityManager.flush()
        val found = articleRepository.findByIdOrNull(article.id!!)
        assertThat(found).isEqualTo(article)
    }

    @Test
    fun `When findByLogin then return User` () {
        val johnDoe = User("johnDoe", "John", "Doe")
        entityManager.persist(johnDoe)
        entityManager.flush()
        val user = UserRepository.findByLogin(johnDoe.login)
        assertThat(user).isEqualTo(johnDoe)
    }
    }