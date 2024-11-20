package ss.user.domain;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.ColumnDefault;

import java.math.BigDecimal;
import java.time.Instant;

@Getter
@Setter
@Entity
@Table(name = "article_meta", schema = "user")
public class Article {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Integer id;

    @jakarta.validation.constraints.Size(max = 255)
    @jakarta.validation.constraints.NotNull
    @Column(name = "title", nullable = false)
    private String title;

    @jakarta.validation.constraints.Size(max = 500)
    @Column(name = "img", length = 500)
    private String img;

    @Lob
    @Column(name = "summary")
    private String summary;

    @jakarta.validation.constraints.Size(max = 500)
    @Column(name = "link", length = 500)
    private String link;

    @ColumnDefault("0.00")
    @Column(name = "rate", precision = 3, scale = 2)
    private BigDecimal rate;

    @ColumnDefault("'0'")
    @Column(name = "views", columnDefinition = "int UNSIGNED")
    private Long views;

    @Column(name = "like", columnDefinition = "int UNSIGNED")
    private Long like;
    @Column(name = "publish_date")
    private Instant publishDate;

    @Column(name = "edit_date")
    private Instant editDate;

    @jakarta.validation.constraints.Size(max = 100)
    @jakarta.validation.constraints.NotNull
    @Column(name = "author", nullable = false, length = 100)
    private String author;

    @jakarta.validation.constraints.Size(max = 500)
    @Column(name = "author_img", length = 500)
    private String authorImg;

    @ColumnDefault("0")
    @Column(name = "is_published")
    private Boolean isPublished;

}