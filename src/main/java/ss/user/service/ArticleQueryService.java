package ss.user.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import ss.user.domain.Article;


public interface ArticleQueryService {
    Page<Article> findAllArticles(Pageable pageable);

}
