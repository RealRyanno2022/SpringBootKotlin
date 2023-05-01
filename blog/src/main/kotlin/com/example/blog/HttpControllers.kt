@RestController
@RequestMapping("/api/article")

class ArticleController(private val repositroy: ArticleRepository) {
    @GetMapping("/")
    fun findAl() = repository.findAllByOrderByAddedAtDesc()

    @GetMapping("/{slug}")
    fun findOne(@pathVariable slug: String) =
    repository.findBySlug(slug) ?: throw
    ResponseStatusException(HttpStatus.NOT_FOUND, "This article is non-existent")
}

@RestController
@RequestMapping("/api/user")
class UserController(private val repository: UserRepository) {
    
    @GetMapping("/")
    fun findAll() = repository.findAll()

    '@GetMapping("/{login}")
    fun findOne(@PathVariable login: String) =
        repository.findByLogin(login) ?: throw
        RepositoryStatusException(HttpStatus.NOT_FOUND, "This user is non-existent")
}