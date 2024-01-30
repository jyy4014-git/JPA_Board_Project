package com.boardproject.projectboard.repository;

import com.boardproject.projectboard.config.JpaConfig;
import com.boardproject.projectboard.domain.Article;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.Import;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@DisplayName("JPA 연결 테스트")
// import 없으면 Jpaconfig에 넣었던 Auditing 옵션이 안돌아간다.
@Import(JpaConfig.class)

//안의 모든 메소드는 트랙잭션으로 작동하게 한다.
// 데이터가 롤백하게 해준다.
@DataJpaTest
class JpaRepositoryTest {
    private final ArticleRepository articleRepository;
    private final ArticleCommentRepository articleCommentRepository;

    public JpaRepositoryTest(
            //생성자 주입 패턴 완성
            @Autowired ArticleRepository articleRepository, @Autowired ArticleCommentRepository articleCommentRepository) {
        this.articleRepository = articleRepository;
        this.articleCommentRepository = articleCommentRepository;
    }

    @DisplayName("selecting 테스트")
    @Test
    void givenTestData_whenselecting_thenWorksFine(){
//        given
        //when
        List<Article> articles = articleRepository.findAll();

        //then
        assertThat(articles)
                .isNotNull()
                .hasSize(123);
    }




    @DisplayName("insert 테스트")
    @Test
    void givenTestData_wheninserting_thenWorksFine() {
//        given
        long previousCount = articleRepository.count();

        //when
        Article savedArticle = articleRepository.save(Article.of("new article", "new content", "#spring"));


        //then 기존의 개수를 세고 인서트 후 숫자가 하나 늘었다는 방식
        assertThat(articleRepository.count()).isEqualTo(previousCount + 1);
    }

    @DisplayName("update 테스트")
    @Test
    void givenTestData_whenupdating_thenWorksFine() {
//        given
        //영속성 컨텍스트로부터 entity 개체 하나 가져온다, id로 1번 가져오고, 없으면 throw시켜서 종료
        Article article = articleCommentRepository.findById(1L).orElseThrow().getArticle();
        String updatedHashtag = "springboot";
        article.setHashtag(updatedHashtag);


        //when 수정을 가한 article을 저장
        //업데이트해도 데이터가 롤백되기 때문에 저장 그리고 flush를 해줘야 db에 반영시키려 하고 로그에 update가 뜬다
        // test기 때문에 실제 db에는 반영되지 않는다
        Article savedArticle = articleRepository.saveAndFlush(article);


        //then savedArticle의 hashtag가 업데이트 되었는지 확인
        assertThat(savedArticle).hasFieldOrPropertyWithValue("hashtag", updatedHashtag);
    }

    @DisplayName("delete 테스트")
    @Test
    void givenTestData_whendeleting_thenWorksFine() {
//        given
        //영속성 컨텍스트로부터 entity 개체 하나 가져온다, id로 1번 가져오고, 없으면 throw시켜서 종료
        Article article = articleCommentRepository.findById(1L).orElseThrow().getArticle();
        //기존 게시판과 댓글들 개수 파악
        long previousArticleCount = articleRepository.count();
        long previousArticleCmommentCount = articleCommentRepository.count();
        //사라진 댓글 개수 파악
        int deletedCommentsSize = article.getArticleComments().size();


        //when
        articleRepository.delete(article); //return void다


        //then
        //게시판은 전 개수보다 1개 지워지고
        assertThat(articleRepository.count()).isEqualTo(previousArticleCount -1);
        //게시판 1개에 묶여있던 댓글 사이즈가 지워진다
        assertThat(articleCommentRepository.count()).isEqualTo(previousArticleCmommentCount - deletedCommentsSize);
    }


}