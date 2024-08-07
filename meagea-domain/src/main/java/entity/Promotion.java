package entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@Entity
@Table(name = "promotion")
@NoArgsConstructor
public class Promotion {
    // 입양 홍보 게시글 정보
    public Promotion(String title, int animalNo, String introduction, String terms) {
        this.no = (int)(Math.random()*10000);
        this.title = title;
        this.animalNo = animalNo;
        this.introduction = introduction;
        this.terms = terms;
        this.makeDate = LocalDateTime.now();
        this.modifyDate = LocalDateTime.now();
        this.remove = 0;
    }

    @Id
    private int no;
    private String title;
    private int animalNo;
    private String introduction;
    private String terms;
    private LocalDateTime makeDate;
    private LocalDateTime modifyDate;
    private int remove;
}
