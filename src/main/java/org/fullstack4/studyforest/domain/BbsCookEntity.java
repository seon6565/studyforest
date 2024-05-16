package org.fullstack4.studyforest.domain;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.*;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Entity
@Table(name="sf_bbscook")
public class BbsCookEntity extends BbsEntity{
    @Id
    @Column(unique = true, nullable = false)
    private int bbsIdx;


}
