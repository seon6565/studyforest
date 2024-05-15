package org.fullstack4.studyforest.domain;

import jakarta.persistence.*;
import lombok.*;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Entity
@Table(name="sf_bbsfree")
public class BbsIndexEntity extends BbsEntity{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(unique = true, nullable = false)
    private int bbsIdx;
    @Column(length = 20, nullable = false)
    private String category;
    @Column(length = 20, nullable = false)
    private String user_id;

}
