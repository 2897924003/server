package ss.user.infrastructure.persistance;

import org.springframework.data.jpa.repository.JpaRepository;
import ss.user.domain.Article;

public interface ArticleRepository extends JpaRepository<Article, Long> {
}
