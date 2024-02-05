package umc.kittenback.domain;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.type.BigIntegerType;
import umc.kittenback.domain.enums.HealthNoteType;

@Entity
@Getter
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
public class HealthNote {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "pet_id")
    private Pet pet;

    // 건강수첩 구분 - 예방접종, 건강수첩, 질병
    @Enumerated(EnumType.STRING)
    private HealthNoteType recordType;

    // 건강수첩 제목
    private String title;

    //건강수첩 병원
    private String hospital;

    //건강수첩 날짜

    //건강수첩 비용
    private BigIntegerType cost;

    //건강수첩 내용
    private String content;


}
