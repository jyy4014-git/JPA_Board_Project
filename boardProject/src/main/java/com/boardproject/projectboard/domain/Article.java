package com.boardproject.projectboard.domain;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.LinkedHashSet;
import java.util.Objects;
import java.util.Set;

@Getter
@ToString(callSuper = true)
@Table(indexes = {
//        빠른검색이 가능하게 index 건다
        @Index(columnList = "title"),
        @Index(columnList = "hashtag"),
        @Index(columnList = "createdAt"),
        @Index(columnList = "createdBy"),
})

//Entity에서 Auditing 쓴다는 표시
@EntityListeners(AuditingEntityListener.class)
@Entity
public class Article extends AuditingFields{

    @Id
//    자동으로 오토 인크리먼트 걸어준다
//    Identity로 바꿔줘야 mysql의 오토인크리먼트와 충돌이 안일어난다
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id; //오토인크리먼트로 자동으로 번호부여된다

    @Setter @ManyToOne(optional = false) @JoinColumn(name = "userId") private UserAccount userAccount; // 유저 정보 (ID)


    @Setter @Column(nullable = false) private String title; //제목
    @Setter @Column(nullable = false, length = 5000) private String content; //내용

//   엔티티 클래스에 있는 모든 필드는 Transiant같은 언급 없으면 컬럼으로 본다
//    그래서 컬럼 생략 가능
    @Setter private String hashtag;


//   ToString이 모든 필드를 돌면서 찍으려 하는데, 아티클에서 댓글로 갔다가 다시 아티클로 계속 순환하다가 메모리가 터진다
//   댓글로부터 글을 참조하는건 정상이지만 글에서 모든 댓글을 참조하진 않기 떄문에 Article에서 끊는다
    @ToString.Exclude
    // mappedBy를 하지 않으면 article과 article comment를 합쳐서 하나의 테이블로 만든다 이걸 막기 위해 사용
    @OneToMany(mappedBy = "article", cascade = CascadeType.ALL)
    @OrderBy("createdAt DESC")
    //set으로 하면article에 연동되있는 코멘트는 중복을 허용치 않고 coolection으로 여기서 보겠다는 의미
    private final Set<ArticleComment> articleComments = new LinkedHashSet<>();



//   엔티티가 Hibernate 구현체 사용할 경우 기본생성자를 가져야한다
//   평소에 열 일이 없기 떄문에 protected 로 한다.
    protected Article() {
    }

//자동으로 넣지 않는 것들만 생성자를 통해 만들수 있게 한다
    private Article(UserAccount userAccount, String title, String content, String hashtag) {
        this.userAccount = userAccount;
        this.title = title;
        this.content = content;
        this.hashtag = hashtag;
    }
//    아티클을 생성하고자 할때 필수 요소들을 넣어달라고 하는것
    public static Article of(UserAccount userAccount, String title, String content, String hashtag) {
        return new Article(userAccount, title, content, hashtag);
    }

//  서로 다른 테이블의 두 로우가 같은지 검사하는 방법
//    id만 equals hashcode()에 넣는다
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Article article)) return false;

//        id가 없는 새로 만든 엔티티는 모든 내용이 같아도 각각 다 다른값으로 취급한다
//        id가 있으면, id 비교를 통해 같으면 같은 객체로 간주
        return Objects.equals(id, article.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
